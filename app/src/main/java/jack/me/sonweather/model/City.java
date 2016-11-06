package jack.me.sonweather.model;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * Created by zjchai on 2016/11/1.
 */
@Data
@Builder
@DatabaseTable
public class City {

    public static final String SPLIT = "#";

    @DatabaseField(columnName = "cId")
    @SerializedName("city_id")
    String id;
    @DatabaseField(columnName = "cName")
    @SerializedName("name")
    String name;
    @DatabaseField(columnName = "cEnglishName")
    @SerializedName("en")
    String englishName;
    @SerializedName("list")
    List<City> cities;
    List<String> childrenId = new ArrayList<>();
    @DatabaseField(columnName = "extra")
    String extra;
    @DatabaseField(columnName = "children")
    String childrenIdJson;


    @Tolerate
    public City() {
    }

    public void transformChildren(){
        if (cities != null) {
            childrenId.clear();
            for (City city : cities) {
                childrenId.add(city.getId());
            }
            childrenIdJson = new Gson().toJson(childrenId);
        }
    }

}
