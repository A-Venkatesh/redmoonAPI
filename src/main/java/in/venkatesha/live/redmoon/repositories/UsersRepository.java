package in.venkatesha.live.redmoon.repositories;

import in.venkatesha.live.redmoon.models.Users;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {

	Users findBy_id(ObjectId _id);
}
