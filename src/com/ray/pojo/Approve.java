package com.ray.pojo;

public class Approve {
	private long starttime;
	private long endtime;
	private long next_spnum;


	public Approve(long starttime, long endtime) {
		super();
		this.starttime = starttime;
		this.endtime = endtime;
	}
	public Approve(long starttime, long endtime, long next_spnum) {
		super();
		this.starttime = starttime;
		this.endtime = endtime;
		this.next_spnum = next_spnum;
	}
	public long getStarttime() {
		return starttime;
	}
	public void setStarttime(long starttime) {
		this.starttime = starttime;
	}
	public long getEndtime() {
		return endtime;
	}
	public void setEndtime(long endtime) {
		this.endtime = endtime;
	}
	public long getNext_spnum() {
		return next_spnum;
	}
	public void setNext_spnum(long next_spnum) {
		this.next_spnum = next_spnum;
	}





}
