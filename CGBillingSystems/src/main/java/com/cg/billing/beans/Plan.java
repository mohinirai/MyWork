package com.cg.billing.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Plan {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int planID;
	private int monthlyRental, freeLocalCalls, freeStdCalls, freeLocalSMS, freeStdSMS, freeInternetDataUsageUnits;
	private float localCallRate, stdCallRate, localSMSRate, stdSMSRate, internetDataUsageRate;
	private String planCircle, planName;
	public int getPlanID() {
		return planID;
	}
	public void setPlanID(int planID) {
		this.planID = planID;
	}
	public int getMonthlyRental() {
		return monthlyRental;
	}
	public void setMonthlyRental(int monthlyRental) {
		this.monthlyRental = monthlyRental;
	}
	public int getFreeLocalCalls() {
		return freeLocalCalls;
	}
	public void setFreeLocalCalls(int freeLocalCalls) {
		this.freeLocalCalls = freeLocalCalls;
	}
	public int getFreeStdCalls() {
		return freeStdCalls;
	}
	public void setFreeStdCalls(int freeStdCalls) {
		this.freeStdCalls = freeStdCalls;
	}
	public int getFreeLocalSMS() {
		return freeLocalSMS;
	}
	public void setFreeLocalSMS(int freeLocalSMS) {
		this.freeLocalSMS = freeLocalSMS;
	}
	public int getFreeStdSMS() {
		return freeStdSMS;
	}
	public void setFreeStdSMS(int freeStdSMS) {
		this.freeStdSMS = freeStdSMS;
	}
	public int getFreeInternetDataUsageUnits() {
		return freeInternetDataUsageUnits;
	}
	public void setFreeInternetDataUsageUnits(int freeInternetDataUsageUnits) {
		this.freeInternetDataUsageUnits = freeInternetDataUsageUnits;
	}
	public float getLocalCallRate() {
		return localCallRate;
	}
	public void setLocalCallRate(float localCallRate) {
		this.localCallRate = localCallRate;
	}
	public float getStdCallRate() {
		return stdCallRate;
	}
	public void setStdCallRate(float stdCallRate) {
		this.stdCallRate = stdCallRate;
	}
	public float getLocalSMSRate() {
		return localSMSRate;
	}
	public void setLocalSMSRate(float localSMSRate) {
		this.localSMSRate = localSMSRate;
	}
	public float getStdSMSRate() {
		return stdSMSRate;
	}
	public void setStdSMSRate(float stdSMSRate) {
		this.stdSMSRate = stdSMSRate;
	}
	public float getInternetDataUsageRate() {
		return internetDataUsageRate;
	}
	public void setInternetDataUsageRate(float internetDataUsageRate) {
		this.internetDataUsageRate = internetDataUsageRate;
	}
	public String getPlanCircle() {
		return planCircle;
	}
	public void setPlanCircle(String planCircle) {
		this.planCircle = planCircle;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public Plan(int planID, int monthlyRental, int freeLocalCalls,
			int freeStdCalls, int freeLocalSMS, int freeStdSMS,
			int freeInternetDataUsageUnits, float localCallRate,
			float stdCallRate, float localSMSRate, float stdSMSRate,
			float internetDataUsageRate, String planCircle, String planName) {
		super();
		this.planID = planID;
		this.monthlyRental = monthlyRental;
		this.freeLocalCalls = freeLocalCalls;
		this.freeStdCalls = freeStdCalls;
		this.freeLocalSMS = freeLocalSMS;
		this.freeStdSMS = freeStdSMS;
		this.freeInternetDataUsageUnits = freeInternetDataUsageUnits;
		this.localCallRate = localCallRate;
		this.stdCallRate = stdCallRate;
		this.localSMSRate = localSMSRate;
		this.stdSMSRate = stdSMSRate;
		this.internetDataUsageRate = internetDataUsageRate;
		this.planCircle = planCircle;
		this.planName = planName;
	}
	public Plan() {
		super();
	}
	@Override
	public String toString() {
		return "Plan [planID=" + planID + ", monthlyRental=" + monthlyRental + ", freeLocalCalls=" + freeLocalCalls
				+ ", freeStdCalls=" + freeStdCalls + ", freeLocalSMS=" + freeLocalSMS + ", freeStdSMS=" + freeStdSMS
				+ ", freeInternetDataUsageUnits=" + freeInternetDataUsageUnits + ", localCallRate=" + localCallRate
				+ ", stdCallRate=" + stdCallRate + ", localSMSRate=" + localSMSRate + ", stdSMSRate=" + stdSMSRate
				+ ", internetDataUsageRate=" + internetDataUsageRate + ", planCircle=" + planCircle + ", planName="
				+ planName + "]";
	}
	
	
	
	
}