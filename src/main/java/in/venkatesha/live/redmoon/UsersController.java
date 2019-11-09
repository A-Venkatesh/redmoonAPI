package in.venkatesha.live.redmoon;

import in.venkatesha.live.redmoon.models.Users;
import in.venkatesha.live.redmoon.repositories.UsersRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersController {
	@Autowired
	  private UsersRepository repository;

	  @RequestMapping(value = "/", method = RequestMethod.GET)
	  public List<Users> getAllUsers() {
	    return repository.findAll();
	  }
	  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
	  public Users getUserById(@PathVariable("id") ObjectId id) {
	    return repository.findBy_id(id);
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	  public void modifyUserById(@PathVariable("id") ObjectId id, @Valid @RequestBody Users pets) {
	    pets.set_id(id);
	    repository.save(pets);
	  }
	 
	  @RequestMapping(value = "/", method = RequestMethod.POST)
	  public Users createUser(@Valid @RequestBody Users pets) {
	    pets.set_id(ObjectId.get());
	    repository.save(pets);
	    return pets;
	  }
	 
	  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	  public void deleteUser(@PathVariable ObjectId id) {
	    repository.delete(repository.findBy_id(id));
	  }
	}