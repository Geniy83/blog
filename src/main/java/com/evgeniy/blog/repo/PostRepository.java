package com.evgeniy.blog.repo;

import com.evgeniy.blog.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {
}
