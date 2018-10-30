package com.verizon.ram.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ticketsd")
public class Tickets {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	
	private long tid;
	private String tsub;
	private String tdesc;
	private long uid;
	private long rid;
	
	@Enumerated(EnumType.STRING)
	private TStatus tstatus;

	@Enumerated(EnumType.STRING)
	private Priority tpriority;
	
	@Enumerated(EnumType.STRING)
	private Trtype ttype;
	
//	@Enumerated(EnumType.STRING)
//	private Hardware hware;
//	
//	@Enumerated(EnumType.STRING)
//	private Software soft ;
	

	private String start;// time when ticket was raised
	private String mtime;// approval or rejection timestamp
	private String end;// when ticket was compeltely addressed
	private String mcmnt;
	private String acmnt;

	public long getTid() {
		return tid;
	}

	public void setTid(long tid) {
		this.tid = tid;
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}
	
	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public TStatus getTstatus() {
		return tstatus;
	}

	public void setTstatus(TStatus tstatus) {
		this.tstatus = tstatus;
	}

	public Priority getTpriority() {
		return tpriority;
	}

	public void setTpriority(Priority tpriority) {
		this.tpriority = tpriority;
	}

	public Trtype getTtype() {
		return ttype;
	}

	public void setTtype(Trtype ttype) {
		this.ttype = ttype;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public String getMcmnt() {
		return mcmnt;
	}

	public void setMcmnt(String mcmnt) {
		this.mcmnt = mcmnt;
	}

	public String getAcmnt() {
		return acmnt;
	}

	public void setAcmnt(String acmnt) {
		this.acmnt = acmnt;
	}

	public String getTsub() {
		return tsub;
	}

	public void setTsub(String tsub) {
		this.tsub = tsub;
	}

	public String getTdesc() {
		return tdesc;
	}

	public void setTdesc(String tdesc) {
		this.tdesc = tdesc;
	}

	public String getmtime() {
		return mtime;
	}

	public void setmtime(String mTime) {
		this.mtime = mTime;
	}

	


	public Tickets() {
		super();
	}
	

public Tickets(long tid, String tsub, String tdesc, long uid, long rid, TStatus tstatus, Priority tpriority,
		Trtype ttype, String start, String mTime, String end, String mcmnt, String acmnt) {
	super();
	this.tid = tid;
	this.tsub = tsub;
	this.tdesc = tdesc;
	this.uid = uid;
	this.rid = rid;
	this.tstatus = tstatus;
	this.tpriority = tpriority;
	this.ttype = ttype;
	this.start = start;
	this.mtime = mTime;
	this.end = end;
	this.mcmnt = mcmnt;
	this.acmnt = acmnt;
}
	

	
	

}
