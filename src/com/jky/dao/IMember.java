package com.jky.dao;

import java.util.List;
import java.util.Vector;

import com.jky.entity.Member;

/**
 * 规定Member表的CRUD操作
 * @author Emily
 *
 */
public interface IMember {
	/**
	 * 用户登陆验证
	 * @param userName 用户名
	 * @param password 密码
	 * @param role 角色 0收银员 1仓库管理员2管理者
	 * @return
	 */
	public Member isLogin(String userName,String password);
	/**
	 * 这个方法描述的是：添加用户
	 * @author Dijkstra 2016年1月7日 上午10:03:10
	 * @param m
	 * @return
	 * @throws Exception
	 */
	public boolean insertMember(Member m) throws Exception;
	/**
	 * 这个方法描述的是：删除用户
	 * @author Dijkstra 2016年1月7日 上午10:03:23
	 * @param memberId
	 * @return
	 * @throws Exception
	 */
	public boolean deleteMember(int memberId) throws Exception;
	/**
	 * 这个方法描述的是：
	 * @author Dijkstra 2016年1月7日 上午10:03:41
	 * @param memberId
	 * @return boolean
	 * @throws Exception
	 */
	public boolean updateMember(int memberId) throws Exception;
	/**查询所有并按oder排序 返回List<Bean>集合  Bean就是实体
	 * 这个方法描述的是：
	 * @author Dijkstra 2015年12月21日 下午3:09:27
	 * @param oder
	 * @return boolean
	 * @throws Exception
	 */
	public List<Member> queryAllOder(String oder) ;
	/**
	 * 根据编号查/根据姓名查/根据角色这个方法描述的是：
	 * @author Dijkstra 2015年12月21日 下午3:10:20
	 * @param condition :ID,NAME,ROLE
	 * @return
	 * @throws Exception
	 */
	public List<Member> queryAllCondition(String condition);
	/**
	 * 这个方法描述的是：将lists转为vector
	 * @author Dijkstra 2015年12月21日 下午4:08:31
	 * @param list
	 * @return boolean
	 */
	public Vector<Vector<Object>> querAllMembers(List<Member> list);
	/**
	 * 这个方法描述的是：添加成员
	 * @author Dijkstra 2015年12月21日 下午6:52:41
	 * @param addUserName
	 * @param addPassword
	 * @param addName
	 * @param index
	 * @return boolean
	 */
	public boolean addMember(String addUserName, String addPassword, String addName, int index);
	/**
	 * 这个方法描述的是：检查用户是否已经存在
	 * @author Dijkstra 2015年12月21日 下午6:52:37
	 * @param addUserName
	 * @return true 用户名已存在
	 */
	public boolean userNameExisted(String addUserName);
	/**
	 * 这个方法描述的是：按账户名删除用户
	 * @author Dijkstra 2015年12月21日 下午11:06:35
	 * @param textDelUserName
	 * @return boolean
	 */
	public boolean delMember(String textDelUserName);
	/**
	 * 这个方法描述的是：更新成员信息
	 * @author Dijkstra 2016年1月7日 上午10:04:09
	 * @param condition
	 * @return boolean
	 */
	public boolean updMember(String[] condition);
	/**
	 * 这个方法描述的是：更新成员上班状态
	 * @author Dijkstra 2016年1月7日 上午10:04:44
	 * @param memberId
	 * @param b
	 * @return boolean
	 */
	public boolean updMember(int memberId,boolean b);
}







