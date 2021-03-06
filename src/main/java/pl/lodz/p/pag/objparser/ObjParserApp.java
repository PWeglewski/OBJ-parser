package pl.lodz.p.pag.objparser;

import org.lwjgl.opengl.Display;
import pl.lodz.p.pag.objparser.control.HierarchyControl;
import pl.lodz.p.pag.objparser.entities.Camera;
import pl.lodz.p.pag.objparser.file.FileUtility;
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

        StaticShader staticShader = new StaticShader();
        Renderer renderer = new Renderer(staticShader);

        Scene scene = new Scene(objParser);

        Camera camera = new Camera();

        HierarchyControl hierarchyControl = new HierarchyControl(scene);

        while (!Display.isCloseRequested()) {
            hierarchyControl.update();
            camera.move();
            renderer.prepare();
            staticShader.start();
            scene.loadLights(staticShader);
            staticShader.loadViewMatrix(camera);
            renderer.render(scene, staticShader);
            staticShader.stop();
            DisplayManager.updateDisplay();
        }
        loader.cleanUp();
        staticShader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
