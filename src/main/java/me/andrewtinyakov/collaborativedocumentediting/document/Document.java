package me.andrewtinyakov.collaborativedocumentediting.document;

import lombok.*;
import org.springframework.data.annotation.Id;

@org.springframework.data.mongodb.core.mapping.Document(collection = "documents")
@ToString
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    @Id
    private String id;
    private String userId;
    private String content;

    public Document(String content) {
        this.content = content;
    }
}
