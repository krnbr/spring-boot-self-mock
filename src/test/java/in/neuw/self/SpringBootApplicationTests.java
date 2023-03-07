package in.neuw.self;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class SpringBootApplicationTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testPong() {
        Pong res = testRestTemplate.getForObject("/v1/upstream", Pong.class);
        assertEquals(true, res.isSuccess());
        assertEquals("pong", res.getMessage());
    }

}
