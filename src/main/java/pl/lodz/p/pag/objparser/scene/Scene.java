package pl.lodz.p.pag.objparser.scene;

import org.lwjgl.util.vector.Vector3f;
import pl.lodz.p.pag.objparser.entities.Entity;
import pl.lodz.p.pag.objparser.entities.Light;
import pl.lodz.p.pag.objparser.models.Model;
import pl.lodz.p.pag.objparser.parser.ObjParser;
import pl.lodz.p.pag.objparser.shaders.StaticShader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 22.05.16.
 */
public class Scene {
    List<Entity> entities = new ArrayList<>();
    List<Light> lights = new ArrayList<>();

    public Scene(ObjParser objParser) {
        float location = 0.0f;
        for(Model model : objParser.getModels()){
            entities.add(new Entity(model, new Vector3f(location, 0.0f, -1.0f), 0, 0, 0, 1));
            lights.add(new Light(new Vector3f(0,4, 5), new Vector3f(1,1,1)));
            location+=5.0f;
        }
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    public void loadLights(StaticShader staticShader){
        lights.forEach(staticShader::loadLight);
    }
}
