package pl.lodz.p.pag.objparser.shaders;

import org.lwjgl.util.vector.Matrix4f;
import pl.lodz.p.pag.objparser.Entities.Camera;
import pl.lodz.p.pag.objparser.toolbox.Maths;

/**
 * Created by piotr on 16.04.2016.
 */
public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/main/java/pl/lodz/p/pag/objparser/shaders/vertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/main/java/pl/lodz/p/pag/objparser/shaders/fragmentShader.glsl";

    private int location_transformationMatrix;
    private int location_projectionMatrix;
    private int location_viewMatrix;

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }

    @Override
    protected void getAllUniformLocations() {
        location_transformationMatrix = super.getUniformLocation("transformationMatrix");
        location_projectionMatrix = super.getUniformLocation("projectionMatrix");
        location_viewMatrix = super.getUniformLocation("viewMatrix");
    }

    public void loadTransformationMatrix(Matrix4f matrix4f) {
        super.loadMatrix(location_transformationMatrix, matrix4f);
    }

    public void loadProjectionMatrix(Matrix4f matrix4f) {
        super.loadMatrix(location_projectionMatrix, matrix4f);
    }

    public void loadViewMatrix(Camera camera) {
        Matrix4f viewMatrix = Maths.createViewMatrix(camera);
        super.loadMatrix(location_viewMatrix, viewMatrix);
    }
}
