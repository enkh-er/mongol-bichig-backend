package stud.enkherdene.mongolbichig.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Document
public class Asuult {
    @JsonProperty("asuult")
    String asuult;

    @JsonProperty("huwilbar_a")
    String huwilbar_a;

    @JsonProperty("huwilbar_b")
    String huwilbar_b;

    @JsonProperty("huwilbar_c")
    String huwilbar_c;

    @JsonProperty("zow_huwilbar")
    String zow_huwilbar;
}
