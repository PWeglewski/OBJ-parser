package pl.lodz.p.pag.objparser;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import pl.lodz.p.pag.objparser.Entities.Camera;
import pl.lodz.p.pag.objparser.Entities.Entity;
import pl.lodz.p.pag.objparser.models.RawModel;
import pl.lodz.p.pag.objparser.models.TextureModel;
import pl.lodz.p.pag.objparser.renderengine.DisplayManager;
import pl.lodz.p.pag.objparser.renderengine.Loader;
import pl.lodz.p.pag.objparser.renderengine.Renderer;
import pl.lodz.p.pag.objparser.shaders.StaticShader;
import pl.lodz.p.pag.objparser.textures.ModelTexture;

/**
 * Created by piotr on 16.04.2016.
 */
public class ObjParserApp {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        Loader loader = new Loader();

        StaticShader staticShader = new StaticShader();
        Renderer renderer = new Renderer(staticShader);

        float[] vertices = {
                -0.5f, 0.5f, 0f,
                -0.5f, -0.5f, 0f,
                0.5f, -0.5f, 0f,
                0.5f, 0.5f, 0f,
        };

        int[] indices = {
                0, 1, 3,
                3, 1, 2
        };

        float[] textureCoords = {
                0, 0,
                0, 1,
                1, 1,
                1, 0,
        };

        RawModel rawModel = loader.loadToVAO(vertices, textureCoords, indices);
        ModelTexture texture = new ModelTexture(loader.loadTexture("texture"));
        TextureModel textureModel = new TextureModel(rawModel, texture);

        Entity entity = new Entity(textureModel, new Vector3f(0.0f, 0.0f, -1.0f), 0, 0, 0, 1);

        Camera camera = new Camera();

        while (!Display.isCloseRequested()) {
            entity.increaseRotation(0, 1, 0);
            //entity.increasePosition(0.001f,0,-0.05f);
            camera.move();
            renderer.prepare();
            staticShader.start();
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
