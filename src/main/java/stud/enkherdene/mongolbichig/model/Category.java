package stud.enkherdene.mongolbichig.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

    /**
     * Angilliin zurag
     */
    private byte[] image;

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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
