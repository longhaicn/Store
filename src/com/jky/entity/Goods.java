package com.jky.entity;

public class Goods {
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getPositionId() {
		return positionId;
	}
	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}
	public int getKindId() {
		return kindId;
	}
	public void setKindId(int kindId) {
		this.kindId = kindId;
	}
	/**商品ID*/
	private int goodsId;
	/**商品名称*/
	private String name;
	/**商品二维码*/
	private String barCode;
	/**商品价格*/
	private float price;
	/**商品数量*/
	private int number;
	/**商品所在货架ID*/
	private int positionId;
	/**商品种类ID*/
	private int kindId;
	@Override
	public String toString() {
		return "Goods [goodsId=" + goodsId + ", name=" + name + ", barCode=" + barCode + ", price=" + price
				+ ", number=" + number + ", positionId=" + positionId + ", kindId=" + kindId + "]";
	}
}
