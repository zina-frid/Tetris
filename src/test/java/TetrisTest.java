import javafx.application.Application;
import org.junit.BeforeClass;
import tetrisfx.*;

import org.junit.Test;

import static org.junit.Assert.*;


public class TetrisTest {

    @BeforeClass
    public static void initGame() throws InterruptedException {
        Thread t = new Thread("JavaFX Init Thread") {
            @Override
            public void run() {
                Application.launch(Tetris.class);
            }
        };
        t.setDaemon(true);
        t.start();
        Thread.sleep(10);
    }

    Board board = Board.board;
    Controller controller = Controller.controller;
    View view = View.view;


    @Test
    public void testForClear(){
        for (int i = 0; i < board.getY(); i++){
            board.setPosition(4, i, 1);
        }
        assertTrue(controller.isFull());
        view.clearBoard();
        assertFalse(controller.isFull());
    }

    
    @Test
    public void testForLeft() {
        view.clearBoard();
        Blocks block = new Blocks();
        int aX = (int) block.a.getX();
        int bX = (int) block.b.getX();
        int cX = (int) block.c.getX();
        int dX = (int) block.d.getX();
        block.moves("l");
        int aXX = (int) block.a.getX();
        int bXX = (int) block.b.getX();
        int cXX = (int) block.c.getX();
        int dXX = (int) block.d.getX();
        assertEquals(aX - 30, aXX);
        assertEquals(bX - 30, bXX);
        assertEquals(cX - 30, cXX);
        assertEquals(dX - 30, dXX);

    }

    @Test
    public void testForRight() {
        view.clearBoard();
        Blocks block = new Blocks();
        int aX = (int) block.a.getX();
        int bX = (int) block.b.getX();
        int cX = (int) block.c.getX();
        int dX = (int) block.d.getX();
        block.moves("r");
        int aXX = (int) block.a.getX();
        int bXX = (int) block.b.getX();
        int cXX = (int) block.c.getX();
        int dXX = (int) block.d.getX();
        assertEquals(aX + 30, aXX);
        assertEquals(bX + 30, bXX);
        assertEquals(cX + 30, cXX);
        assertEquals(dX + 30, dXX);

    }

    @Test
    public void testForDown() {
        view.clearBoard();
        Blocks block = new Blocks();
        int aY = (int) block.a.getY();
        int bY = (int) block.b.getY();
        int cY = (int) block.c.getY();
        int dY = (int) block.d.getY();
        block.moves("d");
        int aYY = (int) block.a.getY();
        int bYY = (int) block.b.getY();
        int cYY = (int) block.c.getY();
        int dYY = (int) block.d.getY();
        assertEquals(aY + 30, aYY);
        assertEquals(bY + 30, bYY);
        assertEquals(cY + 30, cYY);
        assertEquals(dY + 30, dYY);
    }

    @Test
    public void testForFull(){
        view.clearBoard();
        for (int i = 0; i < board.getY(); i++){
            board.setPosition(4, i, 1);
        }
        assertTrue(controller.isFull());

        board.setPosition(4, board.getY() - 1, 0);
        assertFalse(controller.isFull());

    }
    @Test
    public void testForConfiguration(){
        Blocks block4 = new Blocks();
        int config = block4.getCurrentCofiguration();

        block4.moves("d");
        block4.moves("d");
        block4.moves("d");
        block4.turnBlock();
        int config2 = block4.getCurrentCofiguration();
        assertEquals(config + 1, config2); //номер конфигурации изменился
    }

}