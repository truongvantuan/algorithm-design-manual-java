package com.algorist.datastructure;

import com.algorist.test.TestCaseWithInput;
import com.algorist.test.TestEngine;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static com.algorist.datastructure.War.rankCard;
import static com.algorist.datastructure.War.war;

public class WarTest implements TestCaseWithInput {
    @Override
    public void process(Scanner scanner) {
        Queue<Integer>[] decks = new Queue[2]; /* player's decks */
        char value, suit, c;                   /* input characters */

        while (true) {
            for (int i = 0; i <= 1; i++) {
                decks[i] = new Queue<>();

                if (!scanner.hasNext()) return;
                String line = scanner.nextLine();
                int j = 0;
                while (j < line.length()) {
                    c = line.charAt(j++);
                    if (c != ' ') {
                        value = c;
                        suit = line.charAt(j++);
                        decks[i].enqueue(rankCard(value, suit));
                    }
                }
            }

            war(decks[0], decks[1]);
        }
    }

    @Test
    public void test() throws IOException {
        TestEngine.execute(this, "war-in", "war-out");
    }
}