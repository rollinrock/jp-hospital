package studio.rollinrock.cnunicom.jphospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import studio.rollinrock.cnunicom.jphospital.entityjpa.UseMp;

import java.util.List;

@Repository
public interface UseMpRepository extends JpaRepository<UseMp,Integer> {

    List<UseMp> findAllByHeaderNum(int headerNum);
}
