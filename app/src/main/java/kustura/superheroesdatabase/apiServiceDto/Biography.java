
package kustura.superheroesdatabase.apiServiceDto;


import java.io.Serializable;
import java.util.List;

public class Biography implements Serializable {

    private String fullName;
    private String alterEgos;
    private List<String> aliases ;
    private String placeOfBirth;
    private String firstAppearance;
    private String publisher;
    private String alignment;

    public String getFullName() {
        return fullName;
    }

    public String getAlterEgos() {
        return alterEgos;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public String getFirstAppearance() {
        return firstAppearance;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAlignment() {
        return alignment;
    }


}
