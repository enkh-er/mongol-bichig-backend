package stud.enkherdene.mongolbichig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import stud.enkherdene.mongolbichig.repository.CategoryRepository;
import stud.enkherdene.mongolbichig.model.Category;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/create-category")
    public Category createCategory(@ModelAttribute Category category) throws IOException {
        return  categoryRepository.insert(category);
    }

    @PostMapping("/update-category")
    public Category updateCategory(@ModelAttribute Category category) throws IOException {
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(category.getId()));
        Category cat=mongoTemplate.findOne(query,Category.class);
        cat.setCode(category.getCode());
        cat.setName(category.getName());
        cat.setLink(category.getLink());
        cat.setParent(category.getParent());
        cat.setDescription(category.getDescription());
        mongoTemplate.save(cat);
        return cat;
    }

    @DeleteMapping("/delete-category/{id}")
    public String deleteCategory(@PathVariable String id){
        categoryRepository.deleteById(id);
        return id;
    }

    @GetMapping("/categories")
    public List<Category> getCategories(){
        return categoryRepository.findAll();
    }

    @GetMapping("/category/{id}")
    public Optional<Category> getCategory(@PathVariable String id){
        return categoryRepository.findById(id);
    }

    @GetMapping("/category-code/{code}")
    public String getCategoryByCode(@PathVariable String code){
        Query query=new Query();
        query.addCriteria(Criteria.where("code").is(code));
        Category cat=mongoTemplate.findOne(query,Category.class);
        if(cat!=null){
            return cat.getId();
        }
        return "";
    }
}
