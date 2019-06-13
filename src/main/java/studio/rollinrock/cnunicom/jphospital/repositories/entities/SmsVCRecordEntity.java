package studio.rollinrock.cnunicom.jphospital.repositories.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 短信验证码流水
 */
@Data
@Entity
@Table(name = "t_sms_vc_record")
public class SmsVCRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 32)
    private String mobile;

    @Column(name = "verification_code", nullable = false, length = 8)
    private short verificationCode;

    @Column(nullable = false)
    private Date createTime;
}
