package studio.rollinrock.cnunicom.jphospital.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class SmsSendUtils {
    private static final Logger logger = LoggerFactory.getLogger(SmsSendUtils.class);
    /**
     * 功能描述：短信发送，GET请求
     *
     * @param url 短信url地址
     * @param params 短信请求参数
     * @return void
     * @author uccp
     * @date 2019/3/22 23:06
     */
    private boolean smsSendByGet(String url, Map<String, Object> params){
        try {
            String result = HttpUtils.doGet(url, params);
            logger.info("GET短信发送的响应报文【{}】", result);
            return  true;
        }catch (IOException e){
            logger.error("GET短信发送发生异常", e);
        }
        return  false;
    }
    public boolean Send(String phone){
        //初始化参数
        String url = "http://ytx.zj165.com:9098/api/send";
        Map<String, Object> params = initSmsParams(phone);

        logger.debug("请求url地址={}", url);
        logger.debug("请求参数体={}", params);

        //GET发送
        return  smsSendByGet(url, params);

        //POST发送
//       smsSendByPost(url, params);
    }
    /**
     * 功能描述：短信发送，POST请求
     *
     * @param url 短信url地址
     * @param params 短信请求参数
     * @return void
     * @author uccp
     * @date 2019/3/22 23:06
     */
    private void smsSendByPost(String url, Map<String, Object> params){
        try {
            String result = HttpUtils.doPost(url, params);
            logger.info("POST短信发送的响应报文【{}】", result);
        }catch (IOException e){
            logger.error("POST短信发送发生异常", e);
        }
    }
    /**
     * 功能描述：初始化短信参数
     *
     * @param
     * @return java.util.Map<java.lang.String,java.lang.Object>
     * @author uccp
     * @date 2019/3/22 23:09
     */
    private Map<String, Object> initSmsParams(String phone){
        Date date = Calendar.getInstance().getTime();
        String messageContent = EncodeUtils.encodeStr("你好，测试短信Get发送");
        String scheduleTime = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
        String serialNumber = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(date)+this.getThreeRandomNumbers();

        Map<String, Object> params = new HashMap<>(9);
        //企业编号
        params.put("SpCode", "democust");
        //用户名称
        params.put("LoginName", "democust");
        //用户密码
        params.put("Password", "democust_123123");
        //短信内容, 最大480个字（短信内容要求的编码为gbk）
        params.put("MessageContent", "验证码:"+this.getCode());
        //短信类型，1=验证码、2=通知、3=广告 （没传或者非1、2、3系统会默认为4=其他）
        params.put("MessageType", "1");
        //手机号码(多个号码用”,”分隔)，最多1000个号码
        params.put("UserNumber", phone);
        //流水号，20位数字，唯一 （规则自定义,建议时间格式精确到毫秒）
        params.put("SerialNumber", serialNumber);
        //预约发送时间，格式:yyyyMMddhhmmss,如‘20090901010101’
        params.put("ScheduleTime", scheduleTime);
        //接入号扩展号（默认不填，扩展号为数字，扩展位数由当前所配的接入号长度决定，整个接入号最长4位）
        params.put("ExtendAccessNum", "");
        /*
         * 提交时检测方式
         * 为1时，提交号码中有效的号码仍正常发出短信，无效的号码在返回参数faillist中列出
         * 不为1或该参数不存在，提交号码中只要有无效的号码，那么所有的号码都不发出短信，所有的号码在返回参数faillist中列出
         */
        params.put("f", "");

        return params;
    }
    /**
     * 功能描述：获得3位随机数
     *
     * @param
     * @return int
     * @author uccp
     * @date 2019/3/22 23:05
     */
    private int getThreeRandomNumbers(){
        return new java.util.Random().nextInt(900)+100;
    }
    private  String getCode(){
        Random rnd = new Random();
        int code = rnd.nextInt(899999) + 100000;
        return String.valueOf(code);
    }
}

