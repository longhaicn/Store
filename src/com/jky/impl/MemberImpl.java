package com.jky.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jky.dao.IMember;
import com.jky.db.DBConnection;
import com.jky.entity.Member;
import com.jky.utils.Tools;

public class MemberImpl extends DBConnection implements IMember {

	@Override
	public Member isLogin(String userName, String password) {
		// 连接数据库
		conn = super.getConnection();
		Member member = new Member();
		try {
			String sql = "select * from member where username='" + userName + "' and password='" + password + "';";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				while (rs.next()) {
					member.setMemberId(rs.getInt("memberId"));
					member.setUserName(rs.getString("userName"));
					member.setPassword(rs.getString("password"));
					member.setName(rs.getString("name"));
					member.setRole(rs.getInt("role"));
					member.setIsWork(rs.getInt("isWork"));
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return member;

	}

	@Override
	public boolean insertMember(Member m) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteMember(int memberId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateMember(int memberId) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Member> queryAllOder(String order) {
		conn = getConnection();
		Member member = null;
		List<Member> lists = null;
		String sql = "select * from store.member order by " + order;
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				lists = new ArrayList<Member>();
				while (rs.next()) {
					member = new Member();
					member.setMemberId(rs.getInt("memberId"));
					member.setUserName(rs.getString("userName"));
					member.setName(rs.getString("name"));
					member.setRole(rs.getInt("role"));
					member.setIsWork(rs.getInt("isWork"));
					// 添加到集合
					lists.add(member);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;
	}

	@Override
	public List<Member> queryAllCondition(String condition) {
		conn = getConnection();
		Member member = null;
		List<Member> lists = null;
		try {
			int parseInt = Integer.parseInt(condition);
			condition = "memberId like '%" + condition + "%';";

		} catch (Exception e) {
			if ("收银员".equals(condition) || "高管".equals(condition) || "仓管员".equals(condition)) {
				if ("收银员".equals(condition)) {
					condition = "role = 0;";
				} else if ("仓管员".equals(condition)) {
					condition = "role = 1;";
				} else if ("高管".equals(condition)) {
					condition = "role = 2;";
				}
			} else {
				condition = "name like '%" + condition + "%' or username like '%" + condition + "%';";
			}
		}

		String sql = "select * from store.member where " + condition;
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				lists = new ArrayList<Member>();
				while (rs.next()) {
					member = new Member();
					member.setMemberId(rs.getInt("memberId"));
					member.setUserName(rs.getString("userName"));
					member.setName(rs.getString("name"));
					member.setRole(rs.getInt("role"));
					member.setIsWork(rs.getInt("isWork"));
					// 添加到集合
					lists.add(member);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;

	}

	@Override
	public Vector<Vector<Object>> querAllMembers(List<Member> lists) {

		Vector<Vector<Object>> temp = new Vector<Vector<Object>>();
		if (lists != null && lists.size() > 0) {
			for (Member member : lists) {
				// 一包烟
				Vector<Object> v = new Vector<>();
				v.add(member.getMemberId());
				v.add(member.getUserName());
				v.add(member.getName());
				if (member.getRole() == 0) {
					v.add("收银员");
				} else if (member.getRole() == 1) {
					v.add("仓管员");
				} else {
					v.add("高管");
				}
				if (member.getIsWork() == 0) {
					v.add("离线");

				} else {
					v.add("正在上班");
				}
				temp.add(v);
			}
		}
		return temp;
	}

	@Override
	public boolean addMember(String addUserName, String addPassword, String addName, int index) {

		conn = super.getConnection();
		try {
			String sql = "INSERT INTO `store`.`member` (`userName`, `password`, `name`, `role`) VALUES ('" + addUserName
					+ "', '" + addPassword + "', '" + addName + "', '" + index + "');";
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
	public boolean userNameExisted(String addUserName) {
		conn = super.getConnection();
		try {
			String sql = "select * from store.member where userName = '" + addUserName + "'";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				if (rs.next()) {
					return true;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return false;
	}

	@Override
	public boolean delMember(String textDelUserName) {
		conn = super.getConnection();
		try {
			String sql = "delete from store.member where userName = '" + textDelUserName + "'";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			super.closeAll();
		}
		return false;
	}

	@Override
	public boolean updMember(String[] condition) {
		conn = super.getConnection();
		try {
			String sql = "UPDATE `store`.`member` SET ";
			if (!Tools.isEmpty(condition[0])) {
				sql = sql + "password='" + condition[0] + "',";
			}
			if (!Tools.isEmpty(condition[1])) {
				sql = sql + "name='" + condition[1] + "',";
			}
			sql = sql + "role='" + condition[2] + "'WHERE `username`='" + condition[3] + "';";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {

		} finally {
			super.closeAll();
		}

		return false;
	}

	@Override
	public boolean updMember(int memberId,boolean b) {
		conn = super.getConnection();
		try {
			String sql = "UPDATE `store`.`member` SET isWork = 0 where memberId = '"+memberId+"'";
			if (b) {
				sql = "UPDATE `store`.`member` SET isWork = 1 where memberId = '"+memberId+"'";
				
			}
			
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			if (pstmt.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {

		} finally {
			super.closeAll();
		}

		return false;
	}

}
