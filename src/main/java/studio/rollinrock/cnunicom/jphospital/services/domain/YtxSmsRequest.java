package studio.rollinrock.cnunicom.jphospital.services.domain;


import lombok.Data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

@Data
public class YtxSmsRequest extends YtxBaseRequest{

    public void setMessageContent(String messageContent) {
        try {
            this.messageContent = URLEncoder.encode(messageContent, "GBK");
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("短信发送内容通过gbk进行url编码失败：", e);
        }
    }

    private String messageContent;

    private String messageType;

    private String userNumber;

    private String serialNumber;

    private String scheduleTime;

    private String extendAccessNum;

    private String f;






    public Map<String, String> manipulateMap() {
//        try {
            Map<String, String> map = new HashMap<>();
            map.put("SpCode", spCode);
            map.put("LoginName", loginName);
            map.put("Password", password);
            map.put("MessageContent", messageContent);
            map.put("MessageType", messageType);
            map.put("UserNumber", userNumber);
            map.put("SerialNumber", serialNumber);
            map.put("ScheduleTime", scheduleTime);
            map.put("ExtendAccessNum", extendAccessNum);
            map.put("f", f);
            return map;
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//            throw new IllegalStateException("编码失败");
//        }

    }

    /**
     * 平台访问地址：http://ytx.zj165.com:9098
     * 体验企业编码：democust
     * 体验登录账号：democust
     * 体验登录密码：democust_123123
     */

    /**
     * 基于测试账号新建一条短信验证码
     * @param mobile
     * @param messageContent
     * @return
     */
    public static YtxSmsRequest newVCInsBasedOnTestAccount(String mobile, String messageContent){
        YtxSmsRequest request = new YtxSmsRequest();
        request.setSpCode("democust");
        request.setLoginName("democust");
        request.setPassword("democust_123123");
        request.setMessageType("1"); //短信验证码
        request.setMessageContent(messageContent);
        request.setUserNumber(mobile);
        request.setF("");
        request.setSerialNumber(String.format("%020d",System.currentTimeMillis()));
        request.setScheduleTime("");
        request.setExtendAccessNum("");

        return request;
    }

    public static void main(String[] args) {
        System.out.println(YtxSmsRequest.newVCInsBasedOnTestAccount("123", "132"));
    }
}
