package toy.project.coutalk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import toy.project.coutalk.controller.CoutalkController;
import toy.project.coutalk.jpa.repository.CoupangProductInfoRepository;

@EntityScan(basePackages = {"toy.project.coutalk.jpa.domain"})
@SpringBootApplication
public class CoutalkApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoutalkApplication.class, args);
    }

}
