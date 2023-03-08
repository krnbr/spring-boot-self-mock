package in.neuw.self;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpstreamController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DownstreamClientService downstreamClientService;

    public UpstreamController(DownstreamClientService downstreamClientService) {
        this.downstreamClientService = downstreamClientService;
    }

    @GetMapping("/v1/upstream")
    public Pong test() {
        logger.info("upstream url called");
        return downstreamClientService.getPong();
    }

}
