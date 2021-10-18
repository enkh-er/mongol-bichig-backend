package stud.enkherdene.mongolbichig.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import stud.enkherdene.mongolbichig.model.CustomField;

@Repository
public interface CustomFieldRepository extends MongoRepository<CustomField,String> {
}
