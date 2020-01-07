package in.venkatesha.live.redmoon.models;

import java.util.List;

public class Book {

	String Authors;
	String Title;
	String Publisher;
	String Binding;
	String Url;
	String imgUrl;
	String mrp;
	String price;
	String productDetail;
	String productDescription;
	List<String> segment ;
	public Book() {
		// TODO Auto-generated constructor stub
	}

	public String getAuthors() {
		return Authors;
	}

	public void setAuthors(String authors) {
		Authors = authors.trim();
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title.trim();
	}

	public String getPublisher() {
		return Publisher;
	}

	public void setPublisher(String publisher) {
		Publisher = publisher.trim();
	}

	public String getBinding() {
		return Binding;
	}

	public void setBinding(String binding) {
		Binding = binding.trim();
	}

	public String getUrl() {
		return Url;
	}

	public void setUrlISDB(String url) {
		
		Url = url.substring(url.indexOf("\"")+1, url.lastIndexOf("\""));
	}
public void setUrl(String url) {
		
		Url=url;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getMrp() {
		return mrp;
	}

	public void setMrp(String mrp) {
		this.mrp = mrp;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public List<String> getSegment() {
		return segment;
	}

	public void setSegment(List<String> segment) {
		this.segment = segment;
	}

}
