package stud.enkherdene.mongolbichig.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
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
    private String author;
    private String date;
    private List<String> categories;
    private String link;
    private Photo photo;
}
