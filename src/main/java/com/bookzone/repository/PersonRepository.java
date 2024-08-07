/**
 * Repository interface for managing {@link Person} entities.
 */

package com.bookzone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookzone.model.Librarian;

@Repository
public interface PersonRepository extends JpaRepository<Librarian, Long> {
	
	/**
	 * Retrieves a Librarian based on email
	 * 
	 * @param email of the Librarian to retrieve
	 * @return an Optional of type Librarian that matches the given email,
	 * or empty if no Librarian with the given email exists
	 */
	Optional<Librarian> findByEmail(String email);
}
