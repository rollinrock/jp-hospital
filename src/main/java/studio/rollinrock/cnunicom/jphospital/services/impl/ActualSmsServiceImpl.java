package studio.rollinrock.cnunicom.jphospital.services.impl;

import lombok.extern.slf4j.Slf4j;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import studio.rollinrock.cnunicom.jphospital.services.ActualSmsService;
import studio.rollinrock.cnunicom.jphospital.services.api.YtxSmsApi;
import studio.rollinrock.cnunicom.jphospital.services.domain.YtxSmsRequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Map;

/**
 * 实际短信发送服务，注意和短信业务判断解耦
 *
 * 主要功能目标：保证短信的平稳发送
 * 主要功能：短信渠道路由（根据成功率、成本等平滑切换）、黑名单、短信模板、接口切换、欠费预警等等
 *
 * 目前只对接一家可以简单做
 *
 */

@Slf4j
@Service
public class ActualSmsServiceImpl implements ActualSmsService {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(YtxSmsApi.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private YtxSmsApi ytxSmsApi = retrofit.create(YtxSmsApi.class);

    /**
     *
     */
    private final static class UrlQueryParamsConverterFactory extends Converter.Factory{
//        private final YtxSmsResultConverterFactory factory;
        static UrlQueryParamsConverterFactory create() {
            return new UrlQueryParamsConverterFactory();
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return super.responseBodyConverter(type, annotations, retrofit);
//            if ()
        }

        @Override
        public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
            return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
        }

        @Override
        public Converter<?, String> stringConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return super.stringConverter(type, annotations, retrofit);
        }
    }

    @Override
    public boolean sendVerificationCode(String mobile) {
        if (!checkMobile(mobile)) throw new IllegalArgumentException("非法手机号码，无法发送短信验证码");

        Call<ResponseBody> call = null;
        try {

            call = ytxSmsApi.sendOk(YtxSmsRequest.newVCInsBasedOnTestAccount(mobile, "短信验证码").manipulateMap());
            Response<ResponseBody> resp = call.execute();
            log.info("接口测试返回结果：{}", resp.body().string());

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return true;

    }

    @Override
    public boolean sendVerificationCodeByCustom(String mobile, String verificationCode, long expiry) {
        if (!checkMobile(mobile)) throw new IllegalArgumentException("非法手机号码，无法发送自定义短信验证码");

        return false;
    }

    @Override
    public boolean send(String mobile, String content) {
        return false;
    }

    @Override
    public boolean sendByTemplate(String mobile, String templateId, Map<String, String> replacements) {
        return false;
    }
}
