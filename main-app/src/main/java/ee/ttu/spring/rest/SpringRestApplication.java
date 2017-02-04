package ee.ttu.spring.rest;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

import java.sql.SQLException;

@SpringBootApplication
public class SpringRestApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        startH2Server();
        SpringApplication.run(SpringRestApplication.class);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        startH2Server();
        return application.sources(SpringRestApplication.class);
    }

    private static void startH2Server() {
        try {
            Server h2Server = Server.createTcpServer("-tcpPort", "9100", "-tcpAllowOthers").start();
            if (h2Server.isRunning(true)) {
                System.out.println("H2 server was started and is running.");
                System.out.println(h2Server.getURL());
            } else {
                throw new RuntimeException("Could not start H2 server.");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to start H2 server: ", e);
        }
    }
}
