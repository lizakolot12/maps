package proj.kolot.com.nearshops.data

import com.google.gson.annotations.SerializedName

class GeneralResponse {

    @SerializedName("html_attributions")
    var htmlAttributions: List<Any>? = null
    @SerializedName("results")
    var foundedPlaces: List<FoundedPlace>? = null
    @SerializedName("status")
    var status: String? = null

}
