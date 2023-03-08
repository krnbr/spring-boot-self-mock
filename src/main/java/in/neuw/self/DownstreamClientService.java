package in.neuw.self;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DownstreamClientService {

    private final RestTemplate restTemplate;

    public DownstreamClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Pong getPong() {
        return restTemplate.getForObject("/ping", Pong.class);
    }

}
