package com.example.reddit.repositories;

import com.example.reddit.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface postRepository extends CrudRepository<Post, Long> {
}
