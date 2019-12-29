
package kustura.superheroesdatabase.apiServiceDto;


import java.io.Serializable;

public class Result implements Serializable {

    private String id;
    private String name;
    private Powerstats powerstats;
    private Biography biography;
    private Appearance appearance;
    private Work work;
    private Connections connections;
    private Image image;

    public String getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public Powerstats getPowerstats() {
        return powerstats;
    }

    public Biography getBiography() {
        return biography;
    }

    public Appearance getAppearance() {
        return appearance;
    }

    public Work getWork() {
        return work;
    }

    public Connections getConnections() {
        return connections;
    }

    public Image getImage() {
        return image;
    }


}
