
package com.cyfire.users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository class for <code>User</code> domain objects All method names are
 * compliant with Spring Data naming conventions so this interface can easily be
 * extended for Spring Data See here:
 * 
 * http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 *
 * @author Steven Marshall Sheets
 */
@Repository
public interface UserRepository extends JpaRepository<User, String>
{
	/**
	 * Finds the user associated with the given NetID
	 * @param net_id - The netID associated with the user.
	 * @return - User associated with given NetID
	 */
	User findUserByNetID(String net_id);

	/**
	 * Deletes a user from the database.
	 */
	void delete(User user);
}
