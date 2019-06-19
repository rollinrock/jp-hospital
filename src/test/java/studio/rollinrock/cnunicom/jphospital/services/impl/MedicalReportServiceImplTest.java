package studio.rollinrock.cnunicom.jphospital.services.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import studio.rollinrock.cnunicom.jphospital.JpHospitalApplicationTests;
import studio.rollinrock.cnunicom.jphospital.services.MedicalReportService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static org.springframework.util.Assert.isTrue;

public class MedicalReportServiceImplTest extends JpHospitalApplicationTests {

    @Autowired
    private MedicalReportService medicalReportService;

    @Test
    public void uploadReportByPdf() throws IOException {
        Path path = Paths.get("/Users/cj/Desktop", "考评整合.pdf");
        MultipartFile file = new MockMultipartFile("曹晶的体检报告.pdf", Files.newInputStream(path, StandardOpenOption.READ));
        medicalReportService.uploadReportByPdf("15657978981", file);

    }

    @Test
    public void viewReport() {
    }
}