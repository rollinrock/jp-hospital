package studio.rollinrock.cnunicom.jphospital.services.impl;

import lombok.extern.slf4j.Slf4j;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Converter;
import retrofit2.Retrofit;
import studio.rollinrock.cnunicom.jphospital.repositories.SmsRecordRepository;
import studio.rollinrock.cnunicom.jphospital.repositories.entities.SmsRecordEntity;
import studio.rollinrock.cnunicom.jphospital.services.ActualSmsService;
import studio.rollinrock.cnunicom.jphospital.services.api.YtxSmsApi;
import studio.rollinrock.cnunicom.jphospital.services.domain.YtxSmsRequest;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 实际短信发送服务，注意和短信业务判断解耦
 *
 * 主要功能目标：保证短信的平稳发送
 * 主要功能：短信渠道路由（根据成功率、成本等平滑切换）、黑名单、短信模板、接口切换、欠费预警等等
 *
 * 目前只对接一家云通信可以简单做
 *
 */

@Slf4j
@Service
public class ActualSmsServiceImpl implements ActualSmsService {

    @Autowired
    private SmsRecordRepository smsRecordRepository;

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(YtxSmsApi.baseUrl)
            .addConverterFactory(UrlQueryParamsConverterFactory.create())
            .build();

    private YtxSmsApi ytxSmsApi = retrofit.create(YtxSmsApi.class);


    private final static class KeyValueMapConverter implements Converter<ResponseBody, Map<String, String>> {

        @Override
        public Map<String, String> convert(ResponseBody value) throws IOException {
            StringTokenizer tokenizer = new StringTokenizer(value.string(), "&");

            Map<String, String> result = new HashMap<>();
            while(tokenizer.hasMoreTokens()) {
                String token = tokenizer.nextToken();
                if (token.indexOf("=") != -1) {
                    String[] info = token.split("=");
                    result.put(info[0], info[1]);
                }
            }

            return result;
        }
    }

    private final static class UrlQueryParamsConverterFactory extends Converter.Factory{
        static UrlQueryParamsConverterFactory create() {
            return new UrlQueryParamsConverterFactory();
        }

        @Override
        public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
            return new KeyValueMapConverter();
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
    public boolean sendVerificationCode(String mobile, String content) {
        SmsRecordEntity smsRecord = smsRecordRepository.save(SmsRecordEntity.newIns(mobile, content));
        Call<Map<String, String>> caller = ytxSmsApi.send(YtxSmsRequest.newVCInsBasedOnTestAccount(mobile, content).manipulateMap());

        try {
            Map<String, String> resultMap = caller.execute().body();

            String resultCode = resultMap.get("result");
            String resultDescription = resultMap.get("description");
            log.info("给手机号[{}]发送短信验证码内容[{}]结果：{} - {}", mobile, content, resultCode, resultDescription);

            try {
                smsRecord.setResult(resultCode);
                smsRecord.setDescription(resultDescription);
                smsRecordRepository.save(smsRecord);
            } catch (Exception e) {
                //防止数据保存异常 影响其他业务处理
                log.error("保存短信[id: {}]发送结果异常：", smsRecord.getId(), e);
            }

            if (!resultCode.equals("0"))
                throw new IllegalStateException("短信接口返回失败：" + resultCode + resultDescription);

            return true;
        } catch (IOException e) {
            log.error("给手机号[]发送短信验证码[内容:{}]异常：", mobile, content, e);
            throw new IllegalStateException("云通信短信接口异常", e);
        }

    }
}
