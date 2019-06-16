package studio.rollinrock.cnunicom.jphospital.services;

public interface SmsVerificationCodeService {
    boolean sendByMobile(String mobile);
    boolean validate(String mobile, String verificationCode);
}
