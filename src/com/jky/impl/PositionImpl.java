package com.jky.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jky.dao.IPosition;
import com.jky.db.DBConnection;
import com.jky.entity.Kind;
import com.jky.entity.Position;

public class PositionImpl extends DBConnection implements IPosition{



	public List<Position> queryAllPosition(String condition) {

		List<Position> lists = null;

		Position position = null;
		conn = super.getConnection();

		String sql = "SELECT * FROM store.position order by positionId;";
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				lists = new ArrayList<Position>();
				while (rs.next()) {
					position = new Position();
					position.setPositionId(rs.getInt("positionId"));
					position.setName(rs.getString("name"));
					
					lists.add(position);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			super.closeAll();
		}
		return lists;

	
	}

	public Vector<Vector<Object>> queryAllGoodsToVector(List<Position> lists) {
		Vector<Vector<Object>> temp = new Vector<Vector<Object>>();
		if (lists != null && lists.size() > 0) {
			for (Position position : lists) {
				// 一包烟
				Vector<Object> v = new Vector<>();
				v.add(position.getPositionId());
				v.add(position.getName());
			
				temp.add(v);
			}
		}
		return temp;

	}

	public boolean udrPosition(String[] condition) {
		conn = super.getConnection();
		String sql = "";
		switch (condition[0]) {
		case "0"://添加
			sql="INSERT INTO `store`.`position` (`name`) VALUES ('"+condition[2]+"');";
			break;
		case "1"://修改
			sql = "UPDATE `store`.`position` SET `name`='"+condition[2]+"' WHERE `positionId`='"+condition[1]+"';";;
			break;
		case "2"://删除
			sql = "delete from `store`.`position` where positionId='"+condition[1]+"'";
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
