package studio.rollinrock.cnunicom.jphospital.services;

public interface VCGenerateService {

    /**
     * 随机生成6位数字验证码
     * @param aliveExpiry 验证码有效期，即时效内不允许产生重复验证码
     * @return
     */
    String genRandomNumIn6(long aliveExpiry);
}
