package studio.rollinrock.cnunicom.jphospital.entityjpa;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="test")
public class UseMp implements Serializable {

    private static final long serialVersionUID = -7642095996735053870L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",nullable = false,unique =true,length = 10)
    private  int id;

    @Column(name="header_num",length = 50)
    private Integer headerNum;

    public int getId() {
        return id;
    }

    public Integer getHeaderNum() {
        return headerNum;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeaderNum(Integer headerNum) {
        this.headerNum = headerNum;
    }
}
