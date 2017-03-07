package co.edu.uniandes.shamp.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@javax.persistence.Entity
@NamedQueries({
        @NamedQuery(name = User.FIND_BY_USERNAME,
                query = "select u from User u where u.username = :username"),
        @NamedQuery(name = User.FIND_BY_USERNAME_AND_PASSWORD,
                query = "select u from User u where u.username = :username and u.password = :password"),})
@Table(name = "\"user\"")
public class User extends Entity {

    public static final String FIND_BY_USERNAME = PREFIX + "User.findByUsername";

    public static final String FIND_BY_USERNAME_AND_PASSWORD =
            PREFIX + "User.findByUsernameAndPassword";

    @Column(name = "email", nullable = false)
    private String email;

    @Id
    @SequenceGenerator(name = "seq_user_id", sequenceName = "seq_user_id", allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_user_id")
    private Long id;

    @NotEmpty
    @Column(name = "password", nullable = false)
    private String password;

    @NotEmpty
    @Column(name = "surname")
    private String surname;

    @NotEmpty
    @Column(name = "username", unique = true, nullable = false)
    private String username;

    public String getEmail() {
        return this.email;
    }

    public Long getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getUsername() {
        return this.username;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

}
