package com.optimus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.net.InetAddress;
import java.net.UnknownHostException;


@SpringBootApplication
@Slf4j
public class OptimusApplication {
    private static int port;
    private static String contentPath;

    public static void main(String[] args)  {
        SpringApplication.run(OptimusApplication.class, args);

        log.info("..######..##.....##..######...######..########..######...######.\n" +
                ".##....##.##.....##.##....##.##....##.##.......##....##.##....##\n" +
                ".##.......##.....##.##.......##.......##.......##.......##......\n" +
                "..######..##.....##.##.......##.......######....######...######.\n" +
                ".......##.##.....##.##.......##.......##.............##.......##\n" +
                ".##....##.##.....##.##....##.##....##.##.......##....##.##....##\n" +
                "..######...#######...######...######..########..######...######.");

        System.out.println("start successful");
        log.info("App running at:");
        log.info("- Local:   http://localhost:{}{}", port, contentPath);
        try {
            String ip = InetAddress.getLocalHost().getHostAddress();

            log.info("- Network: http://{}:{}{}", ip, port, contentPath);
        } catch (UnknownHostException e) {
            log.error("get local host fail: {}", e.getMessage());
        }
    }

    @Value("${server.port}")
    public void setPort(int port) {
        OptimusApplication.port = port;
    }

    @Value("${server.servlet.context-path}")
    public void setContentPath(String contentPath) {
        OptimusApplication.contentPath = contentPath;
    }

}
