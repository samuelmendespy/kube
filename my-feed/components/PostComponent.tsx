import React from "react";
import { View, Text, Image, Button } from "react-native";
import { Post } from "../types/Post";

const PostComponent: React.FC<{ post: Post; onDelete: (id: string) => void; onEdit: (post: Post) => void }> = ({ post, onDelete, onEdit }) => (
  <View style={{ padding: 10, borderBottomWidth: 1 }}>
    <Text>{post.user}</Text>
    <Text>{post.text}</Text>
    {post.image ? <Image source={{ uri: post.image }} style={{ width: 100, height: 100 }} /> : null}
    <Text>{new Date(post.createdAt).toLocaleString()}</Text>
    <Button title="Edit" onPress={() => onEdit(post)} />
    <Button title="Delete" onPress={() => onDelete(post.id)} />
  </View>
);


export default PostComponent;