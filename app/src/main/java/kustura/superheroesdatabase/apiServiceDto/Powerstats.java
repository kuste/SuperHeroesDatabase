
package kustura.superheroesdatabase.apiServiceDto;


import java.io.Serializable;

public class Powerstats implements Serializable {

    private String intelligence;
    private String strength;
    private String speed;
    private String durability;
    private String power;
    private String combat;

    public String getIntelligence() {
        return intelligence;
    }

    public String getStrength() {
        return strength;
    }

    public String getSpeed() {
        return speed;
    }

    public String getDurability() {
        return durability;
    }

    public String getPower() {
        return power;
    }

    public String getCombat() {
        return combat;
    }


}
