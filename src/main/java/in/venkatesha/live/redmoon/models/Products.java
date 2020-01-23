package in.venkatesha.live.redmoon.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Products {

	

	@Id
	public ObjectId _id;

	public String ProductName;
	public String ProductOwner;
	public String ProductDescription;
	public int ProductPrice;
	public int ProductMRP;
	public String[] ProductCategory;
	public int AgeGroup;
	public String ProductDetail;
	public String[] ProductImages;
	public String[] UploadedImages;

	public Products() {
		// TODO Auto-generated constructor stub
	}
	public Products(ObjectId _id, String productName, String productOwner, String productDescription, int productPrice,
			int productMRP, String[] productCategory, int ageGroup, String productDetail, String[] productImages,
			String[] uploadedImages) {
		super();
		this._id = _id;
		ProductName = productName;
		ProductOwner = productOwner;
		ProductDescription = productDescription;
		ProductPrice = productPrice;
		ProductMRP = productMRP;
		ProductCategory = productCategory;
		AgeGroup = ageGroup;
		ProductDetail = productDetail;
		ProductImages = productImages;
		UploadedImages = uploadedImages;
	}
	
	public String getProductOwner() {
		return ProductOwner;
	}
	public void setProductOwner(String productOwner) {
		ProductOwner = productOwner;
	}
	public int getProductMRP() {
		return ProductMRP;
	}
	public void setProductMRP(int productMRP) {
		ProductMRP = productMRP;
	}
	public String[] getProductCategory() {
		return ProductCategory;
	}
	public void setProductCategory(String[] productCategory) {
		ProductCategory = productCategory;
	}
	public int getAgeGroup() {
		return AgeGroup;
	}
	public void setAgeGroup(int ageGroup) {
		AgeGroup = ageGroup;
	}
	public String getProductDetail() {
		return ProductDetail;
	}
	public void setProductDetail(String productDetail) {
		ProductDetail = productDetail;
	}
	public String[] getProductImages() {
		return ProductImages;
	}
	public void setProductImages(String[] productImages) {
		ProductImages = productImages;
	}
	public String[] getUploadedImages() {
		return UploadedImages;
	}
	public void setUploadedImages(String[] uploadedImages) {
		UploadedImages = uploadedImages;
	}
	public void setProductPrice(int productPrice) {
		ProductPrice = productPrice;
	}
	public String get_id() {
		return _id.toHexString();
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public String getProductName() {
		return ProductName;
	}

	public void setProductName(String productName) {
		ProductName = productName;
	}

	public String getProductDescription() {
		return ProductDescription;
	}

	public void setProductDescription(String productDescription) {
		ProductDescription = productDescription;
	}



}
