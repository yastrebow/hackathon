package ru.yastrebov.hackathon;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = {"default"})
@SpringBootTest
class HackathonApplicationTests {

    @Test
    void contextLoads() {
    }

}
