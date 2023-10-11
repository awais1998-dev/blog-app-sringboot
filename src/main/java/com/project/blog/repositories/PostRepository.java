package com.project.blog.repositories;

import com.project.blog.models.Category;
import com.project.blog.models.Post;
import com.project.blog.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUser(User user, Pageable pageable);
    Page<Post> findByCategory(Category category, Pageable pageable);
    Page<Post> findByTitleContaining(String q, Pageable pageable);
}
