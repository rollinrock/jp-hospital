package studio.rollinrock.cnunicom.jphospital.services;

import org.springframework.web.multipart.MultipartFile;
import studio.rollinrock.cnunicom.jphospital.repositories.entities.MedicalReportUploadRecordEntity;

import java.util.List;

public interface MedicalReportService {
    String uploadReportByPdf(String patientMobile, MultipartFile medicalReport);
    List<MedicalReportUploadRecordEntity> listAllReports();
    String viewReport(String patientMobile);



}
