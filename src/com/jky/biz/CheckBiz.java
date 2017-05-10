package com.jky.biz;

import com.jky.factory.Factory;

public class CheckBiz {

	public CheckBiz() {
		// TODO Auto-generated constructor stub
	}
	public boolean addCheck(int memberId, String date, float sum){
		return Factory.getICheckInstance().addCheck(memberId, date, sum);
	}
	public int getCheckId(String date) {
		// TODO Auto-generated method stub
		return Factory.getICheckInstance().getCheckId(date);
	}

}
