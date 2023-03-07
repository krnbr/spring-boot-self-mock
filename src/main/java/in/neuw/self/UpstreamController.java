package in.neuw.self;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@RestController
public class UpstreamController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/v1/upstream")
    public Pong test() {
        return restTemplate.getForObject("/ping", Pong.class);
    }

}
