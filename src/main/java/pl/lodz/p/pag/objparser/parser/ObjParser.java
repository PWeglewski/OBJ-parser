package pl.lodz.p.pag.objparser.parser;

import pl.lodz.p.pag.objparser.file.FileUtility;
import pl.lodz.p.pag.objparser.models.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 14.05.2016.
 */
public class ObjParser {
    List<Model> models = new ArrayList<>();

    public ObjParser(FileUtility fileUtility) {
        fileUtility.getObjFiles().stream()
                .forEach(objFile -> addNewModel(ObjParserUtility.parseModel(objFile)));
    }

    private void addNewModel(Model model) {
        models.add(model);
    }
}
