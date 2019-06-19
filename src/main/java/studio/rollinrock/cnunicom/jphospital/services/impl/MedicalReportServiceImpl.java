package studio.rollinrock.cnunicom.jphospital.services.impl;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import studio.rollinrock.cnunicom.jphospital.exceptions.UserWarningException;
import studio.rollinrock.cnunicom.jphospital.repositories.MRUploadRecordRepository;
import studio.rollinrock.cnunicom.jphospital.repositories.entities.MedicalReportUploadRecordEntity;
import studio.rollinrock.cnunicom.jphospital.services.MedicalReportService;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;


@Slf4j
@Service
public class MedicalReportServiceImpl implements MedicalReportService {

    @Autowired
    private FastFileStorageClient storageClient;

    @Autowired
    private MRUploadRecordRepository mrUploadRecordRepository;

    @Override
    public String uploadReportByPdf(String patientMobile, MultipartFile medicalReport) {
        Set<MetaData> metas = new HashSet<>();
        MetaData belong = new MetaData("owner-mobile", Objects.requireNonNull(patientMobile));
        MetaData uploadTime = new MetaData("upload-time", new Date().toString());
        metas.add(belong);
        metas.add(uploadTime);


        StorePath storePath = null;
        try (InputStream is = medicalReport.getInputStream()) {
            storePath = storageClient.uploadFile(is, medicalReport.getSize(), "pdf", metas);
        } catch (IOException e) {
            log.error("文件上传输入流获取异常：", e);
        }

        if (null == storePath) throw new UserWarningException("1", "文件上传失败");

        //1. 保存文件路径到数据库
        mrUploadRecordRepository.save(MedicalReportUploadRecordEntity.newIns(patientMobile, storePath.getFullPath()));
        //2.

        log.info("文件上传结果：group[{}],path[{}],fullPath[{}]", storePath.getGroup(), storePath.getPath(), storePath.getFullPath());

        return storePath.getFullPath();

    }

    @Override
    public List<MedicalReportUploadRecordEntity> listAllReports() {
        return Lists.newArrayList(mrUploadRecordRepository.findAll());
    }

    @Override
    public String viewReport(String patientMobile) {
        return null;
    }
}
