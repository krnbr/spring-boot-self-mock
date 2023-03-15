package in.neuw.self;

import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class DownstreamMockController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("ping")
    public ResponseEntity test(@RequestParam(value = "message", defaultValue = "Hello-World") final String message,
                                    HttpServletResponse response) {

        ResponseEntity responseEntity = new ResponseEntity(new Pong("pong", true, new Date().getTime(), true), HttpStatus.OK);
        logger.info("mock received downstream message = {}", message);

        if (message.equals("text-html")) {
            logger.info("mock tex-html scenario");
            response.addHeader("content-type", "text/html");
            responseEntity = new ResponseEntity("<html></html>", HttpStatus.BAD_GATEWAY);
        } else if (message.equals("404")) {
            logger.info("mock 404 scenario");
            responseEntity = new ResponseEntity(new Pong("pong-"+message, false, new Date().getTime(), true), HttpStatus.NOT_FOUND);
        } else if (message.equals("429")) {
            logger.info("mock 429 scenario");
            responseEntity = new ResponseEntity(new Pong("pong-"+message, false, new Date().getTime(), true), HttpStatus.TOO_MANY_REQUESTS);
        } else if (!message.toLowerCase().equals("hello-world")){
            // in this case the response is OK, like when the downstream is sending us OK, html but not JSON.
            logger.info("mock anything else scenario");
            responseEntity = new ResponseEntity("<html></html>", HttpStatus.OK);
        }

        logger.info("for testing only, downstream hosted from the test package's controller");
        return responseEntity;
    }

}
