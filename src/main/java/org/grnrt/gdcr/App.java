package org.grnrt.gdcr;

/**
 * Lets play...
 */
public class App {
    public static void main( String[] args ) throws Exception {
        Game game = new Game();

        // init the board
        game.init(
                // blinker
//                new Point(8, 1),
//                new Point(8, 2),
//                new Point(8, 3),

                // glider
                new Point(2, 2),
                new Point(3, 3),
                new Point(4, 1),
                new Point(4, 2),
                new Point(4, 3)
                );


        // loop forever (show, calculate, sleep a little bit)
        while (true) {
            game.show(10, 10);
            Thread.sleep(500);
            game.next(10, 10);
        }
    }
}
