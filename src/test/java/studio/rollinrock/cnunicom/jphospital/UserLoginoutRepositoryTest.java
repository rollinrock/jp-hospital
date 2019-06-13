package studio.rollinrock.cnunicom.jphospital;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import studio.rollinrock.cnunicom.jphospital.repositories.UserLoginOutRepository;
import studio.rollinrock.cnunicom.jphospital.repositories.entities.UserLoginOutRecordEntity;


public class UserLoginoutRepositoryTest extends JpHospitalApplicationTests {

    @Autowired
    private UserLoginOutRepository userLoginOutRepository;

    @Test
    public void saveTest() {

        userLoginOutRepository.save(UserLoginOutRecordEntity.instantiate("13685760392", "login"));
    }
}
