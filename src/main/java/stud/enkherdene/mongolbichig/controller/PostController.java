package stud.enkherdene.mongolbichig.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import stud.enkherdene.mongolbichig.model.Post;
import stud.enkherdene.mongolbichig.repository.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @PostMapping("/post")
    public String create(@RequestBody Post post) throws IOException {
        Post p=postRepository.insert(post);
        return p.getId();
    }

    @GetMapping("/post/{id}")
    public Optional<Post> getPost(@PathVariable String id) throws IOException {
        return postRepository.findById(id);
    }

    @GetMapping("/posts")
    public List<Post> getPosts() throws IOException {
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

    @GetMapping("/post-by-name/{title}")
    public List<Post> getPostsByName(@PathVariable String title) throws IOException {
        Query query = new Query();
        query.limit(10);
        query.addCriteria(Criteria.where("title").regex(title));
        return mongoTemplate.find(query,Post.class);
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
