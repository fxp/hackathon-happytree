package models;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Reference;
import play.modules.morphia.Model;

/**
 * User: fxp
 * Date: 13-4-13
 * Time: PM3:15
 */
@Entity
public class PlantStatus extends Model {

    @Reference
    public Plant plant;
    public double airHumidity;
    public double temperature;
    public double resistance;
    public double soilHumidity;

    public PlantStatus(Plant plant, double airHumidity, double temperature, double resistance) {
        this.plant = plant;
        this.airHumidity = airHumidity;
        this.temperature = temperature;
        this.resistance = resistance;
    }
}
