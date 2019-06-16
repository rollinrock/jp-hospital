package studio.rollinrock.cnunicom.jphospital.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.rollinrock.cnunicom.jphospital.domain.UserMedicalReport;
import studio.rollinrock.cnunicom.jphospital.services.MedicalReportService;

@RequestMapping("/user")
@RestController
public class UserMedicalReportController {

    @Autowired
    private MedicalReportService medicalReportService;

    @ApiOperation(value = "查看体检报告", notes = "默认查看最近一次体检报告")
    @GetMapping("/{userId}/latest_report")
    public HttpResult<UserMedicalReport> latest(
            @ApiParam(name = "用户id", required = true) @PathVariable("userId") String userId) {

        return null;
    }

}
