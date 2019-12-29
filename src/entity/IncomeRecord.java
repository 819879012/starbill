package entity;

import java.util.Date;

public class IncomeRecord extends CashFlow implements Comparable<IncomeRecord>{

	private double income;
	
	public IncomeRecord() {
		// TODO Auto-generated constructor stub
	}

	public IncomeRecord(int id, int uid, int cid, double income,String comment, Date date) {
		super(id, uid, cid, comment, date);
		this.income = income;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	@Override
	public int compareTo(IncomeRecord o) {
		return (int)(o.getIncome() - income);
	}

	
}
