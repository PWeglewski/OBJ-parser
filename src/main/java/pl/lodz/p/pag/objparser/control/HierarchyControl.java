package pl.lodz.p.pag.objparser.control;

import org.lwjgl.input.Keyboard;
import pl.lodz.p.pag.objparser.entities.Entity;
import pl.lodz.p.pag.objparser.scene.Scene;

/**
 * Created by piotr on 22.05.16.
 */
public class HierarchyControl {
    Scene scene;

    Entity selection;
    int selectionIndex;

    public HierarchyControl(Scene scene) {
        this.scene = scene;
        selectionIndex = 0;
        updateSelection();
    }

    public void update() {
        while (Keyboard.next()) {
            if (Keyboard.getEventKeyState()) {
                if (Keyboard.getEventKey() == Keyboard.KEY_LEFT) {
                    deselect();
                    selectionIndex--;
                    if (selectionIndex < 0) {
                        selectionIndex = scene.getEntities().size() - 1;
                    }
                    updateSelection();
                }
                if (Keyboard.getEventKey() == Keyboard.KEY_RIGHT) {
                    deselect();
                    selectionIndex++;
                    if (selectionIndex == scene.getEntities().size()) {
                        selectionIndex = 0;
                    }
                    updateSelection();
                }
            }
        }
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    private void updateSelection() {
        selection = scene.getEntities().get(selectionIndex);
        selection.setSelected(true);
        String name = selection.getModel().getMaterialLibrary().getFile().getName().replace(".mtl", "");
        System.out.println("Current selection:\t" + name);
    }

    private void deselect(){
        selection.setSelected(false);
    }
}
