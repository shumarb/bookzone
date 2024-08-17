/**
 * Repository interface for managing {@link Person} entities.
 */

package com.bookzone.repository;

import java.util.Optional;

import com.bookzone.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookzone.model.Librarian;

@Repository
public interface PersonRepository extends JpaRepository<Librarian, Long> {
	
	/**
	 * Finds a {@link Person} entity by its email address.
	 * 
	 * @param email The email address of the {@link Person} entity to retrieve.
	 * @return 		An Optional containing a {@link Person} entity, or empty if not found.
	 */
	Optional<Person> findByEmail(String email);

	/**
	 * Finds a {@link Person} entity by its username.
	 *
	 * @param username 	The username of the {@link Person} entity to retrieve.
	 * @return 			An Optional containing a {@link Person} entity, or empty if not found.
	 */
	Optional<Person> findByUsername(String username);

}
