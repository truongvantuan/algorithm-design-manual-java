package com.algorist.geometry;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static com.algorist.geometry.Geometry.Point;
import static com.algorist.geometry.Geometry.Point.readPoints;
import static com.algorist.geometry.Geometry.Polygon;
import static com.algorist.geometry.Triangulate.area;
import static com.algorist.geometry.Triangulate.areaTriangulation;

public class TriangulateTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        Point[] points = readPoints(scanner);

        Polygon p = new Polygon(points);

        System.out.printf(" area via triangulation = %f%n", areaTriangulation(p));
        System.out.printf(" area slick = %f%n", area(p));
    }

    @Test
    public void testTri1() throws IOException {
        TestEngine.execute(this, "tri1", "tri1-out");
    }

    @Test
    public void testTri2() throws IOException {
        TestEngine.execute(this, "tri2", "tri2-out");
    }

    @Test
    public void testTri3() throws IOException {
        TestEngine.execute(this, "tri3", "tri3-out");
    }
}