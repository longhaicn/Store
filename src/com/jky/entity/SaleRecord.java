package com.jky.entity;

public class SaleRecord {

	/**销售记录ID*/
	private int saleRecordId;
	public int getSaleRecordId() {
		return saleRecordId;
	}
	public void setSaleRecordId(int saleRecordId) {
		this.saleRecordId = saleRecordId;
	}
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public float getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(float subTotal) {
		this.subTotal = subTotal;
	}
	/**销售订单ID*/
	private int checkId;
	/**销售的货品ID*/
	private int goodsId;
	/**销售的货品数量*/
	private int number;
	/**单笔销售总额*/
	private float subTotal;
	@Override
	public String toString() {
		return "SaleRecord [saleRecordId=" + saleRecordId + ", checkId=" + checkId + ", goodsId=" + goodsId
				+ ", number=" + number + ", subTotal=" + subTotal + "]";
	}

}
