package com.cg.billing.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;

@Repository
public class BillingDaoImpl implements IBillingDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public Customer insertCustomer(Customer customer) throws BillingServicesDownException {

		em.persist(customer);
		em.flush();
		return customer;
	}

	@Override
	public long insertPostPaidAccount(int customerID, PostpaidAccount account) {
		Customer customer = em.find(Customer.class, customerID);
		customer.setPostpaidAccounts(account);
		account.setCustomer(customer);
		em.persist(account);
		em.flush();
		return account.getMobileNo();
	}


	@Override
	public int insertPlan(Plan plan) throws BillingServicesDownException {

		em.persist(plan);
		em.flush();
		return plan.getPlanID();
	}

	@Override
	public boolean deletePostPaidAccount(int customerID, long mobileNo) {
		boolean result = false;
		System.out.println(em.find(Customer.class, customerID));
		System.out.println(em.find(PostpaidAccount.class, mobileNo));
		if (em.find(Customer.class, customerID) != null && em.find(PostpaidAccount.class, mobileNo) != null) {
			PostpaidAccount acc = em.find(PostpaidAccount.class, mobileNo);
			System.out.println("dao exc mei no error" + acc);
			em.remove(acc);
			em.flush();
			result = true;
		}
		return result;
	}
	
	
	@Override
	public List<PostpaidAccount> getCustomerPostPaidAccounts(int customerID) {

		TypedQuery<PostpaidAccount> query = em.createQuery(
				"select p from PostpaidAccount p where customer.customerID = " + customerID, PostpaidAccount.class);
		return query.getResultList();
	}

	@Override
	public PostpaidAccount getCustomerPostPaidAccount(int customerID, long mobileNo) {
		TypedQuery<PostpaidAccount> query = em
				.createQuery("select p from PostpaidAccount p where customer.customerID = " + customerID
						+ " AND mobileNo = " + mobileNo, PostpaidAccount.class);
		return query.getSingleResult();
	}

	@Override
	public Customer getCustomer(int customerID) {
		Customer customer = em.find(Customer.class, customerID);
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {

		TypedQuery<Customer> query = em.createQuery("select c from Customer c", Customer.class);

		return query.getResultList();
	}

	@Override
	public List<Plan> getAllPlans() {

		TypedQuery<Plan> query = em.createQuery("select p from Plan p", Plan.class);

		return query.getResultList();
	}


	@Override
	public Plan getPlanDetails(int customerID, long mobileNo) throws PostpaidAccountNotFoundException {
		
		TypedQuery<PostpaidAccount> query = em
				.createQuery("select p from PostpaidAccount p where customer.customerID = " + customerID
						+ " AND mobileNo = " + mobileNo, PostpaidAccount.class);
		return query.getSingleResult().getPlan();
	}

	@Override
	public boolean deleteCustomer(int customerID) {
		boolean result = false;
		if (em.find(Customer.class, customerID) != null) {
			result = true;
			em.remove(getCustomer(customerID));
		}
		return result;
	}

	@Override
	public Plan findPlan(int planID) {
		
		return em.find(Plan.class, planID);
	}
	
	@Override
	public boolean updatePostPaidAccount(int customerID, PostpaidAccount account) {

		return false;
	}
	
	@Override
	public double insertMonthlybill(int customerID, long mobileNo, Bill bill) {

		PostpaidAccount postpaid = em.find(PostpaidAccount.class, mobileNo);
		bill.setPostpaidaccount(postpaid);
		postpaid.setBills(bill);
		em.persist(bill);
		return bill.getTotalBillAmount();
	}
	
	@Override
	public Bill getMonthlyBill(int customerID, long mobileNo, String billMonth) {

		return null;
	}

	@Override
	public List<Bill> getCustomerPostPaidAccountAllBills(int customerID, long mobileNo) {

		return null;
	}

}
