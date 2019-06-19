package studio.rollinrock.cnunicom.jphospital.repositories.entities;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "t_medical_report_upload_record")
public class MedicalReportUploadRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "owner_mobile", nullable = false)
    private String ownerMobile;

    @Column(name = "fdfs_path", nullable = false)
    private String fdfsPath;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;


    public static MedicalReportUploadRecordEntity newIns(String host, String ownerMobile, String fdfsPath){
        MedicalReportUploadRecordEntity entity = new MedicalReportUploadRecordEntity();
        entity.setOwnerMobile(Objects.requireNonNull(ownerMobile));
        entity.setFdfsPath(host.concat(Objects.requireNonNull(fdfsPath)));

        return entity;
    }
}
