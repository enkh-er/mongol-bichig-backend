package stud.enkherdene.mongolbichig.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "hicheel")
public class Hicheel {
    @Id
    private String id;
    private String title;
    private String content;
    private String author;
    private String date;
    private String category;
    private String video;
    private String file;
    private String nemelt;
}
