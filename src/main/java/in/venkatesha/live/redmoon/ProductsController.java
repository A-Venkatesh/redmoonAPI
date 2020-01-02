package in.venkatesha.live.redmoon;

import in.venkatesha.live.redmoon.autoFiller.Wild;
import in.venkatesha.live.redmoon.models.Products;
import in.venkatesha.live.redmoon.repositories.ProductsRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200",allowedHeaders="*")
@RequestMapping("/products")
public class ProductsController {
	@Autowired
	  private ProductsRepository repository;
	private Wild wild;


	  @RequestMapping(value = "/", method = RequestMethod.GET)
	  public List<Products> getAllProducts() {
	    return repository.findAll();
	  }
	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  public Products getProductById(@PathVariable("id") ObjectId id) {
	    return repository.findBy_id(id);
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	  public void modifyProductById(@PathVariable("id") ObjectId id, @Valid @RequestBody Products products) {
	    products.set_id(id);
	    repository.save(products);
	  }
	 
	  @RequestMapping(value = "/add", method = RequestMethod.POST)
	  public Products createProduct(@Valid @RequestBody Products products) {
		  System.out.println("ssssssssss");
	    products.set_id(ObjectId.get());
	    System.out.println("jsjnjs");
	    repository.save(products);
	    return products;
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	  public void deleteProduct(@PathVariable ObjectId id) {
	    repository.delete(repository.findBy_id(id));
	  }
	  
	  @RequestMapping(value = "/key/{keyword}", method = RequestMethod.GET)
	  public String getProductSuggestion(@PathVariable("keyword") String keyword) {
	    return wild.suggestion(keyword);
	  }
	}