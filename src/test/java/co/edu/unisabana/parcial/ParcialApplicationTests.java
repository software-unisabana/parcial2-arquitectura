package co.edu.unisabana.parcial;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ActiveProfiles("test")
@SpringBootTest
class ParcialApplicationTests {

    @Test
    void contextLoads() {
        assertDoesNotThrow(() -> ParcialApplication.main(new String[] {}));
    }
}
