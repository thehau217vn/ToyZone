package com.toyZone.dto;

import java.util.ArrayList;
import java.util.List;

public class SessionGioHang {
	
	private int totalItem = 0;
	private String totalPrice = "0";
	private List<ProductOrderDto> gioHangs = new ArrayList<ProductOrderDto>();
	
	public SessionGioHang() {
		super();
		
	}
	
	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public String getTotalPrice() {	
		return totalPrice;
	}

	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}

	public List<ProductOrderDto> getGioHangs() {
		return gioHangs;
	}
	public void setGioHangs(List<ProductOrderDto> gioHangs) {
		this.gioHangs = gioHangs;
	}
	
}
