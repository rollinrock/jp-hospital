package studio.rollinrock.cnunicom.jphospital.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import studio.rollinrock.cnunicom.jphospital.repositories.entities.SmsRecordEntity;


@Repository
public interface SmsRecordRepository extends CrudRepository<SmsRecordEntity, Long> {



}
