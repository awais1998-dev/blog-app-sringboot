package com.project.blog.controllers;

import com.project.blog.config.AppConstants;
import com.project.blog.models.Post;
import com.project.blog.payload.PostResponse;
import com.project.blog.services.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @RequestParam(value = "q", required = false) String q,
            @RequestParam(value = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER, required = false) Integer pageNumber,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.PAGE_SIZE, required = false) Integer pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.SORT_BY, required = false) String sortBy,
            @RequestParam(value = "order", defaultValue = AppConstants.ORDER, required = false) String order
    ){
        return new ResponseEntity<PostResponse>(postService.getAllPosts(q, pageNumber, pageSize, sortBy, order), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<PostResponse> getAllPostsByUser(@PathVariable Long id,
                                                        @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                        @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                          @RequestParam(value = "sortBy", defaultValue = "dateCreated", required = false) String sortBy,
                                                          @RequestParam(value = "order", defaultValue = "desc", required = false) String order
                                                        ){
        return new ResponseEntity<PostResponse>(postService.getAllPostsByUser(id, pageNumber, pageSize, sortBy, order), HttpStatus.OK);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<PostResponse> getAllPostsByCategory(@PathVariable Long id,
                                                              @RequestParam(value = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
                                                              @RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
                                                              @RequestParam(value = "sortBy", defaultValue = "dateCreated", required = false) String sortBy,
                                                              @RequestParam(value = "order", defaultValue = "desc", required = false) String order
    ){
        return new ResponseEntity<PostResponse>(postService.getAllPostsByCategory(id, pageNumber, pageSize, sortBy, order), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@Valid @RequestBody Post post){
        return new ResponseEntity<Post>(postService.createPost(post), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@Valid @RequestBody Post post, @PathVariable Long id){
        return new ResponseEntity<Post>(postService.updatePost(post, id), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id){
        return new ResponseEntity<Post>(postService.getPostById(id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
