package in.venkatesha.live.redmoon.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Users {

	@Id
	public ObjectId _id;

	public String ProductName;
	public String ProductDescription;
	public String ProductPrice;

	public Users() {
		// TODO Auto-generated constructor stub
	}
	public Users(ObjectId _id, String productName, String productDescription, String productPrice) {
		super();
		this._id = _id;
		ProductName = productName;
		ProductDescription = productDescription;
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

	public String getProductPrice() {
		return ProductPrice;
	}

	public void setProductPrice(String productPrice) {
		ProductPrice = productPrice;
	}

}
