package com.jky.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jky.dao.ISaleRecord;
import com.jky.db.DBConnection;
import com.jky.entity.SaleRecord;
import com.jky.entity.WorkRecord;

public class SaleRecordImpl extends DBConnection implements ISaleRecord {

	@Override
	public boolean addSaleRecord(int checkId, int goodsId, int number, float sumUp) {

		conn = super.getConnection();
		try {
			String sql = "INSERT INTO `store`.`salerecord` ( `checkId`, `goodsId`, `number`, `subtotal`) VALUES ( '"
					+ checkId + "', '" + goodsId + "', '" + number + "', '" + sumUp + "');";

			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			super.closeAll();
		}
		return true;

	}

	@Override
	public List<SaleRecord> queryAllSale(String condition) {
		List<SaleRecord> lists = null;

		SaleRecord saleRecord = null;
		conn = super.getConnection();

		String sql = "SELECT * FROM store.salerecord;";
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				lists = new ArrayList<SaleRecord>();
				while (rs.next()) {
					saleRecord = new SaleRecord();
					saleRecord.setSaleRecordId(rs.getInt("salerecordId"));
					saleRecord.setCheckId(rs.getInt("checkId"));
					saleRecord.setGoodsId(rs.getInt("goodsId"));
					saleRecord.setNumber(rs.getInt("number"));
					saleRecord.setSubTotal(rs.getFloat("subtotal"));
					lists.add(saleRecord);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return lists;

	}

	@Override
	public Vector<Vector<Object>> queryAllSaleToVector(List<SaleRecord> lists) {
		Vector<Vector<Object>> temp = new Vector<Vector<Object>>();
		if (lists != null && lists.size() > 0) {
			for (SaleRecord saleRecord : lists) {
				// 一包烟
				Vector<Object> v = new Vector<>();
				v.add(saleRecord.getSaleRecordId());
				v.add(saleRecord.getCheckId());
				v.add(saleRecord.getGoodsId());
				v.add(saleRecord.getNumber());
				v.add(saleRecord.getSubTotal());
				temp.add(v);
			}
		}
		return temp;

	}

	@Override
	public int[] countKindSale(int[] kindId) {
		int length = kindId.length;
		int results[] = new int[length];
		String sql = "";
		for (int i = 0; i < length ; i++) {
				sql = "select  sum(subtotal) from salerecord,goods where salerecord.goodsId = goods.goodsId and kindId='"
						+ kindId[i ] + "'";
		
			try {
				conn = super.getConnection();
				pstmt = conn.prepareStatement(sql);
				System.out.println(sql);

				ResultSet rs = pstmt.executeQuery();
				if (rs != null) {
					while (rs.next()) {
						results[i] = rs.getInt(1);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				super.closeAll();
			}
		}

		return results;
	}

}
