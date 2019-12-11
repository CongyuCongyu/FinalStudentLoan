package pkgLogic;

import java.time.LocalDate;

public class Payment {
	
	//TODO: I've accounted for PaymentNbr, you need to add all the other fields for the class
	private int PaymentNbr;
    private LocalDate DueDate;
    private double Payment;
    private double AdditionalPayment;
    private double InterestPayment;
    private double Principle;
    private double EndingBanlance;
 
//One constructor     
	public Payment(double beginningBalance, 
			int paymentNbr, 
			LocalDate dueDate, 
			Loan loan) 
	{
		
		this.PaymentNbr = paymentNbr;
		this.DueDate = dueDate;
		this.Payment = (beginningBalance>loan.GetPMT())?loan.GetPMT()
				:(beginningBalance+(beginningBalance*(loan.getInterestRate()/12)));
		
		
		this.AdditionalPayment = loan.getAdditionalPayment();
		
		InterestPayment=beginningBalance * (loan.getInterestRate()/12);
		
		Principle=loan.GetPMT()+loan.getAdditionalPayment()-InterestPayment;
		
		EndingBanlance= beginningBalance - Principle;
		
		
	}

	
//getters for the instance variables		
	public int getPaymentNbr() {
		return PaymentNbr;
	}

	public LocalDate getDueDate() {
		return DueDate;
	}

	public double getPayment() {
		return Payment;
	}

	public double getAdditionalPayment() {
		return AdditionalPayment;
	}

	public double getInterestPayment() {
		return InterestPayment;
	}

	public double getPrinciple() {
		return Principle;
	}

	public double getEndingBanlance() {
		return EndingBanlance;
	}

    public double getTotalPayment() {
    	return this.getPayment()+this.getAdditionalPayment();
    }
    
	
	
}
