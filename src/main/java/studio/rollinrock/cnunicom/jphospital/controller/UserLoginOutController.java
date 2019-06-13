package studio.rollinrock.cnunicom.jphospital.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.rollinrock.cnunicom.jphospital.entityjpa.UseMp;
import studio.rollinrock.cnunicom.jphospital.repository.UseMpRepository;
import studio.rollinrock.cnunicom.jphospital.services.SmsVerificationCodeService;
import studio.rollinrock.cnunicom.jphospital.services.UserLoginoutService;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserLoginOutController {

    @Autowired(required = false)
    private SmsVerificationCodeService smsVerificationCodeService;

    @Autowired(required = false)
    private UserLoginoutService userLoginoutService;

    @Autowired(required = false)
    private UseMpRepository useMpRepository;
    @ApiOperation(value = "发送短信验证码")
//    @PostMapping("/sms_verify_code")
    @GetMapping("/sms_verify_code")
    public HttpResult<Boolean> getSmsVerifyCode(@ApiParam(name = "用户手机号", required = true) String mobile){
        int headerNum=Integer.parseInt(mobile.substring(0,3));
        List<UseMp> useMps=useMpRepository.findAllByHeaderNum(headerNum);
        if(useMps!=null&&useMps.size()>0){
            return HttpResult.succeedWithData(smsVerificationCodeService.sendByMobile(mobile));
        }
        return  HttpResult.failWithReason("当前非联通号码暂无法开通");
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
