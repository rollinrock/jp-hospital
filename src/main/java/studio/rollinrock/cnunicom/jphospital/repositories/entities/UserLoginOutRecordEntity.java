package studio.rollinrock.cnunicom.jphospital.repositories.entities;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "t_user_log")
public class UserLoginOutRecordEntity {

    private static final String OP_LOGIN = "login";
    private static final String OP_LOGOUT = "logout";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mobile")
    private String mobile;

    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "operation")
    private String operation;

    public static UserLoginOutRecordEntity newInsAsLogin(String mobile){
        return newIns(mobile, OP_LOGIN);
    }

    public static UserLoginOutRecordEntity newInsAsLogout(String mobile){
        return newIns(mobile, OP_LOGOUT);
    }

    private static UserLoginOutRecordEntity newIns(String mobile, String operation){
        UserLoginOutRecordEntity entity = new UserLoginOutRecordEntity();
        entity.setMobile(Objects.requireNonNull(mobile));
        entity.setOperation(Objects.requireNonNull(operation));
        return entity;
    }
}
