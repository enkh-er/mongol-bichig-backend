package stud.enkherdene.mongolbichig.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class CF  implements Serializable {

    @JsonProperty("name")
    String name;

    @JsonProperty("key")
    String key;

    @JsonProperty("type")
    String type;

}

