package demo.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Data  // spring-boot-starter-parent may not manage lombok, so you have to explicitly assign the version.
@Document
@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor)) // lombok's constructor may NOT be as reliable as the hardcode constructor
public class SupplyLocation {

    @Id
    private String id;

    private String address1;
    private String address2;

    private String city;

    @GeoSpatialIndexed // accelerate accessing!
    @JsonIgnore
    private final Point location; // If a field is final, you must use it in constructor.

    private String state;
    private String zip;
    private String type; // type of supply

    public SupplyLocation() {
        this.location = new Point(0, 0);
    }

    @JsonCreator
    public SupplyLocation(@JsonProperty("longitude") double longitude, @JsonProperty("latitude") double latitude){
        this.location = new Point(longitude, latitude);
    }

    /**
     * the following constructor is equivalent to the lombok annotation : @RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
    @PersistenceConstructor
    public SupplyLocation(Point location) {
        this.location = location;
    }
    */
}
