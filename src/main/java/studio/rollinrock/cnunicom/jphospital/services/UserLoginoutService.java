package studio.rollinrock.cnunicom.jphospital.services;

public interface UserLoginoutService {
    boolean login(String mobile, String smsCode);
    boolean logout(String mobile);
}
