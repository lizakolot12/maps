package proj.kolot.com.nearshops.data

import com.google.gson.annotations.SerializedName

class FoundedPlace {

    @SerializedName("geometry")
    var geometry: Geometry? = null

    @SerializedName("id")
    var id: String? = null
    @SerializedName("name")
    var name: String? = null

    @SerializedName("vicinity")
    var vicinity: String? = null

}
