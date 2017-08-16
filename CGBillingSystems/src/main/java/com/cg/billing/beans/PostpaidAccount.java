package com.cg.billing.beans;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="mobileaccount")
public class PostpaidAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private long mobileNo;

	@OneToOne
	private Plan plan;

	@ManyToOne
	private Customer customer;

	@OneToMany(mappedBy = "postpaidaccount", orphanRemoval= true)
	@JsonIgnore
	private Map<Integer, Bill> bills = new HashMap<>();

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Map<Integer, Bill> getBills() {
		return bills;
	}

	public void setBills(Bill bills) {
		if (this.bills != null) {
			this.bills.put((this.bills.size() + 1), bills);
		} 
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public PostpaidAccount(long mobileNo, Plan plan, Map<Integer, Bill> bills) {
		super();
		this.mobileNo = mobileNo;
		this.plan = plan;
		this.bills = bills;
	}

	public PostpaidAccount() {
		super();
	}

	@Override
	public String toString() {
		return "PostpaidAccount [mobileNo=" + mobileNo + ", plan=" + plan + ", customer=" + customer + ", bills="
				+ bills + "]";
	}

}