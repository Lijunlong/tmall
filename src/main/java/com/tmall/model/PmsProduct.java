package com.tmall.model;

import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModelProperty;
/**
 * 商品信息
 * Created by Mr.Li on 2019/07/26
 */
public class PmsProduct implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "主键")
	private Long id;

	@ApiModelProperty(value = "商品名称")
	private String name;

	@ApiModelProperty(value = "商品图片地址")
	private String pic;

	@ApiModelProperty(value = "货号")
	private String productSn;

	@ApiModelProperty(value = "删除状态：0->未删除；1->已删除")
	private Integer deleteStatus;

	@ApiModelProperty(value = "上架状态：0->下架；1->上架")
	private Integer publishStatus;

	@ApiModelProperty(value = "新品状态:0->不是新品；1->新品")
	private Integer newStatus;

	@ApiModelProperty(value = "推荐状态；0->不推荐；1->推荐")
	private Integer recommandStatus;

	@ApiModelProperty(value = "审核状态：0->未审核；1->审核通过")
	private Integer verifyStatus;

	@ApiModelProperty(value = "排序")
	private Integer sort;

	@ApiModelProperty(value = "销量")
	private Integer sale;

	@ApiModelProperty(value = "价格")
	private BigDecimal price;

	@ApiModelProperty(value = "促销价格")
	private BigDecimal promotionPrice;

	@ApiModelProperty(value = "副标题")
	private String subTitle;

	@ApiModelProperty(value = "商品描述")
	private String description;

	@ApiModelProperty(value = "市场价")
	private BigDecimal originalPrice;

	@ApiModelProperty(value = "库存")
	private Integer stock;

	@ApiModelProperty(value = "库存预警值")
	private Integer lowStock;

	@ApiModelProperty(value = "单位")
	private String unit;

	@ApiModelProperty(value = "商品重量，默认为克")
	private BigDecimal weight;

	@ApiModelProperty(value = "是否为预告商品：0->不是；1->是")
	private Integer previewStatus;

	@ApiModelProperty(value = "以逗号分割的产品服务：1->无忧退货；2->快速退款；3->免费包邮")
	private String serviceIds;

	@ApiModelProperty(value = "关键字")
	private String keywords;

	@ApiModelProperty(value = "备注信息")
	private String note;

	@ApiModelProperty(value = "画册图片，连产品图片限制为5张，以逗号分割")
	private String albumPics;

	@ApiModelProperty(value = "品牌名称")
	private String brandName;
	
	@ApiModelProperty(value = "品牌id")
	private Long brandId;

	@ApiModelProperty(value = "商品分类名称")
	private String productCategoryName;
	
	@ApiModelProperty(value = "商品分类id")
	private Long productCategoryId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getProductSn() {
		return productSn;
	}

	public void setProductSn(String productSn) {
		this.productSn = productSn;
	}

	public Integer getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(Integer deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Integer getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}

	public Integer getNewStatus() {
		return newStatus;
	}

	public void setNewStatus(Integer newStatus) {
		this.newStatus = newStatus;
	}

	public Integer getRecommandStatus() {
		return recommandStatus;
	}

	public void setRecommandStatus(Integer recommandStatus) {
		this.recommandStatus = recommandStatus;
	}

	public Integer getVerifyStatus() {
		return verifyStatus;
	}

	public void setVerifyStatus(Integer verifyStatus) {
		this.verifyStatus = verifyStatus;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Integer getSale() {
		return sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getOriginalPrice() {
		return originalPrice;
	}

	public void setOriginalPrice(BigDecimal originalPrice) {
		this.originalPrice = originalPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getLowStock() {
		return lowStock;
	}

	public void setLowStock(Integer lowStock) {
		this.lowStock = lowStock;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public Integer getPreviewStatus() {
		return previewStatus;
	}

	public void setPreviewStatus(Integer previewStatus) {
		this.previewStatus = previewStatus;
	}

	public String getServiceIds() {
		return serviceIds;
	}

	public void setServiceIds(String serviceIds) {
		this.serviceIds = serviceIds;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getAlbumPics() {
		return albumPics;
	}

	public void setAlbumPics(String albumPics) {
		this.albumPics = albumPics;
	}

	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public Long getBrandId() {
		return brandId;
	}

	public void setBrandId(Long brandId) {
		this.brandId = brandId;
	}

	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public Long getProductCategoryId() {
		return productCategoryId;
	}

	public void setProductCategoryId(Long productCategoryId) {
		this.productCategoryId = productCategoryId;
	}

}