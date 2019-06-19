package studio.rollinrock.cnunicom.jphospital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class JpHospitalApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpHospitalApplication.class, args);
    }

}
