package me.andrewtinyakov.collaborativedocumentediting.document;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface DocumentRepository extends MongoRepository<
        Document, String> {


}

