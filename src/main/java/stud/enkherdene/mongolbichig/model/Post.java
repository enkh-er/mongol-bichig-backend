package stud.enkherdene.mongolbichig.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "post")
public class Post implements Serializable {
    @Id
    private String id;
    private String title;
    private String content;
    private String author;
    private String date;
    private List<String> categories;
    private String link;
    private String image;

    @JsonUnwrapped
    private List<PostCF> acf;
}
