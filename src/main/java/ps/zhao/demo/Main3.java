package ps.zhao.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ps.zhao.demo.service.PersonService;

import java.util.concurrent.*;

@Component
@Slf4j
@Order(3)
public class Main3 implements CommandLineRunner {

    @Autowired
    private PersonService personService = null;

    @Override
    public void run(String... strings) throws Exception {

        log.debug("in Main 3  ...");

        ExecutorService service = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        Future<?> future = service.submit(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // 在线程中，每隔一秒调用一次service方法
                    // 观察结果，第一次无缓存，走方法内部，以后每次都走缓存
                    // 缓存过期后，再次走入方法内部
                    personService.getPerson();
                    sleep(1000);
                }
            }
        });

        future.get();
        System.exit(0);
    }

    private void sleep(long milisec) {
        try {
            Thread.sleep(milisec);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
