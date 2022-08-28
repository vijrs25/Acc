package com.xadmin.pdfreader;

public class TransactionParameters {
	
    private String refrence;
    private String credited;
    private String debited;
    private String date;
    private String nameRefrence;
    private double currentAmount;
    

	public TransactionParameters(String refrence, String credit, double lastCurrentAmount, double currentAmount) {
		super();
		this.refrence = refrence;
		this.credited = credit;
		this.currentAmount = currentAmount;

		if (lastCurrentAmount > currentAmount) {

			setDebited(this.credited);
			setCredited("0");
			
		} else {
			setDebited("0");
		}
	}

	public String getRefrence() {
		return refrence;
	}

	public String getCredited() {
		return credited;
	}
	
	public String getDebited() {
		return debited;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public String getDate() {
		return date;
	}

	public String getNameRefrence() {
		return nameRefrence;
	}

	
	public void setRefrence(String refrence) {
		this.refrence = refrence;
	}

	public void setCredited(String credited) {
		this.credited = credited;
	}

	public void setDebited(String debited) {
		this.debited = debited;
	}
	
	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}
	
	
}
