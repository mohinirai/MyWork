package com.cg.billing.services;

import java.util.List;

import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;

public interface IBillingServices {

	List<Plan> getPlanAllDetails() throws BillingServicesDownException;
	
	//Customer acceptCustomerDetails(Customer customer) throws BillingServicesDownException;

		long openPostpaidMobileAccount(int customerID,int planid,PostpaidAccount account) 
				throws PlanDetailsNotFoundException,CustomerDetailsNotFoundException,
				BillingServicesDownException;
		
		double  generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS, int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls,int internetDataUsageUnits, int planId) 
				throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
				InvalidBillMonthException, BillingServicesDownException, 
				PlanDetailsNotFoundException;
		
		Customer getCustomerDetails(int customerID)
				throws CustomerDetailsNotFoundException, BillingServicesDownException;
		
		List<Customer>  getAllCustomerDetails() throws BillingServicesDownException;
		
		PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo) 
				throws CustomerDetailsNotFoundException, 
				PostpaidAccountNotFoundException, 
				BillingServicesDownException;
		
		List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
				throws CustomerDetailsNotFoundException, BillingServicesDownException;
		
		Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
				throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
				InvalidBillMonthException, BillDetailsNotFoundException, BillingServicesDownException;
		
		List<Bill>   getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo) 
				throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
				BillingServicesDownException;
		
		boolean changePlan(int customerID, long mobileNo, int planID)
				throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
				PlanDetailsNotFoundException, BillingServicesDownException;
		
		boolean closeCustomerPostPaidAccount(int customerID, long mobileNo) 
				throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
				BillingServicesDownException;
		
		boolean deleteCustomer(int customerID) 
				throws BillingServicesDownException, CustomerDetailsNotFoundException;
		
		Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo) 
				throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, 
				BillingServicesDownException, PlanDetailsNotFoundException ;

		Customer acceptCustomerDetails(Customer customer)
				throws BillingServicesDownException;

		public double insertMonthlybill(int customerID, long mobileNo, Bill bill) throws BillingServicesDownException;

		boolean authenticateCustomer(Customer customer) throws CustomerDetailsNotFoundException, BillingServicesDownException;
}
