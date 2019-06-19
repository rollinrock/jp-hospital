package studio.rollinrock.cnunicom.jphospital.repositories.entities;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * 短信验证码流水
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_sms_vc_record")
public class SmsVCRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String mobile;

    @Column(name = "verification_code", nullable = false, length = 8)
    private String verificationCode;

    @CreatedDate
    @Column(nullable = false)
    private Date createTime;

    @Column(name = "alive_expiry_in_mills", nullable = false)
    private Long aliveExpiryInMills;

    @Column(nullable = false)
    private boolean usedOrNot = false;

    public static SmsVCRecordEntity newIns(String mobile, String verificationCode, long aliveExpiryInMills) {
        SmsVCRecordEntity entity = new SmsVCRecordEntity();
        entity.setMobile(mobile);
        entity.setVerificationCode(verificationCode);
        entity.setAliveExpiryInMills(aliveExpiryInMills);
        return entity;
    }
}
