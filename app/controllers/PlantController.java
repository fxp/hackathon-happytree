package controllers;

import play.Logger;
import play.mvc.Controller;

/**
 * User: fxp
 * Date: 13-4-13
 * Time: PM9:45
 */
public class PlantController extends Controller {

    public static void create(String lat, String lng) {
        Logger.info("create,%s,%s", lat, lng);
        models.Plant plant = new models.Plant().save();
        renderJSON(plant);
    }

    public static void get(String id) {
        models.Plant plant = models.Plant.find("_id", id).get();
        renderJSON(plant);
    }

    public static void delete(String id) {
        Logger.info("delete,%s", id);

    }

    public static void unlock(String id) {
        models.Plant plant = models.Plant.find("_id", id).get();
        plant.locked = false;
        plant = plant.save();
        Application.index("map");
    }

    public static void lock(String id) {
        models.Plant plant = models.Plant.find("_id", id).get();
        plant.locked = true;
        plant = plant.save();
        show(id);
    }

    public static void show(String id) {
        models.Plant plant = models.Plant.find("_id", id).get();
//        Key<Plant> key = Plant.find("_id", id).getKey();
//        List<PlantStatus> statuses = PlantStatus.q().filter("plant", key).fetchAll();
//        Logger.info("show,%s,%s", id, statuses.size());
//        renderJSON(statuses);
        render(plant);
    }

    public static void history(String id){
        models.Plant plant = models.Plant.find("_id", id).get();
        render(plant);
    }

}
