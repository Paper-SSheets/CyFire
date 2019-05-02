package com.cyfire.likes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class LikeService
{

	@Autowired
	private LikeRepository repo;

	public List<Likes> getLikesList()
	{
		return repo.findAll();
	}
}
