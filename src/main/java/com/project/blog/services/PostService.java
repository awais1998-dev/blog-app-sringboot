package com.project.blog.services;

import com.project.blog.models.Category;
import com.project.blog.models.Post;
import com.project.blog.models.User;
import com.project.blog.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostResponse getAllPosts(String q, Integer pageNumber, Integer pageSize, String sortBy, String order);
    PostResponse getAllPostsByUser(Long userId, Integer pageNumber, Integer pageSize, String sortBy, String order);
    PostResponse getAllPostsByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String order);
    Post getPostById(Long id);
    Post createPost(Post post);
    Post updatePost(Post post, Long id);
    void deletePost(Long id);
}
