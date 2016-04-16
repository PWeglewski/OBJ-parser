package pl.lodz.p.pag.objparser;

import org.lwjgl.opengl.Display;
import pl.lodz.p.pag.objparser.models.TextureModel;
import pl.lodz.p.pag.objparser.renderengine.DisplayManager;
import pl.lodz.p.pag.objparser.renderengine.Loader;
import pl.lodz.p.pag.objparser.models.RawModel;
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
        Renderer renderer = new Renderer();

        StaticShader staticShader = new StaticShader();

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

        while (!Display.isCloseRequested()) {
            renderer.prepare();
            staticShader.start();
//            renderer.render(rawModel);
            renderer.render(textureModel);
            staticShader.stop();
            DisplayManager.updateDisplay();
        }

        loader.cleanUp();
        staticShader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
