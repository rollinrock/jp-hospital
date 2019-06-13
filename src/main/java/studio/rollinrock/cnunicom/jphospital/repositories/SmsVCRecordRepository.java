package studio.rollinrock.cnunicom.jphospital.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import studio.rollinrock.cnunicom.jphospital.repositories.entities.SmsVCRecordEntity;

import java.util.Date;

@Repository
public interface SmsVCRecordRepository extends CrudRepository<SmsVCRecordEntity, Long> {

    @Query(value = "select r.create_time from t_sms_vc_record r where r.mobile = :mobile order by create_time desc limit 1", nativeQuery = true)
    Date selectLatestVCCreateTime(@Param("mobile") String mobile);

}
