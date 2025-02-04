package org.acme.models;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

class PostTest {

    @Test
    void shouldInitializeCreatedAtOnInstantiation() {
        Post post = new Post();

        assertNotNull(post.createdAt, "The createdAt attribute must be initialized");

        LocalDateTime now = LocalDateTime.now();
        assertTrue(post.createdAt.isBefore(now.plusSeconds(1)) && post.createdAt.isAfter(now.minusSeconds(1)),
                "createdAt must be approximately the same time before and after");
    }
}