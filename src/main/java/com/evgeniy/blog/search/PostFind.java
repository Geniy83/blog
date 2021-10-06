package com.evgeniy.blog.search;

import com.evgeniy.blog.models.Post;

import java.util.List;

public interface PostFind {
    Post getByStatus (String status);
}
