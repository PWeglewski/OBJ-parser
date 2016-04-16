package pl.lodz.p.pag.objparser.models;

import pl.lodz.p.pag.objparser.textures.ModelTexture;

/**
 * Created by piotr on 16.04.2016.
 */
public class TextureModel {
    private RawModel rawModel;
    private ModelTexture modelTexture;

    public TextureModel(RawModel rawModel, ModelTexture modelTexture) {
        this.rawModel = rawModel;
        this.modelTexture = modelTexture;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public ModelTexture getModelTexture() {
        return modelTexture;
    }

}
