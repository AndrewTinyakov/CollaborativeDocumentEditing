package me.andrewtinyakov.collaborativedocumentediting.document;

import com.corundumstudio.socketio.SocketIOServer;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import me.andrewtinyakov.collaborativedocumentediting.document.payload.request.SaveDocumentRequest;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class SocketController {
    private final SocketIOServer socketServer;
    private final DocumentService documentService;

    @PostConstruct
    public void postConstruct() {
        socketServer.addEventListener(
                "join",
                String.class,
                (client, documentId, ackSender) -> {
                    client.joinRoom(documentId);
                    System.out.println("Client joined room for document: " + documentId);
                }
        );

        socketServer.addEventListener(
                "edit",
                SaveDocumentRequest.class,
                (client, data, ackRequest) -> documentService.edit(client, data)
        );
    }

}
