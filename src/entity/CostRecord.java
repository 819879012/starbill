package entity;

import java.util.Date;

public class CostRecord extends CashFlow implements Comparable<CostRecord>{

	private double cost;

	public CostRecord() {
		// TODO Auto-generated constructor stub
	}

	public CostRecord(int id, int uid, int cid, double cost,String comment, Date date) {
		super(id, uid, cid, comment, date);
		this.cost = cost;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	@Override
	public int compareTo(CostRecord o) {
		return (int)(o.getCost() - cost);
	}
	
}
