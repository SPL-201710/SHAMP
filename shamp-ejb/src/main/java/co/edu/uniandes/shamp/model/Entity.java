package co.edu.uniandes.shamp.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@MappedSuperclass
public class Entity {

    protected static final String PREFIX = "co.sabit.srt.model.";

    @Column(name = "active", nullable = false)
    protected Boolean active;

    @Column(name = "creation_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;

    @NotEmpty
    @Column(name = "name", nullable = false, length = 300)
    protected String name;

    public Entity() {
        this.active = true;
        this.creationDate = new Date();
    }

    public Boolean getActive() {
        return this.active;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public String getName() {
        return this.name;
    }

    public void setActive(final Boolean active) {
        this.active = active;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = creationDate;
    }

    public void setName(final String name) {
        this.name = name;
    }

}
