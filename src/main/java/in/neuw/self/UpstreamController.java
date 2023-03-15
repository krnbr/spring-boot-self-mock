package in.neuw.self;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpstreamController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DownstreamClientService downstreamClientService;

    public UpstreamController(DownstreamClientService downstreamClientService) {
        this.downstreamClientService = downstreamClientService;
    }

    @GetMapping("/v1/upstream")
    public Pong test(@RequestParam(value = "message", defaultValue = "Hello-World") final String message,
                     HttpServletResponse response) {
        logger.info("upstream url called, with message = {}", message);
        return downstreamClientService.getPong(message, response);
    }

}
