package com.cyfire.likes;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LikeService {

    @Autowired
    private LikeRepository repo;

    public List<Likes> getLikesList() {
        return repo.findAll();
    }
}
