package stud.enkherdene.mongolbichig.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import stud.enkherdene.mongolbichig.model.Post;

@Repository
public interface PostRepository  extends MongoRepository<Post,String> {
}
