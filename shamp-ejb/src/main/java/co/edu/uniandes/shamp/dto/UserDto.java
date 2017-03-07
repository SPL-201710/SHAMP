package co.edu.uniandes.shamp.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UserDto {

    private String email;
    private String name;
    private String surname;
    private Long tenantId;
    private String username;

    public String getEmail() {
        return this.email;
    }

    public String getName() {
        return this.name;
    }

    public String getSurname() {
        return this.surname;
    }

    public Long getTenantId() {
        return this.tenantId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public void setTenantId(final Long tenantId) {
        this.tenantId = tenantId;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

}