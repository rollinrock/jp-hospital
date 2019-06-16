package studio.rollinrock.cnunicom.jphospital.repositories.entities;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@Table(name = "t_sms_record")
public class SmsRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String mobile;

    @Column(nullable = false, length = 255)
    private String content;

    @CreatedDate
    @Column(name = "create_time", nullable = false)
    private Date createTime;

    @Column(length = 5)
    private String result;

    @Column(length = 64)
    private String description;


    public static SmsRecordEntity newIns(String mobile, String content) {
        SmsRecordEntity entity = new SmsRecordEntity();
        entity.setMobile(Objects.requireNonNull(mobile));
        entity.setContent(Objects.requireNonNull(content));

        return entity;
    }

}
