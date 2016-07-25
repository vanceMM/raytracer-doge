package de.beuth.cg1.dogeraytracer.geometry;

import de.beuth.cg1.dogeraytracer.material.Material;
import de.beuth.cg1.dogeraytracer.vecmatlib.Point3;
import de.beuth.cg1.dogeraytracer.vecmatlib.Ray;
import de.beuth.cg1.dogeraytracer.vecmatlib.Transform;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by valentin on 25/07/16.
 */
public class ShapeFromFile extends Geometry {

    private ArrayList<Geometry> faces;
    private ArrayList<Point3> vertices;
    private String fileName;

    public ShapeFromFile(final Material material, final String fileName) {
        super(material);
        this.fileName = fileName;
        this.faces = new ArrayList<>();
        this.vertices = new ArrayList<>();

    }

    public Node load() {
        File file = new File(fileName);
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            InputStreamReader stream = new InputStreamReader(in);
            BufferedReader reader = new BufferedReader(stream);

            String line = null;
            while((line = reader.readLine()) != null) {
                if(line.startsWith("#")) {
                    continue;
                }
                if(line.startsWith("v")) {
                    addVertices(line);
                }
                if(line.startsWith("f")) {
                    addFaces(line);
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(faces);
        return new Node(new Transform(),faces);
    }

    private void addFaces(String line) {
        String[] tokens = line.split(" ");
        int v1 = Integer.parseInt(tokens[1]);
        int v2 = Integer.parseInt(tokens[2]);
        int v3 = Integer.parseInt(tokens[3]);

        try {

            faces.add(new Triangle(this.material, vertices.get(v1), vertices.get(v2), vertices.get(v3)));
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    private void addVertices(String line) {
        String[] tokens = line.split(" ");
        vertices.add(new Point3(Float.parseFloat(tokens[1]),Float.parseFloat(tokens[2]),Float.parseFloat(tokens[3])));

    }

    @Override
    public Hit hit(Ray r) {
        return null;
    }
}
