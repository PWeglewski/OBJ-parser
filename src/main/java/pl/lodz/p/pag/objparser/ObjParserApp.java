package pl.lodz.p.pag.objparser;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import pl.lodz.p.pag.objparser.entities.Camera;
import pl.lodz.p.pag.objparser.entities.Entity;
import pl.lodz.p.pag.objparser.entities.Light;
import pl.lodz.p.pag.objparser.file.FileUtility;
import pl.lodz.p.pag.objparser.models.Model;
import pl.lodz.p.pag.objparser.parser.ObjParser;
import pl.lodz.p.pag.objparser.renderengine.DisplayManager;
import pl.lodz.p.pag.objparser.renderengine.Loader;
import pl.lodz.p.pag.objparser.renderengine.OBJLoader;
import pl.lodz.p.pag.objparser.renderengine.Renderer;
import pl.lodz.p.pag.objparser.scene.Scene;
import pl.lodz.p.pag.objparser.shaders.StaticShader;

/**
 * Created by piotr on 16.04.2016.
 */
public class ObjParserApp {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        FileUtility fileUtility = new FileUtility(args);

        ObjParser objParser = new ObjParser(fileUtility);

        OBJLoader objLoader = new OBJLoader();
        Loader loader = new Loader();
        objLoader.loadObj(objParser, loader);

//        Loader loader = new Loader();
//
        StaticShader staticShader = new StaticShader();
        Renderer renderer = new Renderer(staticShader);
//
//        RawModel rawModel = OBJLoader.readFromObjFile(args[0], loader);
//
//        ModelTexture texture = new ModelTexture(loader.loadTexture(args[1]));
//        TextureModel textureModel = new TextureModel(rawModel, texture);
//
        Scene scene = new Scene(objParser);
//
        Camera camera = new Camera();
//
        while (!Display.isCloseRequested()) {
            scene.getEntities().get(0).increaseRotation(0,0.1f,0);
//            entity.increaseRotation(0,1,0);
            camera.move();
            renderer.prepare();
            staticShader.start();
            scene.loadLights(staticShader);
            staticShader.loadViewMatrix(camera);
            renderer.render(scene, staticShader);
            staticShader.stop();
            DisplayManager.updateDisplay();
        }
//
        loader.cleanUp();
        staticShader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
