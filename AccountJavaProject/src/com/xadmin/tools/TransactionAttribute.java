package com.xadmin.tools;

public class TransactionAttribute {

	String date ;
	int sequesnce;
	String refrenceNum ;
	double debit ;
	double credit ;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	String amountLeft ;
	String name;
	
	public TransactionAttribute(String date, int sequesnce, String refrenceNum, double debit, double credit,
			String amountLeft, String name) {
		super();
		this.date = date;
		this.sequesnce = sequesnce;
		this.refrenceNum = refrenceNum;
		this.debit = debit;
		this.credit = credit;
		this.amountLeft = amountLeft;
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getSequesnce() {
		return sequesnce;
	}

	public void setSequesnce(int sequesnce) {
		this.sequesnce = sequesnce;
	}

	public String getRefrenceNum() {
		return refrenceNum;
	}

	public void setRefrenceNum(String refrenceNum) {
		this.refrenceNum = refrenceNum;
	}


	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public String getAmountLeft() {
		return amountLeft;
	}

	public void s(String amountLeft) {
		this.amountLeft = amountLeft;
	}
	
	
}
