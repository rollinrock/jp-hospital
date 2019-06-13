package studio.rollinrock.cnunicom.jphospital.services.domain;


import lombok.Data;

/**
 * 云通讯请求父类
 */
@Data
public class YtxBaseRequest {

    /** 公司名称 **/
    protected String spCode;

    protected String loginName;

    protected String password;

}
