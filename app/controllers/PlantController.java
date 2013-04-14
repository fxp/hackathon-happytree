package controllers;

import play.Logger;
import play.mvc.Controller;
import models.*;

/**
 * User: fxp
 * Date: 13-4-13
 * Time: PM9:45
 */
public class PlantController extends Controller {

    public static void create(String lat, String lng) {
        Logger.info("create,%s,%s", lat, lng);
        Plant plant = new Plant().save();
        renderJSON(plant);
    }

    public static void get(String id) {
        Plant plant = Plant.find("_id", id).get();
        renderJSON(plant);
    }

    public static void delete(String id) {
        Logger.info("delete,%s", id);

    }

    public static void unlock(String id) {
        Plant plant = Plant.find("_id", id).get();
        plant.locked = false;
        plant = plant.save();
        Application.index("map");
    }

    public static void lock(String id) {
        Plant plant = Plant.find("_id", id).get();
        plant.locked = true;
        plant = plant.save();
        show(id);
    }

    public static void show(String id) {
        Plant plant = Plant.find("_id", id).get();
        render(plant);
    }

    public static void history(String id){
        Plant plant = Plant.find("_id", id).get();
        render(plant);
    }

}
