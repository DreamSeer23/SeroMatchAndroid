package seromatch.seromatchtest;

/**
 * Created by jason_000 on 5/3/2017.
 */
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jason_000 on 3/25/2017.
 */

public class GoogleMapper {
    @SerializedName("debug_info")
    private List<String> debug_info;

    @SerializedName("html_attributions")
    private List<String> html_attributions;

    @SerializedName("next_page_token")
    private String next_page_token;

    @SerializedName("results")
    private List<Results> results;

    public List<String> getDebugInfo() {
        return debug_info;
    }

    public List<String> getHtmlAttributions() {
        return html_attributions;
    }

    public String getNextPageToken() {
        return next_page_token;
    }

    public List<Results> getResults() {
        return results;
    }

    public String getStatus() {
        return status;
    }

    @SerializedName("status")
    private String status;
}
class Locations {
    public double getLat() {
        return lat;
    }
    public double getLng() {
        return lng;
    }
    @SerializedName("lat")
    private double lat;

    @SerializedName("lng")
    private double lng;
}

class Results {
    public String getFormattedAddress() {
        return formatted_address;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getRating() {
        return rating;
    }

    public String getReference() {
        return reference;
    }

    public List<String> getTypes() {
        return types;
    }

    @SerializedName("formatted_address")
    private String formatted_address;

    @SerializedName("geometry")
    private Geometry geometry;

    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("rating")
    private Double rating;

    @SerializedName("reference")
    private String reference;

    @SerializedName("types")
    private List<String> types;
}
class Geometry {
    public Locations getLocation() {
        return location;
    }

    @SerializedName("location")
    private Locations location;
}