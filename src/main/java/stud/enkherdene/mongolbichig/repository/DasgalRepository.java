package stud.enkherdene.mongolbichig.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import stud.enkherdene.mongolbichig.model.Dasgal;

@Repository
public interface DasgalRepository extends MongoRepository<Dasgal,String> {
}
