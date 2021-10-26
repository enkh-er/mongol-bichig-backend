package stud.enkherdene.mongolbichig.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "category")
public class Category {
    /**
     * Angilliin dugaar
     */
    @Id
    private String id;

    /**
     * Angilliin ner
     */
    private String name;

    /**
     * Etseg angilaliin dugaar
     */
    private String parent;

    /**
     *Angilliin tailbar
     */
    private String description;

    /**
     * Angilliin holboos
     */
    private String link;

    private  String code;

}
