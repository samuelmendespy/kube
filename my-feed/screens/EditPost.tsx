import React, { useState } from 'react';
import { Post } from "../types/Post";
import { View, Button, TextInput } from 'react-native';
import { updatePost } from '../services/postService';

const EditPost: React.FC<{ route: any; navigation: any }> = ({ route, navigation }) => {
  const { post, setPosts } = route.params;
  const [text, setText] = useState(post.text);
  const [image, setImage] = useState(post.image);

  const handleSave = async () => {
    const editedPost = {
      id: post.id,
      text: text,
      image: image,
    }
    const updatedPost = await updatePost(editedPost);
    setPosts((prevPosts: Post[]) => prevPosts.map(p => (p.id === post.id ? updatedPost : p)));
    navigation.goBack();
  };

  return (
    <View>
      <TextInput value={text} onChangeText={setText} />
      <TextInput value={image} onChangeText={setImage} />
      <Button title="Save" onPress={handleSave} />
    </View>
  );
};

export default EditPost;