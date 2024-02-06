package toy.project.coutalk.api.coupang.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import toy.project.coutalk.api.coupang.service.CoupangService;

@SpringBootApplication
@EnableScheduling
public class KeywordScheduling {
    public static void main(String[] args) {
        SpringApplication.run(KeywordScheduling.class, args);
    }
}

