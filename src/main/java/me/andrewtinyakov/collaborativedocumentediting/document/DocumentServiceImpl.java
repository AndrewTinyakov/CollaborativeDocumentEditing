package me.andrewtinyakov.collaborativedocumentediting.document;

import com.corundumstudio.socketio.SocketIOClient;
import lombok.RequiredArgsConstructor;
import me.andrewtinyakov.collaborativedocumentediting.document.payload.request.SaveDocumentRequest;
import me.andrewtinyakov.collaborativedocumentediting.document.pubsub.RedisPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {
    private final RedisPublisher redisPublisher;
    private final DocumentRepository documentRepository;

    @Override
    public void edit(SocketIOClient client, SaveDocumentRequest data) {
        Document document = new Document(
                data.getContent()
        );
        if (data.getId() == null) {
            document.setId(UUID.randomUUID().toString());
        }else {
            document.setId(data.getId());
        }
        documentRepository.save(
                document
        );

        data.setId(document.getId());
        redisPublisher.publish(
                data
        );
    }
}
