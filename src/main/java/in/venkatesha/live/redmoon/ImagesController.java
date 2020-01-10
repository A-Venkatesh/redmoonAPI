package in.venkatesha.live.redmoon;


import in.venkatesha.live.redmoon.models.Image;
import in.venkatesha.live.redmoon.repositories.ImagesRepository;
import in.venkatesha.live.redmoon.service.Scrapper;

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
@RequestMapping("/image")
public class ImagesController {
	@Autowired
	  private ImagesRepository repository;
	


	  @RequestMapping(value = "/", method = RequestMethod.GET)
	  public List<Image> getAllImage() {
	    return repository.findAll();
	  }
	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  public Image getImageById(@PathVariable("id") ObjectId id) {
	    return repository.findBy_id(id);
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	  public void modifyImageById(@PathVariable("id") ObjectId id, @Valid @RequestBody Image image) {
	    image.set_id(id);
	    repository.save(image);
	  }
	 
	  @RequestMapping(value = "/add", method = RequestMethod.POST)
	  public Image createImage(@Valid @RequestBody Image image) {
		  System.out.println("ssssssssss");
	    image.set_id(ObjectId.get());
	    repository.save(image);
	    return image;
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	  public void deleteImage(@PathVariable ObjectId id) {
	    repository.delete(repository.findBy_id(id));
	  }
	}