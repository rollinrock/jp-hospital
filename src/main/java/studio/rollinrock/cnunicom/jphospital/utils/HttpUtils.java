package studio.rollinrock.cnunicom.jphospital.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 类说明：Http请求工具
 * <p>
 * 类名称: HttpUtils.java
 *
 * @version v1.0.0
 * @author uccp
 * @date 2019/3/22 22:37
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 */
public class HttpUtils {
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 10000;
    static {
        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);

        requestConfig = configBuilder.build();
    }

    /**
     * 功能描述：发送 GET 请求（HTTP），不带参数
     *
     * @param url 请求地址
     * @return java.lang.String
     * @author uccp
     * @date 2019/3/22 23:14
     */
    public static String doGet(String url) throws IOException {
        return doGet(url, new HashMap<>(16));
    }

    /**
     * 功能描述：发送 GET 请求（HTTP），带参数Key-Value形式
     *
     * @param url 请求地址
     * @param params 请求参数对象
     * @return java.lang.String
     * @author uccp
     * @date 2019/3/22 23:14
     */
    public static String doGet(String url, Map<String, Object> params) throws IOException{
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        HttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                InputStream instream = entity.getContent();
                result = readInputStreamToString(instream, "UTF-8");
            }
        } catch (IOException e) {
            logger.error("doGet请求异常", e);
            throw e;
        }
        return result;
    }

    /**
     * 功能描述：发送 POST 请求（HTTP），不带参数
     *
     * @param url 请求地址
     * @return java.lang.String
     * @author uccp
     * @date 2019/3/22 23:14
     */
    public static String doPost(String url) throws IOException {
        return doPost(url, new HashMap<>(16));
    }

    /**
     * 功能描述：发送 POST 请求（HTTP），带参数Key-Value形式
     *
     * @param url 请求地址
     * @param params 请求参数对象
     * @return java.lang.String
     * @author uccp
     * @date 2019/3/22 23:14
     */
    public static String doPost(String url, Map<String, Object> params) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr;
        HttpPost httpPost = new HttpPost(url);
        //这里要提前设置好Content-type请求头
        httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        CloseableHttpResponse response = null;
        try {
            httpPost.setConfig(requestConfig);
            List<NameValuePair> pairList = new ArrayList<>(params.size());
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
                pairList.add(pair);
            }
            httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("UTF-8")));
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            logger.error("doPost请求异常", e);
            throw e;
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    throw e;
                }
            }
        }
        return httpStr;
    }

    /**
     * 功能描述：读取流的数据
     *
     * @param inputStream 输入流对象
     * @param charsetName 字符集名称
     * @return java.lang.String
     * @author uccp
     * @date 2019/3/22 23:17
     */
    private static String readInputStreamToString(InputStream inputStream, String charsetName)throws IOException{
        StringBuffer sb = new StringBuffer();
        // 定义BufferedReader输入流来读取URL的响应
        BufferedReader in;
        try {
            in = new BufferedReader(new InputStreamReader(inputStream, charsetName));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
        }catch (IOException e){
            logger.error("readInputStreamToString读取异常", e);
            throw e;
        }finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e2) {
                logger.error("流关闭异常", e2);
                throw e2;
            }
        }
        return sb.toString();
    }
}
