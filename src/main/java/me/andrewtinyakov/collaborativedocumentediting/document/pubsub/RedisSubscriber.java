package me.andrewtinyakov.collaborativedocumentediting.document.pubsub;

import com.corundumstudio.socketio.SocketIOServer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.andrewtinyakov.collaborativedocumentediting.document.payload.request.SaveDocumentRequest;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisSubscriber {
    private final RedisMessageListenerContainer container;
    private final SocketIOServer server;
    private final ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void subscribeToRedisChannel() {
        MessageListenerAdapter listenerAdapter = new MessageListenerAdapter(
                (MessageListener) (message, pattern) -> {
                    try {
                        SaveDocumentRequest document =
                                mapper.readValue(new String(message.getBody()),
                                        SaveDocumentRequest.class);
                        server.getRoomOperations(document.getId())
                                .sendEvent("edit", document);
                    } catch (JsonProcessingException e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        container.addMessageListener(listenerAdapter, new PatternTopic("document-updates"));
        container.start();
    }
}
