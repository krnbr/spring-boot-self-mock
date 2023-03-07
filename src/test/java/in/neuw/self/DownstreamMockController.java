package in.neuw.self;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DownstreamMockController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("ping")
    public Pong test() {
        logger.info("for testing only, downstream hosted from the test package's controller");
        return new Pong("pong", true, new Date().getTime(), true);
    }

}
