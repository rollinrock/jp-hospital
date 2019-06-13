package studio.rollinrock.cnunicom.jphospital.repositories.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "t_user_log")
public class UserLoginOutRecordEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "operation")
    private String operation;

    //todo 只做示例用
    public static UserLoginOutRecordEntity instantiate(String mobile, String operation){
        UserLoginOutRecordEntity entity = new UserLoginOutRecordEntity();
        entity.setMobile(mobile);
        entity.setOperation(operation);
        entity.setCreateTime(new Date());
        return entity;
    }
}
