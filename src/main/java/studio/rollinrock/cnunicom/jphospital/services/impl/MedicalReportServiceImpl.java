package studio.rollinrock.cnunicom.jphospital.services.impl;

import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import studio.rollinrock.cnunicom.jphospital.services.MedicalReportService;


@Slf4j
@Service
public class MedicalReportServiceImpl implements MedicalReportService {

    @Autowired
    private FastFileStorageClient storageClient;

    @Override
    public boolean uploadReportByPdf(String patientMobile, MultipartFile medicalReport) {
        return false;
    }

    @Override
    public String viewReport(String patientMobile) {
        return null;
    }
}
