package stud.enkherdene.mongolbichig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import stud.enkherdene.mongolbichig.model.Menu;
import stud.enkherdene.mongolbichig.repository.MenuRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class MenuController {
    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/create-menu")
    public void create(@ModelAttribute Menu menu) throws IOException {
        String parent=menu.getParent();
        System.out.println(menu.getParent());
        if(parent!=null && parent.trim().length()!=0){
            addChild(parent, menu);
        }
        else {
        System.out.println(menu.getParent());
            menuRepository.insert(menu);
        }
    }
    @PostMapping("/update-menu")
    public void update(@ModelAttribute Menu menu) throws IOException {
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(menu.getId()));
        Menu menu1=mongoTemplate.findOne(query,Menu.class);
        menu1.setCode(menu.getCode());
        menu1.setChild_items(menu.getChild_items());
        menu1.setLink(menu.getLink());
        menu1.setParent(menu.getParent());
        menu1.setName(menu.getName());
        menu1.setType(menu.getType());
        menu1.setTypeId(menu.getTypeId());
        mongoTemplate.save(menu1);
    }

    public void addChild(String id,Menu menu){
        System.out.println("add child");
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Menu menu1=mongoTemplate.findOne(query,Menu.class);
        if(menu1!=null){
            List<Menu> lists;
            if(menu1.getChild_items()!=null){
                lists=menu1.getChild_items();
                lists.add(menu);
            }
            else {
                lists=new ArrayList<Menu>();
                lists.add(menu);
            }
            menu1.setChild_items(lists);
            mongoTemplate.save(menu1);
        }
    }


    @DeleteMapping("/delete-menu/{id}/{name}")
    public void delete(@PathVariable String id,@PathVariable String name){
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Menu menu1=mongoTemplate.findOne(query,Menu.class);
        if(menu1!=null){
            List<Menu> lists;
            if(menu1.getChild_items()!=null){
                if(menu1.getName().equals(name)){
                    menuRepository.deleteById(id);
                }
                else{
                    lists=menu1.getChild_items();
                    for (int i=0; i<lists.size();i++){
                        if(lists.get(i).getName().equals(name)){
                            lists.remove(i);
                        }
                    }
                    menu1.setChild_items(lists);
                    mongoTemplate.save(menu1);
                }
            }
            else {
                menuRepository.deleteById(id);
            }
        }
    }

    @GetMapping("/menus")
    public List<Menu> getMenus(){
        return menuRepository.findAll();
    }


    @GetMapping("/menus/{code}")
    public List<Menu> getMenusByCode(@PathVariable String code){
        Query query=new Query();
        query.addCriteria(Criteria.where("code").is(code));
        return mongoTemplate.find(query,Menu.class);
    }


    @GetMapping("/menu/{id}")
    public Optional<Menu> getMenu(@PathVariable String id){
        return menuRepository.findById(id);
    }


}
