package in.neuw.self;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Service
public class DownstreamClientService {

    private final RestTemplate restTemplate;

    public DownstreamClientService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Pong getPong(String message, HttpServletResponse response) {
        try {
            return restTemplate.getForObject("/ping?message={message}", Pong.class, message);
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode().value() == HttpStatus.NOT_FOUND.value()) {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                return new Pong(message, false, new Date().getTime(), true);
            } else {
                // the scenarios we are unaware of
                // can override obviously
                throw e;
            }
        } catch (HttpServerErrorException e) {
            // can override obviously
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            throw e;
        } catch (Exception e) {
            // can override obviously
            throw e;
        }
    }

}
