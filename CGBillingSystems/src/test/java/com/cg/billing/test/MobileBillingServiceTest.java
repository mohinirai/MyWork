package com.cg.billing.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.cg.billing.beans.Address;
import com.cg.billing.beans.Bill;
import com.cg.billing.beans.Customer;
import com.cg.billing.beans.Plan;
import com.cg.billing.beans.PostpaidAccount;
import com.cg.billing.dao.IBillingDao;
import com.cg.billing.exceptions.BillDetailsNotFoundException;
import com.cg.billing.exceptions.BillingServicesDownException;
import com.cg.billing.exceptions.CustomerDetailsNotFoundException;
import com.cg.billing.exceptions.InvalidBillMonthException;
import com.cg.billing.exceptions.PlanDetailsNotFoundException;
import com.cg.billing.exceptions.PostpaidAccountNotFoundException;
import com.cg.billing.services.BillingServicesImpl;
import com.cg.billing.services.IBillingServices;

public class MobileBillingServiceTest {
	private static IBillingServices service;
	ArrayList<Customer> customerList;
	ArrayList<Plan> planList;
	
	@BeforeClass
	public static void setUpBillingServices(){
		/*dao = Mockito.mock(BillingDAOServices.class);
		service = new BillingServicesImpl(dao);		*/
		
		service = new BillingServicesImpl();
	}
	
	@Before
	public void setUpBillingData() throws BillingServicesDownException{
		Bill bill1= new Bill(01, 5, 0, 9, 0, 9887, "jan", 500, 5, 0, 56, 0, 50, 50, 34);
		Bill bill2= new Bill(02, 6, 0, 9, 0, 9887, "feb", 500, 5, 0, 56, 0, 50, 50, 34);
		Bill bill3= new Bill(03, 7, 0, 9, 0, 9887, "march", 500, 5, 0, 56, 0, 50, 50, 34);
		
		planList = new ArrayList<Plan>();
		Plan plan1 = new Plan(001, 200, 500, 300, 1000, 2000, 8000, 5, 9, 3, 5, 300, null, "jackpot");
		Plan plan2 = new Plan(002, 230, 503, 330, 1300, 4000, 8050, 5, 9, 3, 5, 300, null, "superjackpot");
		Plan plan3 = new Plan(123, 230, 503, 330, 1300, 4000, 8050, 5, 9, 3, 5, 300, null, "superjackpot");
		
		
		planList.add(plan1);
		planList.add(plan2);
		
		PostpaidAccount ppa1 = new PostpaidAccount(98678986, plan1, bill1);
		PostpaidAccount ppa2 = new PostpaidAccount(98788986, plan2, bill2);
		//PostpaidAccount ppa3 = new PostpaidAccount(98788986, plan1, null);
		
		Address addr1 = new Address("lko", "up", 24566);
		Address addr2 = new Address("agra", "up", 4738);
		
		customerList= new ArrayList<>();
		Customer customer1 = new Customer(1001, "ram", "gupta", "ram.cg.com", "25/07/9111", "4344", addr1, ppa1);
		Customer customer2 = new Customer(1002, "ramu", "gupta", "ram.cg.com", "25/07/9111", "4344", addr1, ppa2);
	//	Customer customer3 = new Customer( "shyam", "gupta", "ram.cg.com", "25/07/9111", "4344", null, null);
		
		customerList.add(customer1);
		customerList.add(customer2);
/*
		Mockito.when(dao.insertCustomer(new Customer( "shyam", "gupta", "ram.cg.com", "25/07/9111", "4344", null, null))).thenReturn(1003);
		Mockito.when(dao.insertPostPaidAccount(1001, ppa1)).thenReturn(98678986L);
		Mockito.when(dao.insertPostPaidAccount(1234, ppa2)).thenReturn(0L);
		Mockito.when(dao.insertPostPaidAccount(1001, ppa3)).thenReturn(0L);
		Mockito.when(dao.getCustomerPostPaidAccount(1446, 98678986)).thenReturn(null);
		Mockito.when(dao.getCustomerPostPaidAccount(1001, 97787986)).thenReturn(null);
		Mockito.when(dao.getCustomerPostPaidAccount(1001, 98678986)).thenReturn(ppa1);
		Mockito.when(dao.getAllCustomers()).thenReturn(customerList);
		Mockito.when(dao.getAllPlans()).thenReturn(planList);
		Mockito.when(dao.getMonthlyBill(1452, 789456258L, "jan")).thenReturn(null);
		Mockito.when(dao.getMonthlyBill(1001, 789456258L, "jan")).thenReturn(null);
		Mockito.when(dao.getMonthlyBill(1001, 98678986L, "january")).thenReturn(null);
		Mockito.when(dao.getMonthlyBill(1001, 98678986L, "jan")).thenReturn(bill1);
		Mockito.when(dao.getPlanDetails(2345, 657565644)).thenReturn(null);
		Mockito.when(dao.getPlanDetails(1001, 657565644)).thenReturn(null);
		Mockito.when(dao.getPlanDetails(1001, 98678986L)).thenReturn(plan1);
		Mockito.when(dao.getCustomerPostPaidAccountAllBills(4325, 7888888)).thenReturn(null);
		Mockito.when(dao.getCustomerPostPaidAccountAllBills(1001, 7888888)).thenReturn(null);
		Mockito.when(dao.getCustomerPostPaidAccountAllBills(1001, 98678986L)).thenReturn(new ArrayList<>(customerList.get(1).getPostpaidAccounts().get(1).getBills().values()));
		Mockito.when(dao.getCustomer(3456)).thenReturn(null);
		Mockito.when(dao.getCustomer(1001)).thenReturn(customer1);
		Mockito.when(dao.getCustomerPostPaidAccounts(2345)).thenReturn(null);
		Mockito.when(dao.getCustomerPostPaidAccounts(1001)).thenReturn(new ArrayList<>(customerList.get(1).getPostpaidAccounts().values()));
		Mockito.when(dao.insertMonthlybill(2345, 1234567, new Bill(100,12,38,78,876,"fab",0,0, 0,0,0,0,0,0))).thenReturn(0.0);
		Mockito.when(dao.insertMonthlybill(1001, 1234567, new Bill(100,12,38,78,876,"fab",0,0, 0,0,0,0,0,0))).thenReturn(0.0);
		Mockito.when(dao.insertMonthlybill(1001, 98678986L, new Bill(100,12,38,78,876,"fab",0,0, 0,0,0,0,0,0))).thenReturn(0.0);
		Mockito.when(dao.insertMonthlybill(1001, 98678986L, new Bill(100,12,38,78,876,"May",0,0, 0,0,0,0,0,0))).thenReturn(34234d);
		Mockito.when(dao.deleteCustomer(2345)).thenReturn(false);
		Mockito.when(dao.deleteCustomer(1001)).thenReturn(true);
		Mockito.when(dao.deletePostPaidAccount(2345,9874444)).thenReturn(false);
		Mockito.when(dao.deletePostPaidAccount(1001,9874444)).thenReturn(false);
		Mockito.when(dao.deletePostPaidAccount(1001,98678986L)).thenReturn(true);
		Mockito.when(dao.updatePostPaidAccount(2211,ppa2)).thenReturn(false);
		Mockito.when(dao.updatePostPaidAccount(1001,ppa2)).thenReturn(false);
		Mockito.when(dao.updatePostPaidAccount(1001,ppa3)).thenReturn(false);
		Mockito.when(dao.updatePostPaidAccount(1001,ppa1)).thenReturn(true);*/
	}
	
	@Test
	public void testAcceptCustomerDetailsForValidData() throws BillingServicesDownException{
		int expectedCustomer= 1003;
		Customer customer = service.acceptCustomerDetails(new Customer());
		int actualCustomer = customer.getCustomerID();
		assertEquals(expectedCustomer,actualCustomer);
		
	}
	
	@Test(expected=CustomerDetailsNotFoundException.class)
	public void testOpenPostpaidMobileAccountForInvalidCustomer() throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException{	
		service.openPostpaidMobileAccount(1234,001, new PostpaidAccount());
	}
	
	@Test(expected=PlanDetailsNotFoundException.class)
	public void testOpenPostpaidMobileAccountForInvalidPlan() throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException{
		service.openPostpaidMobileAccount(1001, 789, new PostpaidAccount());
	}
	
	@Test
	public void testOpenPostpaidMobileAccountForValidData() throws PlanDetailsNotFoundException, CustomerDetailsNotFoundException, BillingServicesDownException{
		Long expectedResult = 98678986L;
		Long actualResult = service.openPostpaidMobileAccount(1001, 001, new PostpaidAccount());
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected=CustomerDetailsNotFoundException.class)
	public void testGetPostPaidAccountDetailsForInvalidCustomer() throws CustomerDetailsNotFoundException, BillingServicesDownException, PostpaidAccountNotFoundException{
		service.getPostPaidAccountDetails(1078, 98678986);
	}
	
	@Test(expected= PostpaidAccountNotFoundException.class)
	public void testGetPostPaidAccountDetailsForInvalidAccount() throws CustomerDetailsNotFoundException, BillingServicesDownException, PostpaidAccountNotFoundException{
		service.getPostPaidAccountDetails(1001, 98678766);
	}
	
	@Test
	public void testGetPostPaidAccountDetailsForValidAccount() throws CustomerDetailsNotFoundException, BillingServicesDownException, PostpaidAccountNotFoundException{
		PostpaidAccount expectedAccount= new PostpaidAccount(98678986, new Plan(001, 200, 500, 300, 1000, 2000, 8000, 5, 9, 3, 5, 300, null, "jackpot"), new Bill(01, 5, 0, 9, 0, 9887, "jan", 500, 5, 0, 56, 0, 50, 50, 34));
		PostpaidAccount actualAccount=  service.getPostPaidAccountDetails(1001, 98678986);
		assertEquals(expectedAccount, actualAccount);
	}
	
	@Test
	public void testGetPlanAllDetails() throws BillingServicesDownException{
		List<Plan> expectedList = planList;
		List<Plan> actualList = service.getPlanAllDetails();
		assertEquals(expectedList, actualList);
	}
	
	@Test
	public void testGetAllCustomerDetails() throws BillingServicesDownException{
		List<Customer> expectedList = customerList;
		List<Customer> actualList = service.getAllCustomerDetails();
		assertEquals(expectedList, actualList);
	}
	
	@Test(expected = CustomerDetailsNotFoundException.class)
	public void testGetMobileBillDetailsForInvalidCustomerId() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException, BillingServicesDownException{
		service.getMobileBillDetails(1452, 789456258L, "jan");
	}
	
	@Test(expected = PostpaidAccountNotFoundException.class)
	public void testGetMobileBillDetailsForInvalidMobile() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException, BillingServicesDownException{
		service.getMobileBillDetails(1001, 789456258L, "jan");
	}
	
	@Test(expected = InvalidBillMonthException.class)
	public void testGetMobileBillDetailsForInvalidBillMonth() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException, BillingServicesDownException{
		service.getMobileBillDetails(1001, 98678986L, "january");
	}
	
	@Test
	public void testGetMobileBillDetailsForValidData() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillDetailsNotFoundException, BillingServicesDownException{
		Bill actualBill = service.getMobileBillDetails(1001, 98678986L, "jan");
		Bill expectedBill = new Bill(01, 5, 0, 9, 0, 9887, "jan", 500, 5, 0, 56, 0, 50, 50, 34);
		assertEquals(expectedBill, actualBill);
	}
	
	@Test(expected=CustomerDetailsNotFoundException.class)
	public void testGetCustomerPostPaidAccountPlanDetailsForInvalidCustomerId() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException, PlanDetailsNotFoundException{
		service.getCustomerPostPaidAccountPlanDetails(2345, 657565644);
	}
	
	@Test(expected=PostpaidAccountNotFoundException.class)
	public void testGetCustomerPostPaidAccountPlanDetailsForInvalidMobileNo() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException, PlanDetailsNotFoundException{
		service.getCustomerPostPaidAccountPlanDetails(1001, 657565644);
	}
	
	@Test
	public void testGetCustomerPostPaidAccountPlanDetailsForValidData() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException, PlanDetailsNotFoundException{
		Plan actualPlan= service.getCustomerPostPaidAccountPlanDetails(1001, 98678986L);
		Plan expectedPlan= customerList.get(1).getPostpaidAccounts().get(1).getPlan();
		assertEquals(expectedPlan,actualPlan);
	}
	
	@Test(expected=CustomerDetailsNotFoundException.class)
	public void testgetCustomerPostPaidAccountAllBillDetailsForInavlidCustomerId() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException{
		service.getCustomerPostPaidAccountAllBillDetails(4325, 7888888);
	}
	
	@Test(expected=PostpaidAccountNotFoundException.class)
	public void testgetCustomerPostPaidAccountAllBillDetailsForInavlidMobileNo() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException{
		service.getCustomerPostPaidAccountAllBillDetails(1001, 7888888);
	}
	
	@Test
	public void testgetCustomerPostPaidAccountAllBillDetailsForValidData() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException{
		List<Bill> actualBills=service.getCustomerPostPaidAccountAllBillDetails(1001, 98678986L);
		List<Bill> expectedBills =new ArrayList<>(customerList.get(1).getPostpaidAccounts().get(1).getBills().values());
		assertEquals(expectedBills, actualBills);
	}
	@Test(expected=CustomerDetailsNotFoundException.class)
	public void testGetCustomerDetailsForInvalidCustomerId() throws CustomerDetailsNotFoundException, BillingServicesDownException{
		service.getCustomerDetails(3456);
	}
	@Test
	public void testGetCustomerDetailsForValidData() throws CustomerDetailsNotFoundException, BillingServicesDownException{
		Address addr1 = new Address("lko", "up", 24566);
		Plan plan1 = new Plan(001, 200, 500, 300, 1000, 2000, 8000, 5, 9, 3, 5, 300, null, "jackpot");
		Bill bill1= new Bill(01, 5, 0, 9, 0, 9887, "jan", 500, 5, 0, 56, 0, 50, 50, 34);
		PostpaidAccount ppa1 = new PostpaidAccount(98678986, plan1, bill1);
		Customer expectedResult= new Customer(1001, "ram", "gupta", "ram.cg.com", "25/07/9111", "4344", addr1, ppa1);
		Customer actualResult= service.getCustomerDetails(1001);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected=CustomerDetailsNotFoundException.class)
	public void testGetCustomerAllPostpaidAccountsDetailsForInvalidCustomerId() throws CustomerDetailsNotFoundException, BillingServicesDownException{
		service.getCustomerAllPostpaidAccountsDetails(2345);
	}
	
	@Test
	public void testGetCustomerAllPostpaidAccountsDetailsForValidData() throws CustomerDetailsNotFoundException, BillingServicesDownException{
		List<PostpaidAccount> expectedResult=new ArrayList<>(customerList.get(1).getPostpaidAccounts().values());
		List<PostpaidAccount> actualResult= service.getCustomerAllPostpaidAccountsDetails(1001);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected=CustomerDetailsNotFoundException.class)
	public void testGenerateMonthlyMobileBillForInvalidCustomerId() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillingServicesDownException, PlanDetailsNotFoundException{
		service.generateMonthlyMobileBill(2345, 1234567, "May", 100, 12, 34, 78, 876,001);
	}
	@Test(expected=PostpaidAccountNotFoundException.class)
	public void testGenerateMonthlyMobileBillForInvalidMobileNo() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillingServicesDownException, PlanDetailsNotFoundException{
		service.generateMonthlyMobileBill(1001, 1234567, "May", 100, 12, 34, 78, 876,001);
	}
	@Test(expected=InvalidBillMonthException.class)
	public void testGenerateMonthlyMobileBillForInvalidBillMonth() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillingServicesDownException, PlanDetailsNotFoundException{
		service.generateMonthlyMobileBill(1001, 98678986, "fab", 100, 12, 34, 78, 876,001);
	}
	@Test
	public void testGenerateMonthlyMobileBillForValidData() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, InvalidBillMonthException, BillingServicesDownException, PlanDetailsNotFoundException{
		Double expectedAmount= 34234d;
		Double actualAmount=service.generateMonthlyMobileBill(2345, 98678986, "May", 100, 12, 34, 78, 876,001);
		assertEquals(expectedAmount, actualAmount);
	}
	@Test(expected=CustomerDetailsNotFoundException.class)
	public void testDeleteCustomerForInvalidCustomerId() throws BillingServicesDownException, CustomerDetailsNotFoundException{
		service.deleteCustomer(3456);
	}
	
	@Test
	public void testDeleteCustomerForValidData() throws BillingServicesDownException, CustomerDetailsNotFoundException{
		boolean expectedResult= true;
		boolean actualResult=service.deleteCustomer(1001);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected=CustomerDetailsNotFoundException.class)
	public void testCloseCustomerPostPaidAccountForInvalidCustomerId() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException{
		service.closeCustomerPostPaidAccount(2345,9874444);
	}
	
	@Test(expected=PostpaidAccountNotFoundException.class)
	public void testCloseCustomerPostPaidAccountForInvalidMobileNo() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException{
		service.closeCustomerPostPaidAccount(1001,9874444);
		
	}
	
	@Test
	public void testCloseCustomerPostPaidAccountFoValidData() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, BillingServicesDownException{
		boolean expectedResult=true;
		boolean actualResult= service.closeCustomerPostPaidAccount(1001,98678986L);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected=CustomerDetailsNotFoundException.class)
	public void testChangePlanForInvalidCustomerId() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException, BillingServicesDownException{
		service.changePlan(2211, 9877554, 123);
	}
	
	@Test(expected=PostpaidAccountNotFoundException.class)
	public void testChangePlanForInvalidMobileNo() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException, BillingServicesDownException{
		service.changePlan(1001, 98788986, 123);
	}
	
	@Test(expected=PlanDetailsNotFoundException.class)
	public void testChangePlanForInvalidPlanId() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException, BillingServicesDownException{
		service.changePlan(1001, 98678986L, 123);
	}
	
	@Test
	public void testChangePlanForValidData() throws CustomerDetailsNotFoundException, PostpaidAccountNotFoundException, PlanDetailsNotFoundException, BillingServicesDownException{
		boolean expectedResult=true;
		boolean actualResult= service.changePlan(1001, 98678986L, 002);
		assertEquals(expectedResult, actualResult);
	}
	
	@Test(expected=CustomerDetailsNotFoundException.class)
	public void testAuthenticateCustomerForInvalidCustomer() throws CustomerDetailsNotFoundException, BillingServicesDownException{
		service.authenticateCustomer(new Customer());
	}
	
	@Test
	public void testAuthenticateCustomerForValidCustomer() throws CustomerDetailsNotFoundException, BillingServicesDownException{
		Address addr1 = new Address("lko", "up", 24566);
		Plan plan1 = new Plan(001, 200, 500, 300, 1000, 2000, 8000, 5, 9, 3, 5, 300, null, "jackpot");
		Bill bill1= new Bill(01, 5, 0, 9, 0, 9887, "jan", 500, 5, 0, 56, 0, 50, 50, 34);
		PostpaidAccount ppa1 = new PostpaidAccount(98678986, plan1, bill1);
		
		boolean expectedResult=true;
		boolean actualResult=service.authenticateCustomer( new Customer(1001, "ram", "gupta", "ram.cg.com", "25/07/9111", "4344", addr1, ppa1));
		assertEquals(expectedResult, actualResult);
	}
	
	@After
	public void tearDownBillingData(){
		
	}
	
	@AfterClass
	public static void tearDownBillingServices(){
		service =null;
	}
  
}
