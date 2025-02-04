package org.acme.resources;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import org.acme.dtos.PostCreateDTO;
import org.acme.dtos.PostUpdateDTO;
import org.acme.models.Post;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.List;

@Path("/posts")
public class PostResource {

    @GET
    public List<Post> getPosts() {
        return Post.listAll();
    }

    @POST
    public Response createPost(PostCreateDTO postCreateDTO) {
        if (postCreateDTO.text == null || postCreateDTO.image == null || postCreateDTO.user == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Post is null").build();
        }

        Post post = new Post();
        post.text = postCreateDTO.text;
        post.image = postCreateDTO.image;
        post.user = postCreateDTO.user;

        post.persist();
        return Response.status(Response.Status.CREATED).entity(post).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePost(@PathParam("id") String id, PostUpdateDTO postUpdateDTO) {
        Bson filter = Filters.eq("_id", new ObjectId(id));

        Bson update = Updates.combine(
                Updates.set("text", postUpdateDTO.text),
                Updates.set("image", postUpdateDTO.image),
                Updates.set("user", postUpdateDTO.user)
        );

        long modifiedCount = Post.mongoCollection().updateOne(filter, update).getModifiedCount();

        if (modifiedCount == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletePost(@PathParam("id") String id) {
        Post existingPost = Post.findById(new ObjectId(id));
        if (existingPost == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        existingPost.delete();
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}