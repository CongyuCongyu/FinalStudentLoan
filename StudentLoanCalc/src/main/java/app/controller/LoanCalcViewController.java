package app.controller;

import app.StudentCalc;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pkgLogic.Loan;
import pkgLogic.Payment;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;

public class LoanCalcViewController implements Initializable   {

	private StudentCalc SC = null;
	
	@FXML
	private TextField LoanAmount;
	
	@FXML
	private TextField InterestRate;
	
	@FXML
	private TextField NbrOfYears;
	
	@FXML
	private DatePicker PaymentStartDate;
	
	@FXML
	private TextField AdditionalPayment;
	
	@FXML
	private Label lblTotalPayments;
	
	@FXML
	private Label lblTotalInterest;
	
	@FXML
	private TableView<Payment> tvResults;
	
	
	
	@FXML
	private TableColumn<Payment, Integer> colPaymentNbr;

	@FXML
	private TableColumn<Payment, LocalDate> colDueDate;
	
	@FXML
	private TableColumn<Payment, Double> colPayment;
	
	@FXML
	private TableColumn<Payment, Double> colAdditionalPayment;
	
	@FXML
	private TableColumn<Payment, Double> colInterestPayment;
	
	@FXML
	private TableColumn<Payment, Double> colPrinciple;
	
	@FXML
	private TableColumn<Payment, Double> colEndingBalance;
	
	
	private ObservableList<Payment> paymentList = FXCollections.observableArrayList();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		colPaymentNbr.setCellValueFactory(new PropertyValueFactory<>("PaymentNbr"));
		colDueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));
		colPayment.setCellValueFactory(new PropertyValueFactory<>("Payment"));
		colAdditionalPayment.setCellValueFactory(new PropertyValueFactory<>("AdditionalPayment"));
		colInterestPayment.setCellValueFactory(new PropertyValueFactory<>("InterestPayment"));
		colPrinciple.setCellValueFactory(new PropertyValueFactory<>("Principle"));
		colEndingBalance.setCellValueFactory(new PropertyValueFactory<>("EndingBalance"));

		//TODO: Add a 'setCellValueFactor' entry for each column, mapping to each attribute in Payment
		
		tvResults.setItems(paymentList);
	}

	public void setMainApp(StudentCalc sc) {
		this.SC = sc;
	}
	
	/**
	 * btnCalcLoan - Fire this event when the button clicks
	 * 
	 * @version 1.0
	 * @param event
	 */
	@FXML
	private void btnCalcLoan(ActionEvent event) {
		//	Examples- how to read data from the form
		
		paymentList.clear();
		lblTotalPayments.setText("");
		lblTotalInterest.setText("");

		double dLoanAmount = Double.parseDouble(LoanAmount.getText());	
		double dInterestRate = Double.parseDouble(InterestRate.getText());
		int iNbrOfYears = Integer.parseInt(NbrOfYears.getText());
		LocalDate localDate = PaymentStartDate.getValue();
		double dAdditionalPayment=Double.parseDouble(AdditionalPayment.getText());
		
		Loan StudentLoan=new Loan(localDate,dLoanAmount,dInterestRate,dAdditionalPayment,iNbrOfYears*12);
		for(Payment p: StudentLoan.getLoanPayments()) {
			paymentList.add(p);
		}
		//paymentList.addAll(StudentLoan.getLoanPayments());
		
        lblTotalPayments.setText(Double.toString(StudentLoan.GetTotalPayments()));
        lblTotalInterest.setText(Double.toString(StudentLoan.GetTotalInterest()));
        
		/*
		 * When this button is clicked, you need to do the following:
		 * 
		 * 1) Clear the table
		 * 2) Clear the results fields (Total Payments, Total Interest)
		 * 3) You're going to create 'n' payments based on the data you give.  You'll calculate and
		 * 		carry forward 'balance', because you're going to have to hand calculate that month's
		 * 		interest.
		 * Payment# - you'll set this, counting from 1 to N
		 * Due Date - based on the given date.  method .plusMonths(1) will calculate date + 1 month.
		 * Payment  - Calculate based on PMT function (which is your minimum payment)
		 * Additional Payment - based on Additional Payment given by user
		 * Interest - Calculate based on 
		 */
		
	}
	
}
