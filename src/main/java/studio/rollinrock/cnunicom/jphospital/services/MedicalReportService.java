package studio.rollinrock.cnunicom.jphospital.services;

import org.springframework.web.multipart.MultipartFile;

public interface MedicalReportService {
    boolean uploadReportByPdf(String patientMobile, MultipartFile medicalReport);
    String viewReport(String patientMobile);



}
