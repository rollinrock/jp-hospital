package studio.rollinrock.cnunicom.jphospital.repositories;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import studio.rollinrock.cnunicom.jphospital.repositories.entities.UserLoginOutRecordEntity;

@Repository
public interface UserLoginOutRepository extends CrudRepository<UserLoginOutRecordEntity, Long> {
}
