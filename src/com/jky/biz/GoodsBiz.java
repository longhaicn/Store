package com.jky.biz;

import java.util.List;
import java.util.Vector;

import com.jky.entity.Goods;
import com.jky.entity.GoodsPlus;
import com.jky.factory.Factory;

public class GoodsBiz {
	public Goods getTheGood(String barCode){
		return Factory.getIGoodsInstance().getTheGood(barCode);
		
	}
	public boolean subGoodsNumber(int goodsId,int number) {
		return Factory.getIGoodsInstance().subGoodsNumber(goodsId,number);
	}
	public List<GoodsPlus> queryAllGoods(String condition) {
		// TODO Auto-generated method stub
		return Factory.getIGoodsInstance().queryAllGoods(condition);
	}
	public Vector<Vector<Object>> queryAllGoodsToVector(List<GoodsPlus> listsGoods) {
		// TODO Auto-generated method stub
		return Factory.getIGoodsInstance().queryAllGoodsToVector(listsGoods);
	}
	public boolean addGoods(Goods goods) {
		return Factory.getIGoodsInstance().addGoods(goods);
		
	}
	public boolean delGoods(String barCode) {
		// TODO Auto-generated method stub
		return Factory.getIGoodsInstance().delGoods(barCode);
	}
	public boolean UpdGoods(Goods goods) {
		// TODO Auto-generated method stub
		return Factory.getIGoodsInstance().updGoods(goods);
	}
	public Goods selGoods(String barcode) {
		// TODO Auto-generated method stub
		return Factory.getIGoodsInstance().selGoods(barcode);
	}
	public List<Goods> queryAllGoods(int kindId) {
		// TODO Auto-generated method stub
		return Factory.getIGoodsInstance().queryAllGoods(kindId);
	}
}
