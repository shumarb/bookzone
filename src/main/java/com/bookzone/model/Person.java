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

    /**
     * Returns the name of the {@link Person} entity.
     *
     * @return the name of the {@link Person} entity.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the {@link Person} entity.
     *
     * @param name the name to be assigned to the {@link Person} entity.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the {@link Person} entity.
     *
     * @return the name of the {@link Person} entity.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the {@link Person} entity.
     *
     * @param username the username to be assigned to the {@link Person} entity.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Returns the email address of the {@link Person} entity.
     *
     * @return the email address of the {@link Person} entity.
     */
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the password of the {@link Person} entity.
     *
     * @return the password of the {@link Person} entity.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the {@link Person} entity.
     *
     * @param password the role to be assigned to the {@link Person} entity.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Returns the role of the {@link Person} entity.
     *
     * @return the role of the {@link Person} entity.
     */
    public String getRole() {
        return role;
    }

    /**
     * Sets the role of the {@link Person} entity.
     *
     * @param role the role to be assigned to the {@link Person} entity.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * A string representation of a {@link Person} entity.
     *
     * @return A string representation of a {@link Person} entity.
     */
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
