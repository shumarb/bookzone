/**
 * Represents a librarian, extending from the {@link Person} class.
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
     * @param name      The name of the librarian.
     * @param username  The username of the librarian.
     * @param email     The email address of the librarian.
     * @param password  The password of the librarian.
     */
    public Librarian(String name, String username, String email, String password) {
        super(name, username, email, password, "Librarian");
    }

}
