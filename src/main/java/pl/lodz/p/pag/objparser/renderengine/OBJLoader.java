package pl.lodz.p.pag.objparser.renderengine;

import org.lwjgl.util.vector.Vector2f;
import org.lwjgl.util.vector.Vector3f;
import pl.lodz.p.pag.objparser.models.Group;
import pl.lodz.p.pag.objparser.models.Model;
import pl.lodz.p.pag.objparser.parser.ObjParser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by piotr on 16.04.2016.
 */
public class OBJLoader {
    public static final String OBJ_PATH = "src/main/resources/";

    private static List<Integer> indices = new ArrayList<>();
    private static List<Vector3f> vertices = new ArrayList<>();

//    public static RawModel readFromObjFile(String fileName, Loader loader) {
//
//        float[] verticesArray = null;
//        float[] normalsArray = null;
//        float[] textureArray = null;
//        int[] indicesArray = null;
//
//        try (BufferedReader bufferedReader = new BufferedReader(
//                new FileReader(
//                        new File(fileName)))) {
//            List<String> lines = bufferedReader.lines().collect(Collectors.toList());
//
//            //bufferedReader.lines().forEach(OBJLoader::readLine);
//
//            List<Vector3f> verticesTemp = new ArrayList<>();
//            lines.stream()
//                    .filter(e -> e.startsWith("v "))
//                    .map(e -> e.split(" "))
//                    .map(strings -> Arrays.copyOfRange(strings, 1, strings.length))
//                    .map(strings -> Arrays.stream(strings).mapToDouble(Double::parseDouble).toArray())
//                    .forEach(floats -> verticesTemp.add(new Vector3f((float) floats[0], (float) floats[1], (float) floats[2])));
//            vertices = verticesTemp;
//
//
//            List<Vector2f> textures = new ArrayList<>();
//            lines.stream()
//                    .filter(e -> e.startsWith("vt "))
//                    .map(e -> e.split(" "))
//                    .map(strings -> Arrays.copyOfRange(strings, 1, strings.length))
//                    .map(strings -> Arrays.stream(strings).mapToDouble(Double::parseDouble).toArray())
//                    .forEach(floats -> textures.add(new Vector2f((float) floats[0], (float) floats[1])));
//
//            List<Vector3f> normals = new ArrayList<>();
//            lines.stream()
//                    .filter(e -> e.startsWith("vn "))
//                    .map(e -> e.split(" "))
//                    .map(strings -> Arrays.copyOfRange(strings, 1, strings.length))
//                    .map(strings -> Arrays.stream(strings).mapToDouble(Double::parseDouble).toArray())
//                    .forEach(floats -> normals.add(new Vector3f((float) floats[0], (float) floats[1], (float) floats[2])));
//
//            List<List<Integer[]>> vertexesInfoList = new ArrayList<>();
//            lines.stream()
//                    .filter(e -> e.startsWith("f "))
//                    .map(e -> e.split(" "))
//                    .map(strings -> Arrays.copyOfRange(strings, 1, strings.length))
//                    .map(strings -> Arrays.stream(strings)
//                            .map(s -> (String[]) s.split("/")).toArray())
//                    .forEach(objects -> {
//                        List<Integer[]> list = new ArrayList<>();
//                        Arrays.stream(objects).forEach(o -> list.add(Arrays.stream((String[]) o).mapToInt(Integer::parseInt).boxed().toArray(Integer[]::new)));
//                        vertexesInfoList.add(list);
//                    });
//
//
//            Optional<String> mtllibLine = lines.stream()
//                    .filter(e -> e.startsWith("mtllib "))
//                    .findFirst();
//
//            mtllibLine.ifPresent(s -> {
//                String[] splitted = s.split(" ");
//                if (splitted.length > 1) {
//                    System.out.println(splitted[1]);
//                    MaterialLibraryLoader materialLibraryLoader = new MaterialLibraryLoader();
////                    materialLibraryLoader.readForMtlFile("\\Pikachu\\"+splitted[1]);
//                    File[] files = new File("\\").listFiles();
//                    for (File file : files) {
//                        System.out.println(file.getPath());
//                    }
//                }
//            });
//
//
//            textureArray = new float[vertices.size() * 2];
//            normalsArray = new float[vertices.size() * 3];
//
//            for (List<Integer[]> line : vertexesInfoList) {
//                for (Integer[] vertexData : line) {
//                    processVertex(vertexData,
//                            indices,
//                            textures,
//                            normals,
//                            textureArray,
//                            normalsArray);
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        verticesArray = new float[vertices.size() * 3];
//        indicesArray = new int[indices.size()];
//
//        int vertexPointer = 0;
//        for (Vector3f vertex : vertices) {
//            verticesArray[vertexPointer++] = vertex.x;
//            verticesArray[vertexPointer++] = vertex.y;
//            verticesArray[vertexPointer++] = vertex.z;
//        }
//        for (int i = 0; i < indices.size(); i++) {
//            indicesArray[i] = indices.get(i);
//        }
//        return loader.loadToVAO(verticesArray, textureArray, normalsArray, indicesArray);
//    }


//    public static RawModel loadObjModel(String fileName, Loader loader) {
//        FileReader fr = null;
//        try {
//            fr = new FileReader(new File(OBJ_PATH + fileName + ".obj"));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.err.println("Could not load file: " + fileName + ".");
//            System.exit(-1);
//        }
//        BufferedReader reader = new BufferedReader(fr);
//        String line;
//        List<Vector3f> vertices = new ArrayList<>();
//        List<Vector2f> textures = new ArrayList<>();
//        List<Vector3f> normals = new ArrayList<>();
//        List<Integer> indices = new ArrayList<>();
//
//        float[] verticesArray = null;
//        float[] normalsArray = null;
//        float[] textureArray = null;
//        int[] indicesArray = null;
//
//        try {
//            while (true) {
//                line = reader.readLine();
//                String[] currentLine = line.split(" ");
//                if (line.startsWith("v ")) {
//                    Vector3f vertex = new Vector3f(
//                            Float.parseFloat(currentLine[1]),
//                            Float.parseFloat(currentLine[2]),
//                            Float.parseFloat(currentLine[3])
//                    );
//                    vertices.add(vertex);
//
//                } else if (line.startsWith("vt ")) {
//                    Vector2f texture = new Vector2f(
//                            Float.parseFloat(currentLine[1]),
//                            Float.parseFloat(currentLine[2])
//                    );
//                    textures.add(texture);
//                } else if (line.startsWith("vn ")) {
//                    Vector3f normal = new Vector3f(
//                            Float.parseFloat(currentLine[1]),
//                            Float.parseFloat(currentLine[2]),
//                            Float.parseFloat(currentLine[3])
//                    );
//                    normals.add(normal);
//                } else if (line.startsWith("f ")) {
//                    textureArray = new float[vertices.size() * 2];
//                    normalsArray = new float[vertices.size() * 3];
//                    break;
//                }
//            }
//
//            while (line != null) {
//                if (line.startsWith("o ")) {
//                    break;
//                }
//                if (!line.startsWith("f ")) {
//                    line = reader.readLine();
//
//                    continue;
//                }
//                String[] currentLine = line.split(" ");
//                String[] vertex1 = currentLine[1].split("/");
//                String[] vertex2 = currentLine[2].split("/");
//                String[] vertex3 = currentLine[3].split("/");
//
//                processVertex(vertex1, indices, textures, normals, textureArray, normalsArray);
//                processVertex(vertex2, indices, textures, normals, textureArray, normalsArray);
//                processVertex(vertex3, indices, textures, normals, textureArray, normalsArray);
//                line = reader.readLine();
//            }
//            reader.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//            System.err.println("Exception occurred while reading a file");
//            System.exit(-1);
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.err.println("Unknown exception occurred");
//            System.exit(-1);
//        }
//        verticesArray = new float[vertices.size() * 3];
//        indicesArray = new int[indices.size()];
//
//        int vertexPointer = 0;
//        for (Vector3f vertex : vertices) {
//            verticesArray[vertexPointer++] = vertex.x;
//            verticesArray[vertexPointer++] = vertex.y;
//            verticesArray[vertexPointer++] = vertex.z;
//        }
//
//        for (int i = 0; i < indices.size(); i++) {
//            indicesArray[i] = indices.get(i);
//        }
//        return loader.loadToVAO(verticesArray, textureArray, normalsArray, indicesArray);
//    }

    private static void processVertex(
            String[] vertexData,
            List<Integer> indices,
            List<Vector2f> textures,
            List<Vector3f> normals,
            float[] textureArray,
            float[] normalsArray) {

        int currentVertexPointer = Integer.parseInt(vertexData[0]) - 1;
        indices.add(currentVertexPointer);
        Vector2f currentTex = textures.get(Integer.parseInt(vertexData[1]) - 1);
        textureArray[currentVertexPointer * 2] = currentTex.x;
        textureArray[currentVertexPointer * 2 + 1] = 1 - currentTex.y;
        Vector3f currentNorm = normals.get(Integer.parseInt(vertexData[2]) - 1);
        normalsArray[currentVertexPointer * 3] = currentNorm.x;
        normalsArray[currentVertexPointer * 3 + 1] = currentNorm.y;
        normalsArray[currentVertexPointer * 3 + 2] = currentNorm.z;
    }

    private static void processVertex(
            Integer[] vertexData,
            List<Integer> indices,
            List<Vector2f> textures,
            List<Vector3f> normals,
            float[] textureArray,
            float[] normalsArray) {

        int currentVertexPointer = vertexData[0] - 1;
        indices.add(currentVertexPointer);
        Vector2f currentTex = textures.get(vertexData[1] - 1);
        textureArray[currentVertexPointer * 2] = currentTex.x;
        textureArray[currentVertexPointer * 2 + 1] = 1 - currentTex.y;
        Vector3f currentNorm = normals.get(vertexData[2] - 1);
        normalsArray[currentVertexPointer * 3] = currentNorm.x;
        normalsArray[currentVertexPointer * 3 + 1] = currentNorm.y;
        normalsArray[currentVertexPointer * 3 + 2] = currentNorm.z;
    }

    public static void loadObj(ObjParser objParser, Loader loader) {
        for (Model model : objParser.getModels()) {
            for (Group group : model.getGroups()) {
                group.getMaterial().setTextureVaoId(loader.loadTexture(group.getMaterial().getTextureFileName()));

                int verticesNumber = findMaxVertexIndex(group.getFaces());
                int[] indicesArray = new int[group.getFaces().size() * 3];
                float[] verticesArray = new float[verticesNumber * 3];
                float[] normalsArray = new float[verticesNumber * 3];
                float[] textureArray = new float[verticesNumber * 2];

                group.setVertexCount(indicesArray.length);

                List<Vector3f> vertices = model.getVertices();
                int verticesArrayIterator = 0;
                for (int i = 0; i < verticesNumber; i++) {
                    Vector3f vertex = vertices.get(i);
                    verticesArray[i * 3] = vertex.getX();
                    verticesArrayIterator++;
                    verticesArray[i * 3 + 1] = vertex.getY();
                    verticesArrayIterator++;
                    verticesArray[i * 3 + 2] = vertex.getZ();
                    verticesArrayIterator++;
                }

                int indicesArrayIterator = 0;
                for (List<int[]> face : group.getFaces()) {
                    for (int[] vertexIndex : face) {
                        int currentVertexIndex = vertexIndex[0] - 1;
                        indicesArray[indicesArrayIterator] = currentVertexIndex;

                        Vector2f textureCoordinates = model.getTextureCoordinates().get(vertexIndex[1] - 1);
                        textureArray[currentVertexIndex * 2] = textureCoordinates.getX();
                        textureArray[currentVertexIndex * 2 + 1] = textureCoordinates.getY();

                        Vector3f normal = model.getNormals().get(vertexIndex[2] - 1);
                        normalsArray[currentVertexIndex * 3] = normal.getX();
                        normalsArray[currentVertexIndex * 3 + 1] = normal.getY();
                        normalsArray[currentVertexIndex * 3 + 2] = normal.getZ();

                        indicesArrayIterator++;
                    }
                }
                group.setVaoId(loader.loadToVAO(verticesArray, textureArray, normalsArray, indicesArray));
            }
        }
    }


    private static int findMaxVertexIndex(List<List<int[]>> faces) {
        int max = 0;
        for (List<int[]> face : faces) {
            for (int[] indices : face) {
                for (int index : indices) {
                    if (index > max) {
                        max = index;
                    }
                }
            }
        }
        return max;
    }
}
