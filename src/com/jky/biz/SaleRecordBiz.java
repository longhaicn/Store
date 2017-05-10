package com.jky.biz;

import java.util.List;
import java.util.Vector;

import com.jky.entity.SaleRecord;
import com.jky.factory.Factory;

public class SaleRecordBiz {
	public boolean addSaleRecord(int checkId, int goodsId, int number, float sumUp) {
		return Factory.getISaleRecordInstance().addSaleRecord( checkId,goodsId, number,sumUp);
		
	}

	public List<SaleRecord> queryAllSale(String condition) {
		// TODO Auto-generated method stub
		return Factory.getISaleRecordInstance().queryAllSale(condition);
	}

	public Vector<Vector<Object>> queryAllSaleToVector(List<SaleRecord> listsSale) {
		// TODO Auto-generated method stub
		return Factory.getISaleRecordInstance().queryAllSaleToVector(listsSale);
	}

	public int[] countKindSale(int[] kindId) {
		// TODO Auto-generated method stub
		return Factory.getISaleRecordInstance().countKindSale(kindId);
	}


}
