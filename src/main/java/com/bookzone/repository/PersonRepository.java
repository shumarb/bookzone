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
	 * Finds a {@link Person} entity by email.
	 * 
	 * @param email The email address of the {@link Person} entity to retrieve.
	 * @return 		An Optional containing a {@link Person} entity, or empty if not found.
	 */
	Optional<Person> findByEmail(String email);

	/**
	 * Finds a {@link Person} entity by email.
	 *
	 * @param username 	The username of the {@link Person} entity to retrieve.
	 * @return 			An Optional containing a {@link Person} entity, or empty if not found.
	 */
	Optional<Person> findByUsername(String username);

	/**
	 * Finds a {@link Person} entity by email.
	 *
	 * @param password The password of the {@link Person} entity to retrieve.
	 * @return 			An Optional containing a {@link Person} entity, or empty if not found.
	 */
	Optional<Person> findByPassword(String password);
}
