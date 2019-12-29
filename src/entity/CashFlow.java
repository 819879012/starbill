package entity;

import java.util.Date;

public class CashFlow {
	
	private int id;
	private int uid;
	private int cid;
	private String comment;
	private Date date;
	
	public CashFlow()
	{
		
	}
	
	public CashFlow(int id, int uid, int cid, String comment, Date date) {
		this.id = id;
		this.cid = cid;
		this.uid = uid;
		this.comment = comment;
		this.date = date;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getCid() {
		return cid;
	}
	
	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public String getComment() {
		return comment;
	}
	
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
