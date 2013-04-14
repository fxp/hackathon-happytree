package controllers;

import com.google.code.morphia.Key;
import models.Plant;
import models.PlantStatus;
import org.apache.commons.lang.StringUtils;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Application extends Controller {

    public static void index(String layout) {
        layout = (StringUtils.isEmpty(layout)) ? "map" : layout;
        List<Plant> plants = Plant.findAll();
        render(layout, plants);
    }

    public static void show(String plantId) {
        Key<Plant> key = Plant.find("_id", plantId).getKey();
        List<PlantStatus> statuses = PlantStatus.q().filter("plant", key).fetchAll();
        renderJSON(statuses);
    }

}