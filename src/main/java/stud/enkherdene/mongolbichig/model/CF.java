package stud.enkherdene.mongolbichig.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class CF  implements Serializable {

    @JsonProperty("name")
    String name;

    @JsonProperty("key")
    String key;

    @JsonProperty("type")
    String type;

    public CF() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

