import React, { useState, useEffect } from 'react';
import { Post } from "../types/Post";
import PostComponent from '../components/PostComponent';
import { View, Button, FlatList } from 'react-native';
import { getPosts, deletePost } from '../services/postService';

const Feed: React.FC<{ navigation: any }> = ({ navigation }) => {
  const [posts, setPosts] = useState<Post[]>([]);

  useEffect(() => {
    getPosts().then(setPosts);
  }, []);

  const handleDelete = async (id: string) => {
    await deletePost(id);
    setPosts(posts.filter(post => post.id !== id));
  };

  const handleEdit = (post: Post) => {
    navigation.navigate('EditPost', { post, setPosts });
  };

  return (
    <View>
      <Button title="New Post" onPress={() => navigation.navigate('NewPost', { setPosts })} />
      <FlatList
        data={posts}
        keyExtractor={item => item.id}
        renderItem={({ item }) => <PostComponent post={item} onDelete={handleDelete} onEdit={handleEdit} />}
      />
    </View>
  );
};

export default Feed;