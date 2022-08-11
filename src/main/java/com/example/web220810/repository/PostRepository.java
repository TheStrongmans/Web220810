package com.example.web220810.repository;

import com.example.web220810.modl.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
