package pl.lodz.p.pag.objparser.shaders;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by piotr on 16.04.2016.
 */
public abstract class ShaderProgram {
    private int programId;
    private int vertexShaderId;
    private int fragmentShaderId;

    public ShaderProgram(String vertexShader, String fragmentShader) {
        this.vertexShaderId = loadShader(vertexShader, GL20.GL_VERTEX_SHADER);
        this.fragmentShaderId = loadShader(fragmentShader, GL20.GL_FRAGMENT_SHADER);
        this.programId = GL20.glCreateProgram();
        GL20.glAttachShader(this.programId, this.vertexShaderId);
        GL20.glAttachShader(this.programId, this.fragmentShaderId);
        bindAttributes();
        GL20.glLinkProgram(this.programId);
        GL20.glValidateProgram(programId);
        bindAttributes();
    }

    protected abstract void bindAttributes();

    public void start(){
        GL20.glUseProgram(programId);
    }

    public void stop(){
        GL20.glUseProgram(0);
    }

    public void cleanUp(){
        stop();
        GL20.glDetachShader(programId, this.vertexShaderId);
        GL20.glDetachShader(programId, this.fragmentShaderId);
        GL20.glDeleteShader(this.vertexShaderId);
        GL20.glDeleteShader(this.fragmentShaderId);
        GL20.glDeleteProgram(programId);
    }

    protected void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(programId, attribute, variableName);
    }

    private static int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while((line = bufferedReader.readLine())!=null){
                shaderSource.append(line).append("\n");
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Shader file: "+file+" not found.");
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Exception while reading file: "+file+".");
            System.exit(-1);
        }
        int shaderId = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderId, shaderSource);
        GL20.glCompileShader(shaderId);
        if(GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS)== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderId, 500));
            System.err.println("Could not compile shader.");
            System.exit(-1);
        }
        return shaderId;
    }
}
