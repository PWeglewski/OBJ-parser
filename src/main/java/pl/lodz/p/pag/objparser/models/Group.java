package pl.lodz.p.pag.objparser.models;

import pl.lodz.p.pag.objparser.materials.Material;

/**
 * Created by piotr on 15.05.2016.
 */
public class Group {
    String groupName;
    Material material;

    public Group(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
}
