package com.jky.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import com.jky.dao.IGoods;
import com.jky.db.DBConnection;
import com.jky.entity.Goods;
import com.jky.entity.GoodsPlus;
import com.jky.entity.Kind;

public class GoodsImpl extends DBConnection implements IGoods{

	public GoodsImpl() {
		
	}

	@Override
	public Goods getTheGood(String barCode) {


		//连接数据库
		conn = super.getConnection();
		Goods goods = new Goods();
		try {
			String sql = "SELECT * FROM store.goods where barcode = '"+barCode+"';";
			pstmt = conn.prepareStatement(sql);
			System.out.println(sql);
			rs = pstmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					goods.setGoodsId(rs.getInt("goodsId"));
					goods.setName(rs.getString("name"));
					goods.setBarCode(rs.getString("barcode"));
					goods.setPrice(rs.getFloat("price"));
					goods.setNumber(rs.getInt("number"));
					goods.setPositionId(rs.getInt("positionId"));
					goods.setKindId(rs.getInt("kindId"));
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			super.closeAll();
		}
		return goods;
	
	}

	@Override
	public boolean subGoodsNumber(int goodsId,int number) {
		conn = super.getConnection();
		Goods goods = new Goods();
		try {
			String sql = "UPDATE `store`.`goods` SET `number`='"+number+"' WHERE `goodsId`='"+goodsId+"';";
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
	public List<GoodsPlus> queryAllGoods(String condition) {
		conn=super.getConnection();
		List<GoodsPlus> lists = null;
		GoodsPlus goodsPlus = null;
		String sql="SELECT * FROM store.goods   join store.kind on kind.kindId=goods.kindId join store.position on goods.positionId=position.positionId";
		try {
			System.out.println(condition);
			int index = Integer.parseInt(condition);
			if (index==0) {
				sql=sql+" order by goods.goodsId;";
			}else if(index==1){
				sql=sql+" order by goods.price;";
			}else if(index==2){
				sql=sql+" order by goods.number;";
			}else if(index==3){
				sql=sql+" order by goods.positionId;";
			}
		} catch (Exception e) {//请输入商品名称/二维码/货架名
			if (condition.length()>8) {
				sql=sql+" where goods.name like '%"+condition+"%' or goods.barcode like '%"+condition+"%'";
			}else{
				
				sql=sql+" where goods.name like '%"+condition+"%' or position.name like '%"+condition+"%'" ;
			}
		}

		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				lists = new ArrayList<GoodsPlus>();
				while (rs.next()) {
					goodsPlus = new GoodsPlus();
					goodsPlus.setGoodsId(rs.getInt("goodsId"));
					goodsPlus.setNameGoods(rs.getString("goods.name"));
					goodsPlus.setBarCode(rs.getString("barcode"));
					goodsPlus.setPrice(rs.getFloat("price"));
					goodsPlus.setNumber(rs.getInt("number"));
					goodsPlus.setNamePosition(rs.getString("position.name"));
					goodsPlus.setNameKind(rs.getString("kind.name"));
					
					// 添加到集合
					lists.add(goodsPlus);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lists;

	}

	@Override
	public Vector<Vector<Object>> queryAllGoodsToVector(List<GoodsPlus> lists) {
		Vector<Vector<Object>> temp = new Vector<Vector<Object>>();
		if (lists != null && lists.size() > 0) {
			for (GoodsPlus goodsPlus : lists) {
				// 一包烟
				Vector<Object> v = new Vector<>();
				v.add(goodsPlus.getGoodsId());
				v.add(goodsPlus.getNameGoods());
				v.add(goodsPlus.getBarCode());
				v.add(goodsPlus.getPrice());
				v.add(goodsPlus.getNumber());
				v.add(goodsPlus.getNamePosition());
				v.add(goodsPlus.getNameKind());
			
				temp.add(v);
			}
		}
		return temp;
	}

	@Override
	public boolean addGoods(Goods goods) {

		conn = super.getConnection();
		try {
			String sql = "INSERT INTO `store`.`goods` ( `name`, `barcode`, `price`,`number`,`positionId`,`kindId`) VALUES ( '"+goods.getName()+"', '"+goods.getBarCode()+"', '"+goods.getPrice()+"', '"+goods.getNumber()+"', '"+goods.getPositionId()+"', '"+goods.getKindId()+"');";

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
	public boolean delGoods(String barCode) {

		conn = super.getConnection();
		try {
			String sql = "delete from store.goods where barcode='"+barCode+"';";
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
	public boolean updGoods(Goods goods) {

		conn = super.getConnection();
		try {
			String sql = "UPDATE `store`.`goods` SET `name`='"+goods.getName()+"', `price`='"+goods.getPrice()+"', `number`='"+goods.getNumber()+"', `positionId`='"+goods.getPositionId()+"', `kindId`='"+goods.getKindId()+"' WHERE `barcode`='"+goods.getBarCode()+"';";
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
	public Goods selGoods(String barcode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Goods> queryAllGoods(int kindId) {

		List<Goods> lists = null;

		Goods goods = null;
		conn = super.getConnection();

		String sql = "SELECT * FROM store.goods where kindId='"+kindId+"';";
		System.out.println(sql);
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs != null) {
				lists = new ArrayList<Goods>();
				while (rs.next()) {
					goods = new Goods();
					goods.setGoodsId(rs.getInt("goodsId"));
					goods.setKindId(rs.getInt("kindId"));
					goods.setName(rs.getString("name"));
					goods.setBarCode(rs.getString("barcode"));
					lists.add(goods);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			super.closeAll();
		}
		return lists;

	}
}
