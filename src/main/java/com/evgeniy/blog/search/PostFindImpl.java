package com.evgeniy.blog.search;

import com.evgeniy.blog.models.Post;
import com.evgeniy.blog.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostFindImpl implements PostFind {

    private PostRepository postRepository;

    @Override
    public Post getByStatus(String status) {
        Post post = postRepository.findByStatus(status);
        return post;
    }
}
