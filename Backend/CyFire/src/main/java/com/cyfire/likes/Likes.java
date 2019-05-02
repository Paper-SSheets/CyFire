package com.cyfire.likes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.core.style.ToStringCreator;
import javax.persistence.Id;

@Entity
@Table(name = "likes")

public class Likes
{

	@Id
	@Column(name = "net_id")
	@NotFound(action = NotFoundAction.IGNORE)
	private String netId;

	@Column(name = "likes")
	@NotFound(action = NotFoundAction.IGNORE)
	private String likes;

	public Likes(String net_id, String likes)
	{
		this.netId = net_id;
		this.likes = likes;
	}

	public Likes()
	{
		this.netId = "";
		this.likes = "";
	}

	@Override
	public String toString()
	{
		return new ToStringCreator(this).append("net_id: ", this.getNet_id()).append("likes: ", this.getLikes())
				.toString();
	}

	public String getLikes()
	{
		return this.likes;
	}

	public void addFirstLike(String net_id)
	{
		this.likes = net_id;
	}

	public void addLike(String net_id)
	{
		this.likes += "," + net_id;
	}

	public void setNet_id(String net_id)
	{
		this.netId = net_id;
	}

	public String getNet_id()
	{
		return this.netId;
	}

}
