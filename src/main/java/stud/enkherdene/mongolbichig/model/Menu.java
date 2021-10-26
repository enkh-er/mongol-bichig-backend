package stud.enkherdene.mongolbichig.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "menu")
public class Menu {
    /**
     * Angilliin dugaar
     */
    @Id
    private String id;

    private String name;

    private String link;

    private String code;

    private String type;

    private String parent;

    private String typeId;

    private List<Menu> child_items;
}
