package tetrisfx;

import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Timer;
import java.util.TimerTask;

// отвечает за таймер и падение блоков вниз самостоятельно
public class Controller {

    public static final Controller controller = new Controller();
    private BlockController blockController = BlockController.blockController;
    private View view = View.view;
    Board board = Board.board;

    Stage stage;
    Blocks block;
    private boolean gameStatus = true;

    // задача и таймер который контролирует падение

        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    if(getGameStatus()) {
                        if (block.a.getY() == 0 || block.b.getY() == 0 || block.c.getY() == 0 || block.d.getY() == 0) {
                            if (isFull()) {
                                view.gameOver();
                                setGameStatus(false);

                            }
                        }

                        if (getGameStatus()) {
                            blockController.moveDown(block);

                        }
                    }

                });
            }
        };


    public void initGame() throws Exception {
        view.viewStuff(stage, block);
        timer.schedule(task, 0, 300);
    }

    private Controller() {
        block = new Blocks();
        if (controller != null) {
            throw new IllegalStateException("Already constructed");
        }
    }
    public void setGameStatus(boolean status){
        this.gameStatus = status;
    }

    public boolean getGameStatus(){
        return gameStatus;
    }


    public boolean isFull(){
        int count = 0;
        for (int i = 0; i < board.getY(); i++){
            for (int j = 0; j < board.getX(); j++){
                if(board.getPosition(j, i) == 1) {
                    count++;
                    break;
                }
            }
        }
        return count >= board.getY();
    }
}
