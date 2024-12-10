package me.andrewtinyakov.collaborativedocumentediting.document;

import com.corundumstudio.socketio.SocketIOClient;
import me.andrewtinyakov.collaborativedocumentediting.document.payload.request.SaveDocumentRequest;

public interface DocumentService {
    void edit(SocketIOClient client, SaveDocumentRequest data);
}
