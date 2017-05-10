package com.jky.impl;

import java.sql.SQLException;

import com.jky.dao.ICheck;
import com.jky.db.DBConnection;
import com.jky.entity.Goods;

public class CheckImpl extends DBConnection implements ICheck{

	@Override
	public boolean addCheck(int memberId, String date, float sum) {

		conn = super.getConnection();
		try {
			String sql = "INSERT INTO `store`.`check` ( `memberId`, `date`, `sum`) VALUES ( '"+memberId+"', '"+date+"', '"+sum+"');";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			super.closeAll();
		}
		return true;
	}

	@Override
	public int getCheckId(String date) {

		conn = super.getConnection();
		int checkId=0;
		try {
			String sql = "SELECT * FROM store.`check` where date = '"+date+"';";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					checkId = rs.getInt("checkId");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return checkId;
	
	}

}
