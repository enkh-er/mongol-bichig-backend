package stud.enkherdene.mongolbichig.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import stud.enkherdene.mongolbichig.model.Category;

@Repository
public interface CategoryRepository extends MongoRepository<Category,String> {

}
