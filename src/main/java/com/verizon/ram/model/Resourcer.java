package com.verizon.ram.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "resourcesd")
public class Resourcer {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long rid;

	private long uid;

	@Enumerated(EnumType.STRING)
	private RStatus rstatus;

	@NotNull
	private boolean isdeleted;

	@Enumerated(EnumType.STRING)
	private Rtype rtype;

	@NotNull
	private String rname;

	@NotNull
	private String rdesc;

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public RStatus getRstatus() {
		return rstatus;
	}

	public void setRstatus(RStatus rstatus) {
		this.rstatus = rstatus;
	}

	

	public boolean isIsdeleted() {
		return isdeleted;
	}

	public void setIsdeleted(boolean isdeleted) {
		this.isdeleted = isdeleted;
	}

	public Rtype getRtype() {
		return rtype;
	}

	public void setRtype(Rtype rtype) {
		this.rtype = rtype;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRdesc() {
		return rdesc;
	}

	public void setRdesc(String rdesc) {
		this.rdesc = rdesc;
	}

	public Resourcer() {
		super();
	}

	public Resourcer(long rid, long uid, RStatus rstatus, @NotNull boolean isdeleted, Rtype rtype,
			@NotNull String rname, @NotNull String rdesc) {
		super();
		this.rid = rid;
		this.uid = uid;
		this.rstatus = rstatus;
		this.isdeleted = isdeleted;
		this.rtype = rtype;
		this.rname = rname;
		this.rdesc = rdesc;
	}

}
