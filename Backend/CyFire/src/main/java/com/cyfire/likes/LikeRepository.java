package com.cyfire.likes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Likes, String>
{

	Likes getByNetId(String net_id);

	void delete(Likes like);

}
