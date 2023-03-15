package in.neuw.self;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SpringBootApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testPongPostiveScenario() {
        Pong res = testRestTemplate.getForObject("/v1/upstream", Pong.class);
        assertEquals(true, res.isSuccess());
        assertEquals("pong", res.getMessage());
    }

    @Test
    void testPongNotKnownScenario() {
        ResponseEntity res = testRestTemplate.getForEntity("/v1/upstream?message={message}", Object.class, "not-json");
        assertEquals(true, res.getStatusCode().is5xxServerError());
    }

    @Test
    void testPongHtmlResponse() {
        // this is upstream call
        ResponseEntity res = testRestTemplate.getForEntity("/v1/upstream?message={message}", Object.class, "text-html");
        assertEquals(true, res.getStatusCode().is5xxServerError());
    }

    @Test
    void testPongDownstreamNegative404() {
        String message = "404";
        ResponseEntity res = testRestTemplate.getForEntity("/v1/upstream?message={message}", Object.class, message);
        assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());
    }

    @Test
    void testPongDownstreamNegative429() {
        String message = "429";
        ResponseEntity res = testRestTemplate.getForEntity("/v1/upstream?message={message}", Object.class, message);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());
    }

}
