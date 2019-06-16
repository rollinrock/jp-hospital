package studio.rollinrock.cnunicom.jphospital.services.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.QueryMap;

import java.util.Map;

/**
 * 云通信短信api
 */
public interface YtxSmsApi{

    String baseUrl = "http://ytx.zj165.com:9098/api/";


    @Headers({
            "Content-type:application/x-www-form-urlencoded"
    })
    @GET("send")
    Call<Map<String, String>> send(@QueryMap(encoded = true) Map<String, String> request);

}
