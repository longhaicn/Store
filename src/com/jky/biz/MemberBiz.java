package com.jky.biz;

import java.util.List;
import java.util.Vector;

import com.jky.entity.Member;
import com.jky.factory.Factory;

//业务层一般有接口和实现类 安全性
public class MemberBiz {
	public Member isCheckLogin(String userName,String password){
		//登陆谁做好了？dao
		return Factory.getIMeberInstance().isLogin(userName, password);
	}
	public List<Member> queryAllOder(String order) {
		
		return Factory.getIMeberInstance().queryAllOder(order);
		
	}
	public List<Member> queryAllCondition(String condition) {
		
		return Factory.getIMeberInstance().queryAllCondition(condition);
		
	}
	public Vector<Vector<Object>> querAllMembers(List<Member> lists) {
		
		return Factory.getIMeberInstance().querAllMembers(lists);
		
	}
	/**
	 * 表格中的行用
	 * @return
	 */
	public boolean addMember(String addUserName, String addPassword, String addName, int index) {
		// TODO Auto-generated method stub
		return Factory.getIMeberInstance().addMember(addUserName,addPassword,addName,index);
	}
	public boolean userNameExisted(String addUserName) {
		// TODO Auto-generated method stub
		return Factory.getIMeberInstance().userNameExisted(addUserName);
	}
	public boolean delMember(String textDelUserName) {
		// TODO Auto-generated method stub
		return Factory.getIMeberInstance().delMember(textDelUserName);
	}
	public boolean updMember(String[] condition) {
		
		return Factory.getIMeberInstance().updMember(condition);
	}
	public boolean updMember(int memberId ,boolean b) {
		return Factory.getIMeberInstance().updMember(memberId,b);
		
	}

}
