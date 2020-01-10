package in.venkatesha.live.redmoon.models;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
public class Image {

	
	@Id
	public ObjectId _id;
	public String ImageDescription;
	public Image() {
		// TODO Auto-generated constructor stub
	}
	public ObjectId get_id() {
		return _id;
	}
	public void set_id(ObjectId _id) {
		this._id = _id;
	}
	public String getImageDescription() {
		return ImageDescription;
	}
	public void setImageDescription(String imageDescription) {
		ImageDescription = imageDescription;
	}

}
