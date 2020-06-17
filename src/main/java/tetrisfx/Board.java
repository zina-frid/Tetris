package tetrisfx;

public class Board {

    private int score;
    private int highScore;
    private  int linesNum;
    private final int size;
    private final int x;
    private final int y;
    private int [][] position;

    public Board() {
        score = 0;
        size = 30;
        x = 10;
        y = 20;
        position = new int [10][20];
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getLinesNum(){
        return linesNum;
    }


    public void setLinesNum(int linesNum){
        this.linesNum = linesNum;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getPosition(int x, int y) {
        return position[x][y];
    }

    public void setPosition(int x, int y, int value) {
        this.position[x][y] = value;
    }

    public int getSize() {
        return size;
    }

    public int getHeigth() {
        int heigth = size * y;
        return heigth;
    }

    public int getWidth() {
        int width = size * x;
        return width;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

}

