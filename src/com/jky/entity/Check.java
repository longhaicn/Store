package com.jky.entity;

public class Check {

	/**订单ID*/
	private int checkId;
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public float getSum() {
		return sum;
	}
	public void setSum(float sum) {
		this.sum = sum;
	}
	/**成员ID*/
	private int memberId;
	/**成交时间*/
	private String date;
	/**成交金额*/
	private float sum;
	@Override
	public String toString() {
		return "Check [checkId=" + checkId + ", memberId=" + memberId + ", date=" + date + ", sum=" + sum + "]";
	}

}
