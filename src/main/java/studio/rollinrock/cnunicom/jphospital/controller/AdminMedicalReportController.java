package studio.rollinrock.cnunicom.jphospital.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import studio.rollinrock.cnunicom.jphospital.services.MedicalReportService;

import java.util.Arrays;

@RequestMapping("/medical_report")
@Controller
public class AdminMedicalReportController {

    @Autowired
    private MedicalReportService medicalReportService;

    @GetMapping("/show")
    public String showPageOfUpload() {
        return "/admin/upload";
    }

    @ApiOperation(value = "上传pdf版本体检报告", notes = "rt")
    @PostMapping("/upload")
    public ModelAndView upload(@ApiParam("体检报告所属人电话") String ownerMobile,
                               @ApiParam("上传文件") @RequestParam("file") MultipartFile multipartFile, Model model) {
        String path = medicalReportService.uploadReportByPdf(ownerMobile, multipartFile);
        model.addAttribute("reports", Arrays.asList(medicalReportService.listAllReports()));
        return new ModelAndView("redirect:/admin/upload_result", "model", model);
    }


}
