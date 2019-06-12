package studio.rollinrock.cnunicom.jphospital.configs;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class Swagger2Config {

    @Bean
    public Docket createRestfulApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.buildApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("studio.rollinrock.cnunicom.jphospital.controller"))
                .paths(PathSelectors.any())
                .build();

    }

    private ApiInfo buildApiInfo(){
        return new ApiInfoBuilder()
                .title("中国联通武义医院小程序项目api")
                .description("项目可视化api，开发联系人：曹晶 caoj30@chinaunicom.cn")
                .version("1.0")
//                .termsOfServiceUrl("http://10.21.161.23:8080/")
                .build();
    }

}
