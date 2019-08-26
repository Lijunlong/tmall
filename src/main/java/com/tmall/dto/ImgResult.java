package com.tmall.dto;

public class ImgResult {
	
	private String img;
	private String uuid;
	public ImgResult() {
		super();
	}
	public ImgResult(String img, String uuid) {
		super();
		this.img = img;
		this.uuid = uuid;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
