package models;

import play.data.validation.*;
import javax.persistence.*;
import play.db.ebean.*;
import com.avaje.ebean.*;
import com.avaje.ebean.Model.Finder;

import play.libs.Crypto;
import com.fasterxml.jackson.annotation.*;
import java.util.Date;

@Entity
@Table(name="stamp_raitings")
public class StampRaiting extends Model
{
	@Id
    public long raiting_id;
	
	@ManyToOne
    @Column(name="stamp_id")
    public Stamp stamp_id;
	
	@Column(nullable=false)
	public User user_raiting;
	
	@Column(nullable=false)
	public int raiting_status;
	
	@Column(nullable=false)
	public int stamp_raiting;
	
	@Column(nullable=false)
	public int stamp_comments;
	
	@Column(nullable=false)
	public Date creation_date;
	
	public static Finder<Long,StampRaiting> find = new Finder<Long,StampRaiting>(Long.class, StampRaiting.class);
	
}
