package stud.enkherdene.mongolbichig.controller;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stud.enkherdene.mongolbichig.model.Photo;
import stud.enkherdene.mongolbichig.model.Post;
import stud.enkherdene.mongolbichig.repository.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/post")
    public String create(@RequestParam("title") String title,
                       @RequestParam("categories") List<String> categories,
                       @RequestParam("content") String content,
                       @RequestParam("link") String link,
                       @RequestParam("image") MultipartFile image, Model model) throws IOException {
        Post post=new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCategories(categories);
        post.setLink(link);
        Photo photo=new Photo();
        photo.setName(image.getName());
        photo.setImage(new Binary(BsonBinarySubType.BINARY,image.getBytes()));
        post.setPhoto(photo);
//        System.out.println(post.getContent());
//        System.out.println(post.getCategories());
//        System.out.println(post.getImage());
//        System.out.println(post.getLink());
        Post p=postRepository.save(post);
        return p.getId();
    }

    @GetMapping("/posts")
    public List<Post> getPosts(@ModelAttribute Post post) throws IOException {
        return postRepository.findAll();
    }

    @GetMapping("/post-by-cat/{catId}")
    public List<Post> getPostsByCat(@PathVariable String catId) throws IOException {
        List<Post>posts =new ArrayList<Post>();
        List<Post> lists=postRepository.findAll();
        for (Post p:lists ) {
            for (String cat:p.getCategories() ) {
                if(cat.equals(catId)){
                    posts.add(p);
                }
            }
        }
        return posts;
    }

    @GetMapping("/post-link/{link}")
    public boolean checkLink(@PathVariable String link) throws IOException {
        Query query = new Query();
        query.addCriteria(Criteria.where("link").is(link));
        if(mongoTemplate.findOne(query, Post.class)==null){
            return true;
        }
        return false;
    }

    @PostMapping("/update-post")
    public void update(@ModelAttribute Post post) throws IOException {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(post.getId()));
        Post post1 = mongoTemplate.findOne(query, Post.class);
        post1 = post;
        mongoTemplate.save(post1);
    }

    @DeleteMapping("/post/{id}")
    public void delete(@PathVariable String id) {
        postRepository.deleteById(id);
    }
}
