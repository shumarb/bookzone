/**
 * Parent class for entities sharing common attributes.
 * Maps to the "person" table in the database with a joined inheritance strategy.
 */

package com.bookzone.model;

import jakarta.persistence.*;

@Entity
@Table(name = "person")
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {

    /**
     * Unique identification number of a {@link Person} entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    /**
     * Name of a Person.
     */
    protected String name;

    /**
     * Unique username of a Person.
     */
    @Column(unique = true)
    protected String username;

    /**
     * Unique email address of a Person.
     */
    @Column(unique = true)
    protected String email;

    /**
     * Password of a Person.
     */
    protected String password;

    /**
     * Role of a Person.
     */
    protected String role;

    /**
     * Default no-args constructor as required by Hibernate.
     */
    public Person() {

    }

    /**
     * Constructs a new {@link Person} entity with the specified attributes.
     *
     * @param name          The name of a Person.
     * @param username      The username of a Person.
     * @param email         The email of a Person.
     * @param password      The password of a Person.
     * @param role          The role of a Person.
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

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

}
