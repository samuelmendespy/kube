package org.acme.models;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import java.time.LocalDateTime;

public class Post extends PanacheMongoEntity {
    public String text;
    public String image;
    public String user;
    public LocalDateTime createdAt;
}
