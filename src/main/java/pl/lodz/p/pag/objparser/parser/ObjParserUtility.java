package pl.lodz.p.pag.objparser.parser;

import pl.lodz.p.pag.objparser.file.ObjFile;
import pl.lodz.p.pag.objparser.models.Model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by piotr on 14.05.2016.
 */
public class ObjParserUtility {
    private static final String MATERIAL_LIBRARY = "mtllib";

    public static Model parseModel(ObjFile objFile) {
        Model model = new Model();

        try (BufferedReader bufferedReader = new BufferedReader(
                new FileReader(
                        objFile.getFile()
                )
        )) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if(line.startsWith(MATERIAL_LIBRARY)){
                    MaterialLibraryParser.parseMaterialLibrary(line, objFile);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return model;
    }
}
