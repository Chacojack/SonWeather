package jack.me.sonweather.model;

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
}
