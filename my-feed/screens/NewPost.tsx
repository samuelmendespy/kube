import React, { useState } from 'react';
import { Post } from "../types/Post";
import { View, Button, TextInput } from 'react-native';
import {createPost} from '../services/postService';


const NewPost: React.FC<{ route: any; navigation: any }> = ({ route, navigation }) => {
  const { setPosts } = route.params;
  const [text, setText] = useState('');
  const [image, setImage] = useState('');

  const handleSubmit = async () => {
    const newPost = await createPost({ text, image, user: 'User'});
    setPosts((prevPosts: Post[]) => [...prevPosts, newPost]);
    navigation.goBack();
  };

  return (
    <View>
      <TextInput placeholder="Text" onChangeText={setText} />
      <TextInput placeholder="Image URL" onChangeText={setImage} />
      <Button title="Submit" onPress={handleSubmit} />
    </View>
  );
};

  export default NewPost;