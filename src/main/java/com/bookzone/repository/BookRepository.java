/**
 * Repository interface for managing {@link Book} entities.
 */

package com.bookzone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookzone.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	/**
	 * Finds a {@link Book} by its unique identification number.
	 * 
	 * @param id 	The unique identification number of a {@link Book} entity.
	 * @return 		An {@link Optional} containing the {@link Book} entity if found, or empty if not found.
	 */
	Optional<Book> findById(Long id);
}
