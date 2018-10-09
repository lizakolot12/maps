package proj.kolot.com.nearshops.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GeneralResponse {

    @SerializedName("html_attributions")
    private List<Object> htmlAttributions = null;
    @SerializedName("results")
    private List<FoundedPlace> foundedPlaces = null;
    @SerializedName("status")
    private String status;

    public List<Object> getHtmlAttributions() {
        return htmlAttributions;
    }

    public void setHtmlAttributions(List<Object> htmlAttributions) {
        this.htmlAttributions = htmlAttributions;
    }

    public List<FoundedPlace> getFoundedPlaces() {
        return foundedPlaces;
    }

    public void setFoundedPlaces(List<FoundedPlace> foundedPlaces) {
        this.foundedPlaces = foundedPlaces;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
