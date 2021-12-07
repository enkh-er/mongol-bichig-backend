package stud.enkherdene.mongolbichig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import stud.enkherdene.mongolbichig.model.Dasgal;
import stud.enkherdene.mongolbichig.repository.DasgalRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class DasgalController {
    @Autowired
    private DasgalRepository dasgalRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/dasgal")
    public Dasgal createDasgal(@RequestBody Dasgal dasgal) throws IOException {
        Dasgal dasgal1=dasgalRepository.insert(dasgal);
        return  dasgal1;
    }

    @GetMapping("/dasgaluud")
    public List<Dasgal> getDasgaluud(){
        return dasgalRepository.findAll();
    }

    @GetMapping("/dasgal/{id}")
    public Optional<Dasgal> getDasgal(@PathVariable String id){
        return dasgalRepository.findById(id);
    }

    @DeleteMapping("/delete-dasgal/{id}")
    public void deleteDasgal(@PathVariable String id){
        dasgalRepository.deleteById(id);
    }

    @GetMapping("/dasgal-code/{code}")
    public List<Dasgal> getDByCode(@PathVariable String code){
        Query query=new Query();
        query.addCriteria(Criteria.where("code").is(code));
        return mongoTemplate.find(query,Dasgal.class);
    }

}
