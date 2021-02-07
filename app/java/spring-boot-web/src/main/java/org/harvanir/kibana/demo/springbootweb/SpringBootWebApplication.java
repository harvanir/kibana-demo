package org.harvanir.kibana.demo.springbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RouterFunctions.route;
import static org.springframework.web.servlet.function.ServerResponse.ok;

/**
 * @author Harvan Irsyadi
 */
@SpringBootApplication
public class SpringBootWebApplication {

    private static final String HELLO = "hello";

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);
    }

    @Bean
    public RouterFunction<ServerResponse> helloRouter() {
        return route(GET("/hello-routers"), request -> ok().body(HELLO));
    }

    @RestController
    static class HelloController {

        @GetMapping("/hello-1")
        public ResponseEntity<String> hello1() {
            return ResponseEntity.ok().body(HELLO);
        }

        @GetMapping(value = "/hello-2", produces = MediaType.TEXT_PLAIN_VALUE)
        public ResponseEntity<String> hello2() {
            return ResponseEntity.ok().body(HELLO);
        }

        @ResponseStatus(HttpStatus.OK)
        @GetMapping(value = "/hello-3", produces = MediaType.TEXT_PLAIN_VALUE)
        public String hello3() {
            return HELLO;
        }

        @ResponseStatus(HttpStatus.OK)
        @GetMapping(value = "/hello-4", produces = MediaType.TEXT_PLAIN_VALUE)
        public byte[] hello4() {
            return HELLO.getBytes();
        }

        @GetMapping("/hello-5")
        public void hello5(HttpServletResponse response) throws IOException {
            response.setStatus(HttpStatus.OK.value());
            response.getWriter().println(HELLO);
        }
    }
}