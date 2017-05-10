package com.jky.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jky.dao.IKind;
import com.jky.db.DBConnection;
import com.jky.entity.Kind;
import com.jky.entity.SaleRecord;

public class KindImpl extends DBConnection implements IKind {

	@Override
	public List<Kind> queryAllKind(String condition) {

		List<Kind> lists = null;
		Kind kind = null;
		conn = super.getConnection();

		String sql = "SELECT * FROM store.kind order by kindId;";
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				lists = new ArrayList<Kind>();
				while (rs.next()) {
					kind = new Kind();
					kind.setKindId(rs.getInt("kindId"));
					kind.setName(rs.getString("name"));

					lists.add(kind);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			super.closeAll();
		}
		return lists;

	}

	@Override
	public Vector queryAllKindToVector(List<Kind> lists) {
		Vector<Vector<Object>> temp = new Vector<Vector<Object>>();
		if (lists != null && lists.size() > 0) {
			for (Kind kind : lists) {
				// 一包烟
				Vector<Object> v = new Vector<>();
				v.add(kind.getKindId());
				v.add(kind.getName());

				temp.add(v);
			}
		}
		return temp;

	}

	@Override
	public boolean udrKind(String[] condition) {
		conn = super.getConnection();
		String sql = "";
		switch (condition[0]) {
		case "0"://添加
			sql="INSERT INTO `store`.`kind` (`name`) VALUES ('"+condition[2]+"');";
			break;
		case "1"://修改
			sql = "UPDATE `store`.`kind` SET `name`='"+condition[2]+"' WHERE `kindId`='"+condition[1]+"';";;
			break;
		case "2"://删除
			sql = "delete from `store`.`kind` where kindId='"+condition[1]+"'";
			break;
		default:
			break;
		}
		try {
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

}
