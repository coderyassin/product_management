package org.alten.shared;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.alten.*"})
public class SharedApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharedApplication.class, args);
    }

}
