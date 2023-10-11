package com.project.blog.servicesImpl;

import com.project.blog.exceptions.ResourceNotFound;
import com.project.blog.models.Comment;
import com.project.blog.models.Post;
import com.project.blog.models.User;
import com.project.blog.repositories.CommentRepository;
import com.project.blog.repositories.PostRepository;
import com.project.blog.repositories.UserRepository;
import com.project.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Comment createComment(Comment comment) {
        System.out.println("userId : "+ comment);
        System.out.println("postId : "+ comment.getPost().getId());
        User user = userRepository.findById(comment.getUser().getId()).orElseThrow(
                () -> new ResourceNotFound("User", "Id", comment.getUser().getId())
        );
        Post post = postRepository.findById(comment.getPost().getId()).orElseThrow(
                () -> new ResourceNotFound("Post", "Id", comment.getPost().getId())
        );
        Comment newComment = new Comment();
        newComment.setContent(comment.getContent());
        newComment.setPost(post);
        newComment.setUser(user);
        return commentRepository.save(newComment);
    }

    @Override
    public void deleteComment(Long id) {
       Comment comment = commentRepository.findById(id).orElseThrow(
               () -> new ResourceNotFound("Comment", "Id", id)
       );
       commentRepository.delete(comment);
    }
}
