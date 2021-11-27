package stud.enkherdene.mongolbichig.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import stud.enkherdene.mongolbichig.model.Hicheel;

@Repository
public interface HicheelRepository extends MongoRepository<Hicheel, String> {
}
