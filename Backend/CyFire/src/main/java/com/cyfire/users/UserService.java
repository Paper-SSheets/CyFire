package com.cyfire.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Steven Marshall Sheets
 *
 */
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	public List<User> getUserList()
	{
		return userRepository.findAll();
	}
}
