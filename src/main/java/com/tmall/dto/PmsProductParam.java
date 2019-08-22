package com.tmall.dto;

import com.tmall.model.PmsProduct;

public class PmsProductParam extends PmsProduct {

	@Override
	public String toString() {
		return "PmsProductParam [getId()=" + getId() + ", getName()=" + getName() + ", getPic()=" + getPic()
				+ ", getProductSn()=" + getProductSn() + ", getDeleteStatus()=" + getDeleteStatus()
				+ ", getPublishStatus()=" + getPublishStatus() + ", getNewStatus()=" + getNewStatus()
				+ ", getRecommandStatus()=" + getRecommandStatus() + ", getVerifyStatus()=" + getVerifyStatus()
				+ ", getSort()=" + getSort() + ", getSale()=" + getSale() + ", getPrice()=" + getPrice()
				+ ", getPromotionPrice()=" + getPromotionPrice() + ", getSubTitle()=" + getSubTitle()
				+ ", getDescription()=" + getDescription() + ", getOriginalPrice()=" + getOriginalPrice()
				+ ", getStock()=" + getStock() + ", getLowStock()=" + getLowStock() + ", getUnit()=" + getUnit()
				+ ", getWeight()=" + getWeight() + ", getPreviewStatus()=" + getPreviewStatus() + ", getServiceIds()="
				+ getServiceIds() + ", getKeywords()=" + getKeywords() + ", getNote()=" + getNote()
				+ ", getAlbumPics()=" + getAlbumPics() + ", getBrandName()=" + getBrandName() + ", getBrandId()="
				+ getBrandId() + ", getProductCategoryName()=" + getProductCategoryName() + ", getProductCategoryId()="
				+ getProductCategoryId() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	
}
