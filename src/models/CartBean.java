package models;

import java.io.Serializable;

public class CartBean implements Serializable {
	private String name=null;
	private int price=0;
	private int kosuu=0;
	private int proCd;
	private int stockNo;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getKosuu() {
		return kosuu;
	}
	public void setKosuu(int kosuu) {
		this.kosuu = kosuu;
	}
	public int getProCd() {
		return proCd;
	}
	public void setProCd(int proCd) {
		this.proCd = proCd;
	}
	public int getStockNo() {
		return stockNo;
	}
	public void setStockNo(int stockNo) {
		this.stockNo = stockNo;
	}



}
