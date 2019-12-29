package entity;

import java.util.Date;

public class CostPlan {

	private int id;
	private int uid;
	private int cid;
	private double spend;
	private String comment;
	private Date date;
	
	public CostPlan() {
		// TODO Auto-generated constructor stub
	}

	public CostPlan(int id, double spend, int uid, int cid, String comment, Date date) {
		super();
		this.id = id;
		this.spend = spend;
		this.uid = uid;
		this.cid = cid;
		this.comment = comment;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getSpend() {
		return spend;
	}

	public void setSpend(double spend) {
		this.spend = spend;
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
