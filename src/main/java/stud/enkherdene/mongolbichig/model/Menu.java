package stud.enkherdene.mongolbichig.model;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

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

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Menu> getChild_items() {
        return child_items;
    }

    public void setChild_items(List<Menu> child_items) {
        this.child_items = child_items;
    }

    public Menu() {
    }
}
