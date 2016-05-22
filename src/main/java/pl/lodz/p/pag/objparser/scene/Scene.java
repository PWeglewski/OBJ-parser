package pl.lodz.p.pag.objparser.scene;

import org.lwjgl.util.vector.Vector3f;
import pl.lodz.p.pag.objparser.entities.Entity;
import pl.lodz.p.pag.objparser.models.Model;
import pl.lodz.p.pag.objparser.parser.ObjParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 22.05.16.
 */
public class Scene {
    List<Entity> entities = new ArrayList<>();

    public Scene(ObjParser objParser) {
        for(Model model : objParser.getModels()){
            entities.add(new Entity(model, new Vector3f(0.0f, 0.0f, -1.0f), 0, 0, 0, 1));
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }
}
