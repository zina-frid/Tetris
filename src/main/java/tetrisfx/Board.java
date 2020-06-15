package tetrisfx;

public class Board {

    private int score;
    private int highScore;
    private  int linesNum;
    private final int size;
    private final int x;
    private final int y;
    private final int heigth;
    private final int width;
    private int [][] position;

    public static final Board board = new Board();

    public Board() {
        score = 0;
        size = 30;
        x = 10;
        y = 20;
        heigth = size * y;
        width = size * x;
        position = new int [10][20];

        if (board != null) {
            throw new IllegalStateException("Board is already exist");
        }
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
        return heigth;
    }


    public int getWidth() {
        return width;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

}

