/**
 * Parent class for entities sharing common attributes.
 */

package com.bookzone.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    /**
     * Identification number of a {@link Person} entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    /**
     * Name of a {@link Person} entity.
     */
    protected String name;

    /**
     * Username of a {@link Person} entity.
     */
    @Column(unique = true)
    protected String username;

    /**
     * Email address of a {@link Person} entity.
     * This attribute must be unique across all the {@link Person} entities.
     */
    @Column(unique = true)
    protected String email;

    /**
     * Password of a {@link Person} entity.
     * This attribute must be unique across all the {@link Person} entities.
     */
    @Column(unique = true)
    protected String password;

    /**
     * Role of a {@link Person} entity.
     * This attribute must be unique across all the {@link Person} entities.
     */
    protected String role;

    /**
     * Default no-args constructor as required by Hibernate.
     */
    public Person() {

    }

    /**
     * Constructs a new {@link Person} entity with each attribute assigned to its corresponding provided values.
     *
     * @param name          The name of the Person entity.
     * @param username      The username of the Person entity.
     * @param email         The email of the Person entity.
     * @param password      The password of the Person entity.
     * @param role          The role of the Person entity.
     */
    public Person(String name, String username, String email, String password, String role) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
