package stud.enkherdene.mongolbichig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import stud.enkherdene.mongolbichig.model.Hicheel;
import stud.enkherdene.mongolbichig.repository.HicheelRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class HicheelController {
    @Autowired
    private HicheelRepository hicheelRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/hicheel")
    public String create(@RequestBody Hicheel hicheel) throws IOException {
        Hicheel p=hicheelRepository.insert(hicheel);
        return p.getId();
    }

    @GetMapping("/hicheelvvd")
    public List<Hicheel> getPosts() throws IOException {
        return hicheelRepository.findAll();
    }

    @GetMapping("/hicheel/{id}")
    public Optional<Hicheel> getPost(@PathVariable String id) throws IOException {
        return hicheelRepository.findById(id);
    }

    @GetMapping("/hicheel-by-cat/{catId}")
    public List<Hicheel> getHicheelByCat(@PathVariable String catId) throws IOException {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(catId));
        return mongoTemplate.find(query, Hicheel.class);
    }

    @DeleteMapping("/hicheel/{id}")
    public void delete(@PathVariable String id) {
        hicheelRepository.deleteById(id);
    }
}
