package com.project.blog.servicesImpl;

import com.project.blog.exceptions.ResourceNotFound;
import com.project.blog.models.Category;
import com.project.blog.models.Post;
import com.project.blog.models.User;
import com.project.blog.payload.PostResponse;
import com.project.blog.repositories.CategoryRepository;
import com.project.blog.repositories.PostRepository;
import com.project.blog.repositories.UserRepository;
import com.project.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public PostResponse getAllPosts(String q, Integer pageNumber, Integer pageSize, String sortBy, String order) {
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost;
        if(q != null){
            pagePost = postRepository.findByTitleContaining(q, pageable);
        }
        else {
            pagePost = postRepository.findAll(pageable);
        }
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(pagePost.getContent());
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastpage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostResponse getAllPostsByUser(Long userId, Integer pageNumber, Integer pageSize, String sortBy, String order) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFound("User", "Id", userId)
        );
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepository.findByUser(user,pageRequest);
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(pagePost.getContent());
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastpage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public PostResponse getAllPostsByCategory(Long categoryId, Integer pageNumber, Integer pageSize, String sortBy, String order) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new ResourceNotFound("Category", "Id", categoryId)
        );
        Sort sort = order.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page<Post> pagePost = postRepository.findByCategory(category, pageRequest);
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(pagePost.getContent());
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastpage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public Post getPostById(Long id) {
        return postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Post", "Id", id)
        );
    }

    @Override
    public Post createPost(Post post) {
        User user = userRepository.findById(post.getUser().getId()).orElseThrow(
                () -> new ResourceNotFound("User", "Id", post.getUser().getId())
        );
        Category category = categoryRepository.findById(post.getCategory().getId()).orElseThrow(
                () -> new ResourceNotFound("Category", "Id", post.getCategory().getId())
        );
        Post newPost = new Post();
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setImageName(post.getImageName());
        newPost.setCategory(category);
        newPost.setUser(user);
        return postRepository.save(newPost);
    }

    @Override
    public Post updatePost(Post post, Long id) {
        Post oldPost = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Post", "Id", id)
        );
        User user = userRepository.findById(post.getUser().getId()).orElseThrow(
                () -> new ResourceNotFound("User", "Id", post.getUser().getId())
        );
        Category category = categoryRepository.findById(post.getCategory().getId()).orElseThrow(
                () -> new ResourceNotFound("Category", "Id", post.getCategory().getId())
        );
        Post newPost = new Post();
        oldPost.setTitle(post.getTitle());
        oldPost.setContent(post.getContent());
        oldPost.setImageName(post.getImageName());
        oldPost.setCategory(category);
        oldPost.setUser(user);
        return postRepository.save(oldPost);
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Post", "Id", id)
        );
        postRepository.delete(post);
    }
}
