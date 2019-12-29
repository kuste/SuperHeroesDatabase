package kustura.superheroesdatabase.apiServiceDto;


import java.io.Serializable;
import java.util.List;

public class Appearance implements Serializable {

    private String gender;
    private String race;
    private List<String> height ;
    private List<String> weight;
    private String eyeColor;
    private String hairColor;

    public String getGender() {
        return gender;
    }

    public String getRace() {
        return race;
    }

    public List<String> getHeight() {
        return height;
    }

    public List<String> getWeight() {
        return weight;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }


}
