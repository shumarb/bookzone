/**
 * {@link Librarian} class extending from the {@link Person} class.
 * 
 * @author Sheikh Umar
 */

package com.bookzone.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "librarians")
@PrimaryKeyJoinColumn(name = "librarian_id")
public class Librarian extends Person {

    /**
     * Default constructor required by Hibernate.
     */
    public Librarian() {

    }

    /**
     * Contracts a {@link Librarian} with the specified details.
     *
     * @param name      The name of the Librarian.
     * @param username  The username of the Librarian.
     * @param email     The email address of the Librarian.
     * @param password  The password of the Librarian.
     */
    public Librarian(String name, String username, String email, String password) {
        super(name, username, email, password, "Librarian");
    }

}
