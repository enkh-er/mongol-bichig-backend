package stud.enkherdene.mongolbichig.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import stud.enkherdene.mongolbichig.model.Menu;

@Repository
public interface MenuRepository extends MongoRepository<Menu,String> {
}
