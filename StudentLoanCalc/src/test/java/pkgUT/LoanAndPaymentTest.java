package pkgUT;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import pkgLogic.Loan;
import pkgLogic.Payment;

class LoanAndPaymentTest {
	
//pay extra 100
	@Test
	void Test1() {
		LocalDate ld = LocalDate.of(2019,12,05);
        Loan l =new Loan(ld,100000.00, 0.067,100,120);
		double expectedTotalInterest = 33029.39;
		double expectedTotalPayments = 133029.39;
		assertEquals(l.GetTotalInterest(), expectedTotalInterest,0.1);
		assertEquals(l.GetTotalPayments(), expectedTotalPayments,0.1);
	}
	
	
//no extra payment
	@Test 
	void Test2() {
		LocalDate ld = LocalDate.of(2019,12,12);
        Loan l =new Loan(ld,100000.00, 0.067,0,120);
		double expectedTotalInterest = 37481.87;
		double expectedTotalPayments = 137481.87;
		assertEquals(l.GetTotalInterest(), expectedTotalInterest,0.1);
		assertEquals(l.GetTotalPayments(), expectedTotalPayments,0.1);
	}
	
	@Test 
	void Test3() {
		LocalDate ld = LocalDate.of(2019,12,12);
        Loan l =new Loan(ld,500000.00, 0.08,100,360);
		double expectedTotalInterest = 724058.92;
		double expectedTotalPayments = 1224058.92;
//		System.out.printf("%f\n", l.GetTotalInterest());
//		System.out.printf("%f\n", l.GetTotalPayments());
		assertEquals(l.GetTotalInterest(), expectedTotalInterest,0.1);
		assertEquals(l.GetTotalPayments(), expectedTotalPayments,0.1);
	}
	
	@Test 
	void Test4() { 
		LocalDate ld = LocalDate.of(2019,12,12);
        Loan l =new Loan(ld,500000.00, 0, 0.08,100,360,false);
        assertEquals(l.isbCompoundingOption(),false);
	}
	

    @Test
	void Test5() {
		LocalDate ld = LocalDate.of(2019,12,12);
        Loan l =new Loan(ld,500000.00, 0.08,100,360);
        assertEquals(l.getStartDate(), ld);
	}
    
    @Test
   	void Test6() {
   		LocalDate ld = LocalDate.of(2019,12,12);
           Loan l =new Loan(ld,500000.00, 0.08,100,360);
           Payment p = new Payment(500000.00, 360,ld,l);
           assertEquals(p.getPaymentNbr(),360 );
           assertEquals(p.getDueDate(),ld );
           assertEquals(p.getPrinciple(),435.49,0.1);

   	}

}
