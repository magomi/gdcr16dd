package org.grnrt.gdcr;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by marco on 22/10/16.
 */
public class Game {
    Map<Point, String> board = new HashMap<Point, String>();

    public void init(Point... points) {
        for (Point point : points) {
            board.put(point, "x");
        }
    }

    public void show(int width, int height) throws Exception {

        System.out.print(String.format("\033[2J"));

        System.out.print("+");
        for (int w = 0; w < width; w++) {
            System.out.print("-");
        }
        System.out.println("+");

        for (int h = 0; h < height; h++) {
            System.out.print("|");
            for (int w = 0; w < width; w++) {
                String field = board.get(new Point(w, h));
                if (null == field || field.isEmpty()) {
                    field = " ";
                }
                System.out.print(field);
            }
            System.out.println("|");
        }

        System.out.print("+");
        for (int w = 0; w < width; w++) {
            System.out.print("-");
        }
        System.out.println("+");
    }

    public void next(int width, int height) {
        Map<Point, String> nextBoard = new HashMap<Point, String>();
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                Point point = new Point(w, h);
                int nCount = count(point);

                // Any live cell with fewer than two live neighbours dies, as if caused by under-population.
                if (is(point) && nCount < 2) {
                    // nothing to do
                }

                // Any live cell with two or three live neighbours lives on to the next generation.
                if (is(point) && nCount >= 2 && nCount <= 3) {
                    nextBoard.put(point, "x");
                }

                // Any live cell with more than three live neighbours dies, as if by over-population.
                if (is(point) && nCount > 3) {
                    // nothing to do
                }

                // Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
                if (!is(point) && nCount == 3) {
                    nextBoard.put(point, "x");
                }
            }
        }
        board = nextBoard;
    }

    private int count(Point point) {
        int x = point.getX();
        int y = point.getY();
        int count = 0;
        if (is(new Point(x - 1, y - 1))) {
            count++;
        }
        if (is(new Point(x, y - 1))) {
            count++;
        }
        if (is(new Point(x + 1, y - 1))) {
            count++;
        }
        if (is(new Point(x - 1, y))) {
            count++;
        }
        if (is(new Point(x + 1, y))) {
            count++;
        }
        if (is(new Point(x - 1, y + 1))) {
            count++;
        }
        if (is(new Point(x, y + 1))) {
            count++;
        }
        if (is(new Point(x + 1, y + 1))) {
            count++;
        }
        return count;
    }

    private boolean is(Point point) {
        String value = board.get(point);
        return null != value && value.equals("x");
    }
}
