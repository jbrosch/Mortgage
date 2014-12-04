package main;

public class calculator {
	
	public static double PV(double payment, double interest, int years) {
	    //return payment * (Math.pow((1 + interest/12), 12*years)-1)/(interest/12);
		interest = interest/12;
		years = years*12;
		//return payment*Math.pow((1 + interest/12), 12*years);
		return (payment/interest)*(1-Math.pow(1+interest, -years));
	}
	
	public static double houseOnly(double income){
		return (income/12)*.18;
	}
	
	public static double houseObligations(double income, double monthlyDebt){
		return ((income/12)*.36) - monthlyDebt;
	}
	
	public static double allowedPayment(double income, double monthlyDebt){
		return Math.min(houseOnly(income), houseObligations(income,monthlyDebt));
	}
	
}

