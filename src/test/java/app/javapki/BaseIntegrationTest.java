package app.javapki;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
    webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT
)
public class BaseIntegrationTest {

    @Value("${local.server.port}")
    int localServerPort;

}
