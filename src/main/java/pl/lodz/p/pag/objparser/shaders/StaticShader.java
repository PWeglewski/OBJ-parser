package pl.lodz.p.pag.objparser.shaders;

import sun.security.provider.SHA;

/**
 * Created by piotr on 16.04.2016.
 */
public class StaticShader extends ShaderProgram {

    private static final String VERTEX_FILE = "src/main/java/pl/lodz/p/pag/objparser/shaders/vertexShader.glsl";
    private static final String FRAGMENT_FILE = "src/main/java/pl/lodz/p/pag/objparser/shaders/fragmentShader.glsl";

    public StaticShader() {
        super(VERTEX_FILE, FRAGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttribute(0, "position");
        super.bindAttribute(1, "textureCoords");
    }
}
