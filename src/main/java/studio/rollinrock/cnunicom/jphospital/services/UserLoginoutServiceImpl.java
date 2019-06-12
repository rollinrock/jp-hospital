package studio.rollinrock.cnunicom.jphospital.services;


import org.springframework.stereotype.Service;

@Service
public class UserLoginoutServiceImpl implements UserLoginoutService {
    @Override
    public boolean login(String mobile, String smsCode) {
        return true;
    }

    @Override
    public boolean logout(String mobile) {
        return true;
    }
}
