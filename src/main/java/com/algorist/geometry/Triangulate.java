/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commerical applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.geometry;

import static com.algorist.geometry.Geometry.cw;
import static com.algorist.geometry.Geometry.triangleArea;

/**
 * Triangulate a polygon via ear-clipping, and compute the area
 * of a polygon.
 *
 * @author csong2022
 */
public class Triangulate {
    private static Geometry.Triangulation triangulate(Geometry.Polygon p) {
        int[] l = new int[p.n];    /* left neighbor indices */
        int[] r = new int[p.n];    /* left neighbor indices */

        for (int i = 0; i < p.n; i++) {    /* initialization */
            l[i] = ((i - 1) + p.n) % p.n;
            r[i] = ((i + 1) + p.n) % p.n;
        }

        int tn = 0;
        int[][] tarr = new int[p.n][3];
        int i = p.n - 1;
        while (tn < (p.n - 2)) {
            i = r[i];
            if (earQ(l[i], i, r[i], p)) {
                tarr[tn] = new int[]{l[i], i, r[i]};
                tn++;
                l[r[i]] = l[i];
                r[l[i]] = r[i];
            }
        }

        return new Geometry.Triangulation(tarr, tn);
    }

    private static boolean earQ(int i, int j, int k, Geometry.Polygon p) {
        Geometry.Triangle t = new Geometry.Triangle(p.p[i], p.p[j], p.p[k]);

        if (cw(t.a, t.b, t.c)) return false;

        for (int m = 0; m < p.n; m++) {
            if (m != i && m != j && m != k)
                if (pointInTriangle(p.p[m], t)) return false;
        }

        return true;
    }

    private static boolean pointInTriangle(Geometry.Point p, Geometry.Triangle t) {
        if (cw(t.a, t.b, p)) return false;
        else if (cw(t.b, t.c, p)) return false;
        else if (cw(t.c, t.a, p)) return false;
        else return true;
    }

    public static double areaTriangulation(Geometry.Polygon p) {
        double total = 0.0;        /* total area so far */

        Geometry.Triangulation t = triangulate(p);  /* output triangulation */
        for (int i = 0; i < t.n; i++)
            total += triangleArea(p.p[t.t[i][0]], p.p[t.t[i][1]], p.p[t.t[i][2]]);

        return total;
    }

    public static double area(Geometry.Polygon p) {
        double total = 0.0;        /* total area so far */

        for (int i = 0; i < p.n; i++) {
            int j = (i + 1) % p.n;
            total += (p.p[i].x * p.p[j].y) - (p.p[j].x * p.p[i].y);
        }

        return total / 2.0;
    }

}