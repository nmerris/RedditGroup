package com.example.reddit.repositories;

import com.example.reddit.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
    Iterable<Post> findAllByScreenNameIs(String sname);
}
