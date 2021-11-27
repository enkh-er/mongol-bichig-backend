package stud.enkherdene.mongolbichig.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "dasgal")
public class Dasgal {
    /**
     * CF-iin dugaar
     */
    @Id
    private String id;

    private String name;

    private String code;

    @JsonUnwrapped
    private List<Asuult> asuultList;
}
