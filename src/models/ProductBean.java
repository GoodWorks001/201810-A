package models;

import java.io.Serializable;

public class ProductBean implements Serializable {


	private int proCd;
	private String proName;
	private int stockNo;
	private int proPrice;
	private String catName;
	private String proImg;
	private String proMsg;

	public int getProCd() {
		return proCd;
	}
	public void setProCd(int proCd) {
		this.proCd = proCd;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public int getStockNo() {
		return stockNo;
	}
	public void setStockNo(int stockNo) {
		this.stockNo = stockNo;
	}
	public int getProPrice() {
		return proPrice;
	}
	public void setProPrice(int proPrice) {
		this.proPrice = proPrice;
	}
	public String getCatName() {
		return catName;
	}
	public void setCatName(String catName) {
		this.catName = catName;
	}
	public String getProImg() {
		return proImg;
	}
	public void setProImg(String proImg) {
		this.proImg = proImg;
	}
	public String getProMsg() {
		return proMsg;
	}
	public void setProMsg(String proMsg) {
		this.proMsg = proMsg;
	}

}
