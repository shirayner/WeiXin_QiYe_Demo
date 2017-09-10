package com.ray.pojo;

import java.util.List;

public class CheckIn {

	private int opencheckindatatype;
	private long starttime;
	private long endtime;
	private List<String> useridlist;
	
	public CheckIn(){} ;
	
	public CheckIn(int opencheckindatatype, long starttime, long endtime, List<String> useridlist) {
		super();
		this.opencheckindatatype = opencheckindatatype;
		this.starttime = starttime;
		this.endtime = endtime;
		this.useridlist = useridlist;
	}
	public int getOpencheckindatatype() {
		return opencheckindatatype;
	}
	public void setOpencheckindatatype(int opencheckindatatype) {
		this.opencheckindatatype = opencheckindatatype;
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
	public List<String> getUseridlist() {
		return useridlist;
	}
	public void setUseridlist(List<String> useridlist) {
		this.useridlist = useridlist;
	}

	@Override
	public String toString() {
		return "CheckIn [opencheckindatatype=" + opencheckindatatype + ", starttime=" + starttime + ", endtime="
				+ endtime + ", useridlist=" + useridlist + "]";
	}
	
	
	
}
