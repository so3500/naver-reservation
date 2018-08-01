package com.nts.pjt3.dto;

public class Promotion {

	private int id;
	private int productId;
	private int categoryId;
	private int productImageId;
	private String categoryName;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return String.format(
			"Promotion [id=%s, productId=%s, categoryId=%s, productImageId=%s, categoryName=%s, description=%s]", id,
			productId, categoryId, productImageId, categoryName, description);
	}

}
