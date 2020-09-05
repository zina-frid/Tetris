package tetrisfx;

import javafx.application.Application;
import javafx.stage.Stage;

public class Tetris extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

       Controller controller = Controller.controller;
       controller.stage = primaryStage;

       controller.initGame();

    }
    public static void main(String[] args) {
        // Запускаем JavaFX-приложение
        launch(args);
    }
}
