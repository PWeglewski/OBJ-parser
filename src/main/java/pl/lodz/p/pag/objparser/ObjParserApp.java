package pl.lodz.p.pag.objparser;

import org.lwjgl.opengl.Display;
import pl.lodz.p.pag.objparser.renderengine.DisplayManager;

/**
 * Created by piotr on 16.04.2016.
 */
public class ObjParserApp {
    public static void main(String[] args) {
        DisplayManager.createDisplay();

        while (!Display.isCloseRequested()){
            DisplayManager.updateDisplay();
        }

        DisplayManager.closeDisplay();
    }
}
