package me.andrewtinyakov.collaborativedocumentediting.document.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class SaveDocumentRequest {
    private String id;
    private String content;
}
