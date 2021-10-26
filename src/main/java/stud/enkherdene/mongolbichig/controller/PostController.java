package stud.enkherdene.mongolbichig.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import stud.enkherdene.mongolbichig.model.Post;
import stud.enkherdene.mongolbichig.model.PostCF;
import stud.enkherdene.mongolbichig.repository.PostRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
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
                         @RequestParam("author") String author,
                         @RequestParam("date") String date,
                         @RequestParam("acf") List<PostCF> acf,
                       @RequestParam(value = "image",required = false) MultipartFile image, Model model) throws IOException {
        Post post=new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setCategories(categories);
        post.setAuthor(author);
        post.setDate(date);
        post.setLink(link);
//        ObjectMapper mapper = new ObjectMapper();
//        List<PostCF> acfs = new ArrayList<>();
        // 1. convert JSON array to Array objects
//        PostCF[] pp1 = mapper.readValue(acf, PostCF[].class);
//        List<PostCF> ppl2 = Arrays.asList(mapper.readValue(acf, PostCF[].class));
//        post.setAcf(ppl2);
        if(image!=null &&!image.isEmpty()){
            post.setPhoto(Base64.getEncoder().encodeToString(image.getBytes()));
        }
        Post p=postRepository.save(post);
        return p.getId();
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
