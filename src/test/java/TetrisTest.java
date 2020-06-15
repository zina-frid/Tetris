
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

    @Test
    public void testForLeft() {
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

}