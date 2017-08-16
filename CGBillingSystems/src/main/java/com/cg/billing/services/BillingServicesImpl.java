package com.cg.billing.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.dao.BillingDaoImpl;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;

@Service
@Transactional
public class BillingServicesImpl implements IBillingServices {

	@Autowired
	BillingDaoImpl dao;

	@Override
	public List<Plan> getPlanAllDetails() throws BillingServicesDownException {

		return dao.getAllPlans();
	}

	@Override
	public long openPostpaidMobileAccount(int customerID, int planId, PostpaidAccount account)
			throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException {

		Plan plan = dao.findPlan(planId);
		account.setPlan(plan);
		return dao.insertPostPaidAccount(customerID, account);
	}

	@Override
	public double generateMonthlyMobileBill(int customerID, long mobileNo, String billMonth, int noOfLocalSMS,
			int noOfStdSMS, int noOfLocalCalls, int noOfStdCalls, int internetDataUsageUnits, int planId)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillingServicesDownException, PlanDetailsNotFoundException {

		Plan plan = dao.getPlanDetails(customerID, mobileNo);
		Bill bill = new Bill();

		float amount = 0;

		if (noOfLocalCalls > plan.getFreeLocalCalls())
			bill.setLocalCallAmount((noOfLocalCalls - plan.getFreeLocalCalls()) * plan.getLocalCallRate());
		amount = amount + bill.getLocalCallAmount();

		if (noOfLocalSMS > plan.getFreeLocalSMS())
			bill.setLocalSMSAmount((noOfLocalSMS - plan.getFreeLocalSMS()) * plan.getLocalSMSRate());
		amount = amount + bill.getLocalSMSAmount();

		if (noOfStdSMS > plan.getFreeStdSMS())
			bill.setStdSMSAmount((noOfStdSMS - plan.getFreeStdSMS()) * plan.getStdSMSRate());
		amount = amount + bill.getStdSMSAmount();

		if (noOfStdCalls > plan.getFreeStdCalls())
			bill.setStdCallAmount((noOfStdCalls - plan.getFreeStdCalls()) * plan.getStdCallRate());
		amount = amount + bill.getStdCallAmount();

		if (internetDataUsageUnits > plan.getFreeInternetDataUsageUnits())
			bill.setInternetDataUsageAmount(
					(internetDataUsageUnits - plan.getFreeInternetDataUsageUnits()) * plan.getInternetDataUsageRate());
		amount = amount + bill.getInternetDataUsageAmount();

		bill.setServicesTax((float) (0.1 * amount));
		bill.setVat((float) (0.06 * amount));
		bill.setTotalBillAmount(plan.getPlanID() + amount + bill.getServicesTax() + bill.getVat());

		return dao.insertMonthlybill(customerID, mobileNo, bill);
	}

	@Override
	public Customer getCustomerDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {

		return dao.getCustomer(customerID);
	}

	@Override
	public List<Customer> getAllCustomerDetails() throws BillingServicesDownException {

		return dao.getAllCustomers();
	}

	@Override
	public PostpaidAccount getPostPaidAccountDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {

		return dao.getCustomerPostPaidAccount(customerID, mobileNo);
	}

	@Override
	public List<PostpaidAccount> getCustomerAllPostpaidAccountsDetails(int customerID)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {

		return dao.getCustomerPostPaidAccounts(customerID);
	}

	@Override
	public Bill getMobileBillDetails(int customerID, long mobileNo, String billMonth)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException,
			BillDetailsNotFoundException, BillingServicesDownException {

		return dao.getMonthlyBill(customerID, mobileNo, billMonth);
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBillDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {

		return dao.getCustomerPostPaidAccountAllBills(customerID, mobileNo);
	}

	@Override
	public boolean changePlan(int customerID, long mobileNo, int planID) throws CustomerDetailsNotFoundException,
			PostpaidAccountNotFoundException, PlanDetailsNotFoundException, BillingServicesDownException {
		return false;
	}

	@Override
	public boolean closeCustomerPostPaidAccount(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException {
		return dao.deletePostPaidAccount(customerID, mobileNo);
	}

	@Override
	public boolean deleteCustomer(int customerID)
			throws BillingServicesDownException, CustomerDetailsNotFoundException {
		return dao.deleteCustomer(customerID);
	}

	@Override
	public Plan getCustomerPostPaidAccountPlanDetails(int customerID, long mobileNo)
			throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException,
			PlanDetailsNotFoundException {
		return dao.getPlanDetails(customerID, mobileNo);
	}

	@Override
	public Customer acceptCustomerDetails(Customer customer) throws BillingServicesDownException {

		return dao.insertCustomer(customer);
	}

	@Override
	public boolean authenticateCustomer(Customer customer)
			throws CustomerDetailsNotFoundException, BillingServicesDownException {
		return false;
	}

	@Override
	public double insertMonthlybill(int customerID, long mobileNo, Bill bill) throws BillingServicesDownException {
		return dao.insertMonthlybill(customerID, mobileNo, bill);
	}
}
