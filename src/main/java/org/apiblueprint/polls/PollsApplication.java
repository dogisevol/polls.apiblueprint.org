package org.apiblueprint.polls;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;


@SpringBootApplication
@Configuration
public class PollsApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(PollsApplication.class)
                .run(args);
    }
}
