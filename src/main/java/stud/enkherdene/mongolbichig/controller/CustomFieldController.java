package stud.enkherdene.mongolbichig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import stud.enkherdene.mongolbichig.model.CF;
import stud.enkherdene.mongolbichig.model.CustomField;
import stud.enkherdene.mongolbichig.repository.CustomFieldRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class CustomFieldController {
    @Autowired
    private CustomFieldRepository customFieldRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/create-custom-fields")
    public CustomField createCategory(@RequestBody CustomField customField) throws IOException {
        CustomField insertCF=customFieldRepository.insert(customField);
        return  insertCF;
    }

    @GetMapping("/custom-fields")
    public List<CustomField> getCF(){
        return customFieldRepository.findAll();
    }


    @GetMapping("/custom-fields-by-cat/{catId}")
    public List<CustomField> getMenus(@PathVariable String catId){
        List<CustomField>fields =new ArrayList<CustomField>();
        List<CustomField> lists=customFieldRepository.findAll();
        for (CustomField cf:lists ) {
            for (String cat:cf.getCategories() ) {
                if(cat.equals(catId)){
                    fields.add(cf);
                }
            }
        }
        return fields;
    }

    @DeleteMapping("/delete-cf-by-name/{id}/{name}")
    public void deleteCF(@PathVariable String id,@PathVariable String name){
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(id));
        CustomField customField=mongoTemplate.findOne(query,CustomField.class);
        if(customField!=null){
            List<CF> lists;
            if(customField.getFields().size()!=0){
                lists=customField.getFields();
                for (int i = 0; i < customField.getFields().size(); i++) {
                    if(customField.getFields().get(i).getName().equals(name)){
                        lists.remove(i);
                    }
                }
                customField.setFields(lists);
                mongoTemplate.save(customField);
                }
            }
    }
    @DeleteMapping("/delete-cf-by-catid/{id}/{catID}")
    public void deleteCat(@PathVariable String id,@PathVariable String catID){
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(id));
        CustomField customField=mongoTemplate.findOne(query,CustomField.class);
        if(customField!=null){
            List<String> lists;
            if(customField.getCategories().size()!=0){
                lists=customField.getCategories();
                for (int i = 0; i < customField.getCategories().size(); i++) {
                    if(customField.getCategories().get(i).equals(catID)){
                        lists.remove(i);
                    }
                }
                customField.setCategories(lists);
                mongoTemplate.save(customField);
            }
        }
    }

    @DeleteMapping("/delete-custom-fields/{id}")
    public void delete(@PathVariable String id){
        customFieldRepository.deleteById(id);
    }

    @PostMapping("/update-custom-fields")
    public void update(@ModelAttribute  CustomField customField) throws IOException {
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(customField.getId()));
        CustomField customField1=mongoTemplate.findOne(query,CustomField.class);
        customField1.setName(customField.getName());
        customField1.setFields(customField.getFields());
        customField1.setCategories(customField.getCategories());
        mongoTemplate.save(customField1);
    }
}
