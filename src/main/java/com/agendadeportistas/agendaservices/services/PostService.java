package com.agendadeportistas.agendaservices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agendadeportistas.agendaservices.repositories.PostRepository;

@Service
public class PostService {

    PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;    
    }
}
