package stud.enkherdene.mongolbichig.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "post")
public class Post {
    @Id
    private String id;
    private String title;
    private String content;
    private List<String> categories;
    private String link;
    private Photo photo;
}
