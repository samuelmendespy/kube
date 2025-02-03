import api from "./api";
import { Post } from "../types/Post";

export const getPosts = async (): Promise<Post[]> => {
  try {
    const response = await api.get<Post[]>("/posts");
    return response.data;
  } catch (error) {
    console.error("Erro ao buscar posts", error);
    throw error;
  }
};

export const createPost = async (post: Omit<Post, "id" | "createdAt">) => {
  try {
    const response = await api.post<Post>("/posts", post);
    return response.data;
  } catch (error) {
    console.error("Erro ao criar post", error);
    throw error;
  }
};

export const deletePost = async (id: string) => {
  try {
    await api.delete(`/posts/${id}`);
  } catch (error) {
    console.error("Erro ao deletar post", error);
    throw error;
  }
};

export const updatePost = async (post: Omit<Post, "user" | "createdAt">) => {
  try {
    const response = await api.put<Post>(`/posts/${post.id}`, post);
    return response.data;
  } catch (error) {
    console.error("Erro ao atualizar post", error);
    throw error;
  }
};