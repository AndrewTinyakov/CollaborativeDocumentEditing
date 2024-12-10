package me.andrewtinyakov.collaborativedocumentediting.config;

import com.corundumstudio.socketio.SocketIOServer;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SocketIORunner implements ApplicationRunner {

    private final SocketIOServer server;

    @Override
    public void run(ApplicationArguments args) {
        server.start();
    }
}
