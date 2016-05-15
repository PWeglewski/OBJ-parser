package pl.lodz.p.pag.objparser;

import pl.lodz.p.pag.objparser.file.FileUtility;
import pl.lodz.p.pag.objparser.parser.ObjParser;
import pl.lodz.p.pag.objparser.renderengine.DisplayManager;

/**
 * Created by piotr on 16.04.2016.
 */
public class ObjParserApp {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        FileUtility fileUtility = new FileUtility(args);

        ObjParser objParser = new ObjParser(fileUtility);

//        Loader loader = new Loader();
//
//        StaticShader staticShader = new StaticShader();
//        Renderer renderer = new Renderer(staticShader);
//
//        RawModel rawModel = OBJLoader.readFromObjFile(args[0], loader);
//
//        ModelTexture texture = new ModelTexture(loader.loadTexture(args[1]));
//        TextureModel textureModel = new TextureModel(rawModel, texture);
//
//        Entity entity = new Entity(textureModel, new Vector3f(0.0f, 0.0f, -1.0f), 0, 0, 0, 1);
//        Light light = new Light(new Vector3f(0,4, 5), new Vector3f(1,1,1));
//
//        Camera camera = new Camera();
//
//        while (!Display.isCloseRequested()) {
//            entity.increaseRotation(0,1,0);
//            camera.move();
//            renderer.prepare();
//            staticShader.start();
//            staticShader.loadLight(light);
//            staticShader.loadViewMatrix(camera);
//            renderer.render(entity, staticShader);
//            staticShader.stop();
//            DisplayManager.updateDisplay();
//        }
//
//        loader.cleanUp();
//        staticShader.cleanUp();
//        DisplayManager.closeDisplay();
    }
}
