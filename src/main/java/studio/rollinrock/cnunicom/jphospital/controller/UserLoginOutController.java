package studio.rollinrock.cnunicom.jphospital.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.rollinrock.cnunicom.jphospital.services.SmsVerificationCodeService;
import studio.rollinrock.cnunicom.jphospital.services.UserLoginoutService;

@RestController
@RequestMapping("/user")
public class UserLoginOutController {

    @Autowired(required = false)
    private SmsVerificationCodeService smsVerificationCodeService;

    @Autowired(required = false)
    private UserLoginoutService userLoginoutService;

    @ApiOperation(value = "发送短信验证码")
    @PostMapping("/sms_verify_code")
    public HttpResult<Boolean> getSmsVerifyCode(@ApiParam(name = "用户手机号", required = true) String mobile){
        return HttpResult.succeedWithData(smsVerificationCodeService.sendByMobile(mobile));
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/login")
    public HttpResult<Boolean> userLogin(@ApiParam(name = "用户手机号", required = true) String mobile,
                                         @ApiParam(name = "短信验证码", required = true) String smsCode){
        return HttpResult.succeedWithData(userLoginoutService.login(mobile, smsCode));
    }

    @ApiParam(value = "用户登出")
    @PostMapping("/logout")
    public HttpResult<Boolean> userLogout(@ApiParam(name = "用户手机号", required = true) String mobile){
        return HttpResult.succeedWithData(userLoginoutService.logout(mobile));
    }
}
