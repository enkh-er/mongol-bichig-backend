package stud.enkherdene.mongolbichig.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "custom-fields")
public class CustomField  implements Serializable {
    /**
     * CF-iin dugaar
     */
    @Id
    private String id;

    private String name;

    @JsonUnwrapped
    private List<CF> fields;

    private List<String> categories;

    public CustomField() {
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

    public List<CF> getFields() {
        return fields;
    }

    public void setFields(List<CF> fields) {
        this.fields = fields;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }
}
