package tetrisfx;

public class BlockController {

    public static final BlockController blockController = new BlockController();
    private Controller controller;
    private View view = View.view;
    private Board board = Board.board;



    private BlockController() {

        if (blockController != null) {
            throw new IllegalStateException("BlockCotroller is already exist");
        }
    }

    public void moveToTheLeft(Blocks block) {
        block.moves("l");
    }

    public void moveToTheRight(Blocks block) {
        block.moves("r");
    }

    // Отвечает за падение вниз
    // Если блок достиг низа или других блоков, генерирует новый блок наверху

    public void moveDown(Blocks block) {

            if (block.moves("d")) {

                controller = Controller.controller;

                if (controller.getGameStatus()) {
                    controller.block = new Blocks();
                    block = controller.block;

                    view.removeRows();
                    view.setBlock(block);
                    view.addBlockToStage(block);
                }
            }
        }


    public void turnBlock(Blocks block) {
        block.turnBlock();
    }

    public void newGame() {
        view.clearBoard();
        controller.setGameStatus(true);
        if(board.getHighScore() < board.getScore())
            board.setHighScore(board.getScore());
        board.setScore(0);
        board.setLinesNum(0);
        view.score.setText("Score: " + board.getScore());
        view.lines.setText("Lines: " + board.getLinesNum());
        view.highScore.setText("High score: " + board.getHighScore());
        controller.block = new Blocks();
        view.setBlock(controller.block);
        view.addBlockToStage(controller.block);

    }

}
