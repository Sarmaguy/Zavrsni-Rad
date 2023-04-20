package zavrsni.webapp.picea;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import zavrsni.webapp.picea.config.WebConfig;

@SpringBootApplication
@RestController
@Import(WebConfig.class)
public class PiceaApplication {

    public static void main(String[] args) {
        SpringApplication.run(PiceaApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

}
