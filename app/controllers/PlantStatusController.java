package controllers;

import models.Plant;
import play.Logger;
import play.mvc.Controller;

/**
 * User: fxp
 * Date: 13-4-14
 * Time: AM7:43
 */
public class PlantStatusController extends Controller {

    public static void create(
            String plantId,
            Double h, Double t, Double r) {
        r = (r == null) ? 0.5 : r;
        Logger.info("param,%s", params.allSimple());
        models.PlantStatus ret = null;
        Plant plant = Plant.findById(plantId);
        if (plant == null) {
            Logger.info("checkin failed,cannot find plant");
        } else {
            Logger.info("checkin success,%s,%s,%s,%s", plant, h, t, r);
            models.PlantStatus status = new models.PlantStatus(plant, h, t, r).save();
            plant.humidity = h;
            plant.health = r / 1800;
            plant.health = (plant.health > 1) ? 100 : plant.health * 100;
            plant.solidHumidity = r;
            plant.temperature = t;
            plant.save();
            ret = status;
        }
        renderJSON(ret);
    }

}
