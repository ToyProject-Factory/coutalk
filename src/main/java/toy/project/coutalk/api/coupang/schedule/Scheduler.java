package toy.project.coutalk.api.coupang.schedule;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import toy.project.coutalk.api.coupang.service.CoupangService;

@Component
@RequiredArgsConstructor
public class Scheduler {
    final CoupangService coupangService;

    /* 키워드 크롤링 스케줄러 - 매 정각 마다 실행 */
    @Scheduled(cron = "0 0 * * * *")
    public void scheduleTask() {
        coupangService.autoProductClollect();
    }

}
