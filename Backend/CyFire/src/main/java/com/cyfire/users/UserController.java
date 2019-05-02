
package com.cyfire.users;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cyfire.code.CodeGenerator;

@RestController
public class UserController
{
	@Autowired
	public UserRepository usersRepository;

	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	static
	{
		System.out.println("\nCyFire: Ready to save new user...\n");
	}

	/**
	 * Saves a new user into the database.
	 * 
	 * @param user - The user being added.
	 * @return - The Net-ID of the user being added.
	 */
	@RequestMapping(method = RequestMethod.POST, path = "/users/new", produces = "application/json")
	public String saveUser(@RequestBody User user)
	{ // usersRepository.save(user.getFirst_name());
		user.setCode(new CodeGenerator().sevenDigit());
		usersRepository.save(user);

		System.out.println("\n" + user.getNetID() + " has been successfully saved. \n");
		return user.getNetID();
	}

	/**
	 * Gets a list of all the users in the database.
	 * 
	 * @return - A list of all the users in the database.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/users")
	public List<User> getAllUsers()
	{
		logger.info("Entered into Controller Layer");
		List<User> results = usersRepository.findAll();
		logger.info("Number of Records Fetched:" + results.size());
		return results;
	}

	/**
	 * Very important "helper" method. Finds the respective user corresponding to
	 * the NetID being entered in.
	 * 
	 * @param net_id - The NetID of the user we are looking for.
	 * @return - The user corresponding to the NetID, assuming that it exists.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/users/{net_id}")
	public User findUserByNetID(@PathVariable("net_id") String net_id)
	{
		logger.info("Entered into Controller Layer");
		List<User> allUsers = usersRepository.findAll();
		User user = new User();

		for (int i = 0; i < allUsers.size(); i++)
		{
			if (allUsers.get(i).getNetID().equals(net_id))
			{
				user = allUsers.get(i);
				break;
			}
		}
		System.out.println(
				"\nUser associated with net-id " + net_id + "is : " + user.getFirstName() + " " + user.getLastName());

		return user;
	}

	/**
	 * Primarily for testing purposes. Gets the 'code' column from the respective
	 * user.
	 * 
	 * @param net_id - The NetID of the user who we're checking the code from.
	 * @return userCode - The code of the user.
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/users/{code}")
	public Integer getCodeByNetID(@PathVariable("net_id") String net_id)
	{
		logger.info("Entered into Controller Layer");
		User user = findUserByNetID(net_id);
		return user.getCode();
	}

	/**
	 * When the user enters the code on the app and hits submit, it will both enter
	 * the entered_code into the database and check if the entered_code matches the
	 * code. If so, then the user is "verified".
	 * 
	 * @param entered_code - The code (received through email) that is entered in by
	 *                     the user.
	 * @param net_id       - The NetID of the user entering the code.
	 */
	@RequestMapping(method = RequestMethod.POST, path = "users/{entered_code}")
	public void setUserEnteredCode(@RequestBody Integer entered_code, @PathVariable("net_id") String net_id)
	{
		User user = findUserByNetID(net_id);
		user.setEnteredCode(entered_code);
		user.setVerified(user.getEnteredCode() == user.getCode());
		usersRepository.save(user);
	}

}
