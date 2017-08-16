package com.cg.billing.dao;

import java.util.List;

import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;

public interface IBillingDao {
	
		Customer insertCustomer(Customer customer) throws BillingServicesDownException ;
		long insertPostPaidAccount(int customerID, PostpaidAccount account);
		boolean updatePostPaidAccount(int customerID, PostpaidAccount account);
		double insertMonthlybill(int customerID, long mobileNo, Bill bill);
		int insertPlan(Plan plan) throws BillingServicesDownException;
		boolean deletePostPaidAccount(int customerID, long mobileNo);
		Bill getMonthlyBill(int customerID, long mobileNo, String billMonth);
		List<Bill>getCustomerPostPaidAccountAllBills(int customerID, long mobileNo);
		List<PostpaidAccount> getCustomerPostPaidAccounts(int customerID);
		Customer getCustomer(int customerID);
		List<Customer>  getAllCustomers();
	    List<Plan> getAllPlans();
		PostpaidAccount getCustomerPostPaidAccount(int customerID, long mobileNo);
		Plan getPlanDetails(int customerID, long mobileNo) throws PostpaidAccountNotFoundException;
		boolean deleteCustomer(int customerID);
		Plan findPlan(int planID);

}
		