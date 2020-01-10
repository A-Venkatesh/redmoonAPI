package in.venkatesha.live.redmoon.repositories;

import in.venkatesha.live.redmoon.models.Image;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImagesRepository extends MongoRepository<Image, String> {

	Image findBy_id(ObjectId _id);
}
