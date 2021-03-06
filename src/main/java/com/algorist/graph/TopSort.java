/*
Copyright 2003 by Steven S. Skiena; all rights reserved.

Permission is granted for use in non-commercial applications
provided this copyright notice remains intact and unchanged.

This program appears in my book:

"Programming Challenges: The Programming Contest Training Manual"
by Steven Skiena and Miguel Revilla, Springer-Verlag, New York 2003.

See our website www.programming-challenges.com for additional information.

This book can be ordered from Amazon.com at

http://www.amazon.com/exec/obidos/ASIN/0387001638/thealgorithmrepo/

*/
package com.algorist.graph;

import com.algorist.datastructure.Queue;

import java.util.Arrays;

/**
 * Topologically sort a directed acyclic graph (DAG)
 * <p>
 * Translate from topsort.c.
 *
 * @param <T> edge node type.
 * @author csong2022
 */
public class TopSort<T extends EdgeNode> {
    private final int[] sorted;

    public TopSort(Graph<T> g) {
        this.sorted = new int[g.nvertices() + 1];

        int[] indegree = computeIndegrees(g);             /* indegree of each vertex */
        Queue<Integer> zeroin = new Queue<>();            /* vertices of indegree 0 */
        for (int i = 1; i <= g.nvertices(); i++)
            if (indegree[i] == 0) zeroin.enqueue(i);

        int j = 0;        /* counters */
        while (!zeroin.isEmpty()) {
            int x = zeroin.dequeue();
            sorted[++j] = x;
            for (T p : g.edges(x)) {
                int y = p.y();
                indegree[y]--;
                if (indegree[y] == 0) zeroin.enqueue(y);
            }
        }

        if (j != g.nvertices())
            System.out.printf("Not a DAG -- only %d vertices found%n", j);
    }

    private int[] computeIndegrees(Graph<T> g) {
        int[] in = new int[g.nvertices() + 1];
        for (int i = 1; i <= g.nvertices(); i++) in[i] = 0;

        for (int i = 1; i <= g.nvertices(); i++) {
            for (T p : g.edges(i)) {
                in[p.y()]++;
            }
        }

        return in;
    }

    public int[] sorted() {
        return Arrays.copyOf(this.sorted, this.sorted.length);
    }
}
