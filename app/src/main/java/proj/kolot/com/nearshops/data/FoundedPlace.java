package proj.kolot.com.nearshops.data;

import com.google.gson.annotations.SerializedName;

public class FoundedPlace {

    @SerializedName("geometry")
    private Geometry geometry;

    @SerializedName("id")

    private String id;
    @SerializedName("name")

    private String name;

    @SerializedName("vicinity")

    private String vicinity;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
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



    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

}
