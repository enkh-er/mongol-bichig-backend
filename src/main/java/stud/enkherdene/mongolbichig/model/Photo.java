package stud.enkherdene.mongolbichig.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.Binary;

@Getter
@Setter
@NoArgsConstructor
public class Photo {
    private String name;
    private Binary image;
}
