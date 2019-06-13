package studio.rollinrock.cnunicom.jphospital.services.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;
import studio.rollinrock.cnunicom.jphospital.services.domain.YtxSmsRequest;
import studio.rollinrock.cnunicom.jphospital.services.domain.YtxSmsResult;

import java.util.Map;

/**
 * 云通信短信api
 */
public interface YtxSmsApi{

    String baseUrl = "http://ytx.zj165.com:9098/api/";

    @Deprecated //仅供测试用
    @Headers({
            "Content-type:application/x-www-form-urlencoded"
    })
    @GET("send")
    Call<ResponseBody> sendOk(@QueryMap(encoded = true) Map<String, String> request);

    @Headers({
            "Content-type:application/x-www-form-urlencoded"
    })
    @GET("send")
    Call<YtxSmsResult> send(@QueryMap(encoded = true) Map<String, String> request);

    @POST("send")
    Call<YtxSmsResult> sendByPost(@Body YtxSmsRequest request);

}
