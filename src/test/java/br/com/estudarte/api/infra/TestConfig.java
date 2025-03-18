package br.com.estudarte.api.infra;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@TestConfiguration
public class TestConfig {

    @Bean
    public TestPersistenceHelper testPersistenceHelper(TestEntityManager em) {
        return new TestPersistenceHelper(em);
    }
}
