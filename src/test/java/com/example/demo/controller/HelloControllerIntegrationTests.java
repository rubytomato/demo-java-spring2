package com.example.demo.controller;

import com.example.demo.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 結合テスト
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(HelloControllerIntegrationTests.TestConfig.class)
public class HelloControllerIntegrationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private MediaType contentType = new MediaType(MediaType.TEXT_PLAIN.getType(),
            MediaType.TEXT_PLAIN.getSubtype(), Charset.forName("utf8"));

    @Test
    public void greeting() {
        ResponseEntity<String> result = testRestTemplate.getForEntity("/hello/world", String.class);

        assertThat(result).isNotNull();
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getHeaders().getContentType()).isEqualTo(contentType);
        assertThat(result.getBody()).isEqualTo("hello world");
    }

    @TestConfiguration
    public static class TestConfig {
        @Bean
        public DataSource datasource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        }
    }

/*
    @SpringBootApplication(scanBasePackages = {"com.example.demo"})
    // don't work
    @EnableAutoConfiguration(
        exclude = {
                DataSourceAutoConfiguration.class,
                DataSourceTransactionManagerAutoConfiguration.class,
                HibernateJpaAutoConfiguration.class,
                JpaRepositoriesAutoConfiguration.class
        }
    )
    public static class TestAppConfigure {
        public static void main(String[] args) {
            SpringApplication.run(TestAppConfigure.class, args);
        }
        @Bean
        public DataSource datasource() {
            return new EmbeddedDatabaseBuilder()
                    .setType(EmbeddedDatabaseType.H2)
                    .build();
        }
    }
*/
}
