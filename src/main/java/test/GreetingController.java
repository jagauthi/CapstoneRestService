package test;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    
/*
    In order to get this to run, just go in command line to the project folder
    and run "mvnw spring-boot:run". That will make it run.
    Then you can go to "http://localhost:8080/greeting".
    Another option is go to the project folder and run "mvnw clean package". Then if you go to the project
    folder, go to the target folder, there will be something called
    gs-rest-service-0.1.0.jar   You can run that, and it will do the same as if you ran
    "mvnw spring-boot:run", then you can hit the url previously given.
*/

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(),
                            String.format(template, name));
    }
}
