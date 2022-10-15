package com.example.Estate_Twin.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.util.StringUtils;

import javax.annotation.*;
import java.io.*;
import java.net.*;
import java.util.Objects;
import redis.embedded.RedisServer;

@Configuration
@Profile("test")
public class EmbeddedRedisConfig {
    @Value("${spring.redis.port}")
    private int redisPort;

    private RedisServer redisServer;

    @PostConstruct
    public void redisServer() throws IOException, URISyntaxException {
        int port = isRedisRunning() ? findAvailablePort() : redisPort;

        if(isArmMac()) {
            redisServer = new RedisServer(getRedisFileForArmMac(),port);
        } else {
            redisServer = new RedisServer(port);
        }
        redisServer.start(); // Redis 시작
    }

    private File getRedisFileForArmMac() throws URISyntaxException {
        URL resource = getClass()
                .getClassLoader()
                .getResource("binary/redis/redis-server-6.2.5-mac-arm64");

        assert resource != null;
        File file = new File(resource.toURI());
        file.setExecutable(true);
        return file;
    }

    private boolean isArmMac() {
        return Objects.equals(System.getProperty("os.arch"),"aarch64")
                && Objects.equals(System.getProperty("os.name"), "Mac OS X");
    }

    @PreDestroy
    public void stopRedis() {
        if(redisServer != null) {
            redisServer.stop(); // Redis 종료
        }
    }

    private boolean isRedisRunning() throws IOException {
        return isRunning(executeGrepProcessCommand(redisPort));
    }

    private int findAvailablePort() throws IOException {
        for (int port = 10000; port <= 65535; port++) {
            Process process = executeGrepProcessCommand(port);
            if(!isRunning(process)) {
                return port;
            }
        }
        throw new IllegalArgumentException(
                "[EMBEDDED-REDIS] Not Found Available port: 10000 ~ 65535"
        );
    }

    private Process executeGrepProcessCommand(int port) throws IOException {
        if(System.getProperty("os.name").startsWith("Windows")) {
            String command = String.format("netstat -nat | findstr LISTEN | findstr %d", port);
            String[] shell = {"cmd.exe","/c",command};
            return Runtime.getRuntime().exec(shell);
        }
        String command = String.format("netstat -nat | grep LISTEN | grep %d", port);
        String[] shell = {"/bin/sh","-c",command};
        return Runtime.getRuntime().exec(shell);
    }

    private boolean isRunning(Process process) {
        String line;
        StringBuilder pidInfo = new StringBuilder();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            while((line = input.readLine())!= null) {
                pidInfo.append(line);
            }
        } catch (Exception ignored) {

        }
        return !StringUtils.isEmpty(pidInfo.toString());
    }

}
