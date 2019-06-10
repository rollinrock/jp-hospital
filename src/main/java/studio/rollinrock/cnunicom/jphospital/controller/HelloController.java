package studio.rollinrock.cnunicom.jphospital.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import studio.rollinrock.cnunicom.wrappers.HttpResult;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping(value = "/greet")
    public HttpResult<String> greet() {
        return HttpResult.succeedWithData("hello u.");
    }

}
