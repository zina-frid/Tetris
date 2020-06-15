package tetrisfx;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

//визуальная часть

public class View {

    public static final View view = new View();
    BlockController blockController = BlockController.blockController;

    Board board = Board.board;
    private Blocks block;

    private Pane group = new Pane();
    private Scene scene = new Scene(group, board.getWidth() + 300, board.getHeigth());
    Text lines = new Text ("Lines: ");
    Text score = new Text("Score: ");
    Text highScore = new Text("Hi-score: ");
    Text gameOver = new Text("GAME OVER!");
    Text instr = new Text ("Press ENTER to start new game");
    Text instr2 = new Text ("Press ESCAPE to quit the game");


    private View (){
        if(view != null) throw new IllegalStateException();
    }

    //блок в сцену
    public void addBlockToStage(Blocks block){
        group.getChildren().addAll(block.a, block.b, block.c, block.d);
    }

    //добавление всякой всячины
    public void viewStuff(Stage stage, Blocks currblock) throws Exception {

        block = currblock;
        blockController = BlockController.blockController;

        Image image = new Image(Tetris.class.getResourceAsStream("/back1.png"));
        ImageView imageView = new ImageView(image);
        imageView.setX(300);
        imageView.setY(0);
        group.getChildren().add(imageView);

        score.setStyle("-fx-font: 27 arials;");
        score.setX(board.getWidth() + 17);
        score.setY(board.getSize() * 2);
        score.setText("Score: " + board.getScore());
        score.setFill(Color.BLACK);

        lines.setStyle("-fx-font: 27 arials;");
        lines.setX(board.getWidth() + 17);
        lines.setY(board.getSize() * 4);
        lines.setText("Lines: " + board.getLinesNum());
        lines.setFill(Color.BLACK);

        highScore.setStyle("-fx-font: 27 arials;");
        highScore.setX(board.getWidth() + 17);
        highScore.setY(board.getSize() * 6);
        highScore.setText("High score: " + board.getHighScore());
        highScore.setFill(Color.BLACK);

        group.getChildren().addAll(score, lines, highScore);

        Image image1 = new Image(Tetris.class.getResourceAsStream("/try1.png"),
                board.getWidth(), board.getHeigth(), false, true);

        BackgroundImage back = new BackgroundImage(image1, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        group.setBackground(new Background(back));

        group.getChildren().addAll(block.a, block.b, block.c, block.d);
        stage.setScene(scene);
        stage.setTitle("T E T R I S");
        stage.show();
        stage.setOnCloseRequest(windowEvent -> System.out.println("CLOSED"));

        scene.setOnKeyPressed(event -> {

            if (event.getCode() == KeyCode.LEFT || event.getCode() == KeyCode.A) {
                blockController.moveToTheLeft(block);
            } else if (event.getCode() == KeyCode.RIGHT || event.getCode() == KeyCode.D) {
                blockController.moveToTheRight(block);
            } else if (event.getCode() == KeyCode.DOWN || event.getCode() == KeyCode.S) {
                blockController.moveDown(block);
            } else if (event.getCode() == KeyCode.UP || event.getCode() == KeyCode.W) {
                blockController.turnBlock(block);
            } else if (event.getCode() == KeyCode.ENTER) {
                blockController.newGame();
            }else if (event.getCode() == KeyCode.ESCAPE) {
                System.exit(0);
            }

        });
    }

    public void removeRows() {
        List<Node> current = new ArrayList<>();
        List<Node> future = new ArrayList<>();
        int[] linesToRemove = new int[4];//самая большое возможное количество подряд
        int count = 0; // количество заполненных "клеток" в ряду
        int rows = -1;


        for (int i = 0; i < board.getY(); i++) {
            for (int j = 0; j < board.getX(); j++) {
                if (board.getPosition(j, i) == 1)
                    count++;
            }
            if (count == board.getX()) {
                rows++;
                linesToRemove[rows] = i;
            }
            count = 0;
        }

        if(rows != -1) {
            board.setScore(board.getScore() + 10 * (2 * rows + 1));
            score.setText("Score: " + board.getScore());
            board.setLinesNum(board.getLinesNum() + (rows + 1));
            lines.setText("Lines: " + board.getLinesNum());
        }

        int iterator = 0;

        if (rows > -1) {
            do {
                for(int i = 0; i < board.getX(); i++) {
                    for (int j = 0; j < board.getY(); j++) {
                        board.setPosition(i, j, 0);
                    }
                }

                for (Node node : group.getChildren()) {
                    if (node instanceof Rectangle)
                        current.add(node);
                }

                for (Node node : current) {
                    Rectangle temp = (Rectangle) node;
                    if ((int) temp.getY() / board.getSize() == linesToRemove[iterator]) {
                        group.getChildren().remove(temp);
                    } else
                        future.add(temp);
                }

                for (Node node : future) {
                    Rectangle temp = (Rectangle) node;
                    if ((int) temp.getY() / board.getSize() < linesToRemove[iterator]) {
                        temp.setY(temp.getY() + board.getSize());
                    }
                }

                current.clear();
                future.clear();
                iterator++;
                rows--;

                for (Node node : group.getChildren()) {
                    if (node instanceof Rectangle) {
                        Rectangle temp = (Rectangle) node;
                        board.setPosition((int) temp.getX() / board.getSize(),
                                (int) temp.getY() / board.getSize(), 1);

                    }
                }

            } while (rows >= 0);
        }
    }


    public void clearBoard() {
        List<Node> list = new ArrayList<>();

        for (Node node : group.getChildren()) {
            if (node instanceof Rectangle)
                list.add(node);
        }

        for(int i = 0; i < board.getX(); i++) {
            for (int j = 0; j < board.getY(); j++) {
                board.setPosition(i, j, 0);
            }
        }

        for (Node node : list) {
            Rectangle temp = (Rectangle) node;
            group.getChildren().remove(temp);
        }
        group.getChildren().removeAll(gameOver, instr2, instr);
    }

    public void gameOver() {
        gameOver.setStyle("-fx-font: 80 arials;");
        gameOver.setX(60);
        gameOver.setY((int)(board.getHeigth() / 2 - 40));
        gameOver.setFill(Color.DARKRED);

        instr.setStyle("-fx-font: 40 arials;");
        instr.setX(35);
        instr.setY((int)(board.getHeigth() / 2 + 40 ));
        instr.setFill(Color.WHITE);

        instr2.setStyle("-fx-font: 40 arials;");
        instr2.setX(38);
        instr2.setY((int)(board.getHeigth() / 2 + 85));
        instr2.setFill(Color.WHITE);

        group.getChildren().addAll(gameOver, instr, instr2);
        highScore.setText("High score: " + board.getHighScore());


    }

    public void setBlock(Blocks block) {
        this.block = block;
    }

}



