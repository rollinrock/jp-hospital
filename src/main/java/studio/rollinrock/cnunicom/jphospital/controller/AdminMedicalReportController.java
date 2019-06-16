package studio.rollinrock.cnunicom.jphospital.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import studio.rollinrock.cnunicom.jphospital.services.MedicalReportService;

@RequestMapping("/medical_report")
@RestController
public class AdminMedicalReportController {

    @Autowired
    private MedicalReportService medicalReportService;

    @ApiOperation(value = "上传pdf版本体检报告", notes = "rt")
    @PostMapping("/upload")
    public HttpResult<String> upload(@ApiParam("体检报告所属人电话") String ownerMobile,
                                     @ApiParam("上传文件") @RequestParam("file") MultipartFile multipartFile) {

        return HttpResult.succeedWithData("访问路径");
    }
}
