package models;


import com.google.code.morphia.annotations.Entity;
import play.modules.morphia.Model;

/**
 * User: fxp
 * Date: 13-4-13
 * Time: PM3:13
 */
@Entity
public class Plant extends Model {

    public double lat;
    public double lng;
    public String desc;
    public double humidity = 0;
    public double solidHumidity = 0;
    public double temperature = 0;
    public double health = 0;
    public boolean locked = false;
    public String img;

    @Override
    public String toString() {
        return String.format("%s,%s", lat, lng);
    }
}
