package in.neuw.self;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DownstreamMockController {

    @GetMapping("ping")
    public Pong test() {
        System.out.println("for testing only, downstream hosted from the test package's controller");
        return new Pong("pong", true, new Date().getTime(), true);
    }

}
