package com.jky.entity;

public class WorkRecord {

	public int getWorkRecordId() {
		return workRecordId;
	}
	public void setWorkRecordId(int workRecordId) {
		this.workRecordId = workRecordId;
	}
	public int getMemeberId() {
		return memeberId;
	}
	public void setMemeberId(int memeberId) {
		this.memeberId = memeberId;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getLastTime() {
		return lastTime;
	}
	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}
	/**工作记录ID*/
	private int workRecordId;

	/**成员ID*/
	private int memeberId;
	/**开始工作时间*/
	private String startTime;
	/**工作持续时间*/
	private String lastTime;
	@Override
	public String toString() {
		return "WorkRecord [workRecordId=" + workRecordId + ", memeberId=" + memeberId + ", startTime=" + startTime
				+ ", lastTime=" + lastTime + "]";
	}


}
