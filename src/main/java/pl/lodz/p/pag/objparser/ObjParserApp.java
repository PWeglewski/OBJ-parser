package pl.lodz.p.pag.objparser;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import pl.lodz.p.pag.objparser.Entities.Camera;
import pl.lodz.p.pag.objparser.Entities.Entity;
import pl.lodz.p.pag.objparser.Entities.Light;
import pl.lodz.p.pag.objparser.models.RawModel;
import pl.lodz.p.pag.objparser.models.TextureModel;
import pl.lodz.p.pag.objparser.renderengine.DisplayManager;
import pl.lodz.p.pag.objparser.renderengine.Loader;
import pl.lodz.p.pag.objparser.renderengine.OBJLoader;
import pl.lodz.p.pag.objparser.renderengine.Renderer;
import pl.lodz.p.pag.objparser.shaders.StaticShader;
import pl.lodz.p.pag.objparser.textures.ModelTexture;

import java.util.Arrays;

/**
 * Created by piotr on 16.04.2016.
 */
public class ObjParserApp {
    public static void main(String[] args) {
        if(args.length<2){
            System.err.println("Missing argument");
            System.exit(-1);
        }
        System.out.println("OBJ file: "+args[0]);
        System.out.println("Texture file: "+args[1]);


        DisplayManager.createDisplay();

        Loader loader = new Loader();

        StaticShader staticShader = new StaticShader();
        Renderer renderer = new Renderer(staticShader);

        RawModel rawModel = OBJLoader.readFromObjFile(args[0], loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture(args[1]));
        TextureModel textureModel = new TextureModel(rawModel, texture);

        Entity entity = new Entity(textureModel, new Vector3f(0.0f, 0.0f, -1.0f), 0, 0, 0, 1);
        Light light = new Light(new Vector3f(0,4, 5), new Vector3f(1,1,1));

        Camera camera = new Camera();

        while (!Display.isCloseRequested()) {
            entity.increaseRotation(0,1,0);
            camera.move();
            renderer.prepare();
            staticShader.start();
            staticShader.loadLight(light);
            staticShader.loadViewMatrix(camera);
            renderer.render(entity, staticShader);
            staticShader.stop();
            DisplayManager.updateDisplay();
        }

        loader.cleanUp();
        staticShader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
