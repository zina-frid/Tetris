package tetrisfx;

import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class Blocks {

    public Rectangle a;
    public Rectangle b;
    public Rectangle c;
    public Rectangle d;
    char name;
    int currentCofiguration = 1;

    private View view = View.view;
    Board board = view.getBoard();

    int size = board.getSize();
    int width = board.getWidth();
    int heigth = board.getHeigth();
    int center = width / 2;
    Image image;

    //конструктор блоков
    //сгенерированное число отсылает к одной из семи фигур
    //фигура собирается из 4х соответствующе окрашенных прямоугольников в центре поля

    public Blocks(){

        a = new Rectangle(30,30);
        b = new Rectangle(30,30);
        c = new Rectangle(30,30);
        d = new Rectangle(30,30);
        int random = (int) (Math.random() * 70);

        if (random < 11) {
            a.setX(center - size);
            a.setY(0);
            b.setX(center);
            b.setY(0);
            c.setX(center +  size);
            c.setY(0);
            d.setX(center + 2 * size);
            d.setY(0);

            image = new Image("/yellow.png");
            name = 'I';

        } else if (random < 21) {
            a.setX(center + size);
            a.setY(0);
            b.setX(center + size);
            b.setY(size);
            c.setX(center + size);
            c.setY(2 * size);
            d.setX(center);
            d.setY(2 * size);

            image = new Image("/purple.png");
            name = 'J';

        } else if (random < 31) {
            a.setX(center);
            a.setY(0);
            b.setX(center);
            b.setY(size);
            c.setX(center);
            c.setY(2 * size);
            d.setX(center + size);
            d.setY(2 * size);

            image = new Image("/darkblue.png");
            name = 'L';

        } else if (random < 41) {
            a.setX(center);
            a.setY(0);
            b.setX(center + size);
            b.setY(0);
            c.setX(center);
            c.setY(size);
            d.setX(center + size);
            d.setY(size);

            image = new Image("/red.png");
            name = 'O';

        } else if (random < 51) {
            a.setX(center + size);
            a.setY(0);
            b.setX(center);
            b.setY(0);
            c.setX(center);
            c.setY(size);
            d.setX(center - size);
            d.setY(size);

            image = new Image("/orange.png");
            name = 'S';

        } else if (random < 61) {
            a.setX(center - size);
            a.setY(0);
            b.setX(center);
            b.setY(0);
            c.setX(center + size);
            c.setY(0);
            d.setX(center);
            d.setY(size);

            image = new Image("/strange.png");
            name = 'T';

        } else {
            a.setX(center);
            a.setY(0);
            b.setX(center + size);
            b.setY(0);
            c.setX(center + size);
            c.setY(size);
            d.setX(center + 2 * size);
            d.setY(size);

            image = new Image("/blue.png");
            name = 'Z';
        }

        a.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        b.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        c.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
        d.setFill(new ImagePattern(image, 0, 0, 1, 1, true));
    }


    //смещения влево, вправо и вниз
    //проверка на то, есть ли возможность сдвинуться в пределах поля
    //проверка на то, нет ли ничего там, куда фигура сдвинется

    public boolean moves(String flag) {
        int moveX = 0;
        int moveY = 0;
        boolean end = false;

        //смещение влево

        if (flag.equals("l")){
            if (a.getX() - size >= 0 && b.getX() - size >= 0 && c.getX() - size >= 0 && d.getX() - size >= 0){
                moveX = -1;
            }
        }

        //смещение вправо

        if (flag.equals("r")) {
            if (a.getX() + size <= width - size && b.getX() + size <= width - size &&
                    c.getX() + size <= width - size && d.getX() + size <= width - size) {
                moveX = 1;
            }
        }

        //смещение вниз
        //проверка на то, нет ли ничего там, куда фигура сдвинется
        //если всё впрорядке, то сдвигаем

        if (flag.equals("d")){

            if ((int) a.getY() == heigth - size || (int) b.getY() == heigth - size ||
                    (int) c.getY() == heigth - size || (int) d.getY() == heigth - size) {

                board.setPosition((int) a.getX() / size, (int) a.getY() / size, 1);
                board.setPosition((int) b.getX() / size, (int) b.getY() / size, 1);
                board.setPosition((int) c.getX() / size, (int) c.getY() / size, 1);
                board.setPosition((int) d.getX() / size, (int) d.getY() / size, 1);

                end = true;

            } else {
                // проверка та то, нет ли блока там, куда сдвинется
                int nextA = board.getPosition((int) a.getX() / size, ((int) a.getY() / size) + 1);
                int nextB = board.getPosition((int) b.getX() / size, ((int) b.getY() / size) + 1);
                int nextC = board.getPosition((int) c.getX() / size, ((int) c.getY() / size) + 1);
                int nextD = board.getPosition((int) d.getX() / size, ((int) d.getY() / size) + 1);

                if (nextA == 1 || nextB == 1 || nextC == 1 || nextD == 1) {
                    //если есть, то устанавливаем там, где находится сейчас
                    board.setPosition((int) a.getX() / size, (int) a.getY() / size, 1);
                    board.setPosition((int) b.getX() / size, (int) b.getY() / size, 1);
                    board.setPosition((int) c.getX() / size, (int) c.getY() / size, 1);
                    board.setPosition((int) d.getX() / size, (int) d.getY() / size, 1);

                    end = true;
                }
            }
            if (a.getY() + size <= heigth - size && b.getY() + size <= heigth - size
                    && c.getY() + size <= heigth - size && d.getY() + size <= heigth - size) {
                moveY = 1;
            }
        }

        int moveA = board.getPosition((int) a.getX() / size + moveX, (int) a.getY() / size + moveY);
        int moveB = board.getPosition((int) b.getX() / size + moveX, (int) b.getY() / size + moveY);
        int moveC = board.getPosition((int) c.getX() / size + moveX, (int) c.getY() / size + moveY);
        int moveD = board.getPosition((int) d.getX() / size + moveX, (int) d.getY() / size + moveY);

        if (moveA == 0 && moveB == 0 && moveC == 0 && moveD == 0){
            a.setX(a.getX() + moveX * size);
            b.setX(b.getX() + moveX * size);
            c.setX(c.getX() + moveX * size);
            d.setX(d.getX() + moveX * size);
            a.setY(a.getY() + moveY * size);
            b.setY(b.getY() + moveY * size);
            c.setY(c.getY() + moveY * size);
            d.setY(d.getY() + moveY * size);
        }

        return end;
    }


    // Переворот блока для разных типов
    // Использует мотод, который определяет возможно ли смещение прямоугольника в заданную клетку,
    // метод, возвращающий номер текущей конфигурации, и метод меняющий номер конфигурации

    public void turnBlock() {
        int turn = 0;
        switch (name){
            case 'I':
                if(getCurrentCofiguration() % 2 == 1 && changePosition(a, 1, -1)
                        && changePosition(c, -1, 1) && changePosition(d, -2, 2)) {
                    turn = 1;

                } else if(getCurrentCofiguration() % 2 == 0 && changePosition(a, -1, 1)
                        && changePosition(c, 1, -1) && changePosition(d, 2, -2)) {
                    turn = -1;
                }
                a.setX(a.getX() + turn * size);
                a.setY(a.getY() - turn * size);
                c.setX(c.getX() - turn * size);
                c.setY(c.getY() + turn * size);
                d.setX(d.getX() - 2 * turn * size);
                d.setY(d.getY() + 2 * turn * size);

                changeConfiguration();
                break;

            case 'J':
                if(getCurrentCofiguration() == 1
                        && changePosition(a, -1, 0) && changePosition(b, 0, -1)
                        && changePosition(c, 1, -2) && changePosition(d, 2, -1)) {

                    a.setX(a.getX() - size);
                    b.setY(b.getY() - size);
                    c.setX(c.getX() + size);
                    c.setY(c.getY() - 2 * size);
                    d.setX(d.getX() + 2 * size);
                    d.setY(d.getY() - size);

                    changeConfiguration();

                } else if (getCurrentCofiguration() == 2
                        && changePosition(a, 0, 2) && changePosition(b, -1, 1)
                        && changePosition(c, -2, 0) && changePosition(d, -1, -1)) {

                    a.setY(a.getY() + 2 * size);
                    b.setX(b.getX() - size);
                    b.setY(b.getY() + size);
                    c.setX(c.getX() - 2 * size);
                    d.setX(d.getX() - size);
                    d.setY(d.getY() - size);

                    changeConfiguration();

                } else if(getCurrentCofiguration() == 3
                        && changePosition(a, 2, -1) && changePosition(b, 1, 0)
                        && changePosition(c, 0, 1) && changePosition(d, -1, 0)) {

                    a.setX(a.getX() + 2 * size);
                    a.setY(a.getY() - size);
                    b.setX(b.getX() + size);
                    c.setY(c.getY() + size);
                    d.setX(d.getX() - size);

                    changeConfiguration();

                } else if (getCurrentCofiguration() == 4 && changePosition(a, -1, -1)
                        && changePosition(c, 1, 1) && changePosition(d, 0, 2)) {

                    a.setX(a.getX() - size);
                    a.setY(a.getY() - size);
                    c.setX(c.getX() + size);
                    c.setY(c.getY() + size);
                    d.setY(d.getY() + 2 * size);

                    changeConfiguration();
                }
                break;

            case 'L':
                if(getCurrentCofiguration() == 1
                        && changePosition(a, 0, 1) && changePosition(b, 1, 0)
                        && changePosition(c, 2, -1) && changePosition(d, 1, -2)) {

                    a.setY(a.getY() + size);
                    b.setX(b.getX() + size);
                    c.setX(c.getX() + 2 * size);
                    c.setY(c.getY() - size);
                    d.setX(d.getX() + size);
                    d.setY(d.getY() - 2 * size);

                    changeConfiguration();

                } else if(getCurrentCofiguration() == 2 && changePosition(a, 1, 1)
                        && changePosition(c, -1, -1) && changePosition(d, -2, 0)) {

                    a.setX(a.getX() + size);
                    a.setY(a.getY() + size);
                    c.setX(c.getX() - size);
                    c.setY(c.getY() - size);
                    d.setX(d.getX() - 2 * size);

                    changeConfiguration();

                } else if(getCurrentCofiguration() == 3
                        && changePosition(a, 1, -2) && changePosition(b, 0, -1)
                        && changePosition(c, -1, 0) && changePosition(d, 0, 1)) {

                    a.setX(a.getX() + size);
                    a.setY(a.getY() - 2 * size);
                    b.setY(b.getY() - size);
                    c.setX(c.getX() - size);
                    d.setY(d.getY() + size);

                    changeConfiguration();

                } else if(getCurrentCofiguration() == 4
                        && changePosition(a, -2, 0) && changePosition(b, -1, 1)
                        && changePosition(c, 0, 2) && changePosition(d, 1, 1) ) {

                    a.setX(a.getX() - 2 * size);
                    b.setX(b.getX() - size);
                    b.setY(b.getY() + size);
                    c.setY(c.getY() + 2 * size);
                    d.setX(d.getX() + size);
                    d.setY(d.getY() + size);

                    changeConfiguration();
                }
                break;

            case 'O':
                break;

            case 'S':
                if(getCurrentCofiguration() % 2 == 1
                        && changePosition(a, -2, -1) && changePosition(d, 0, -1)) {
                    turn = -1;

                } else if(getCurrentCofiguration() % 2 == 0
                        && changePosition(a, 2, 1) && changePosition(d, 0, 2)) {
                    turn = 1;
                }
                a.setX(a.getX() + turn * 2 * size);
                a.setY(a.getY() + turn * size);
                d.setY(d.getY() + turn * size);

                changeConfiguration();
                break;

            case 'T':
                if(getCurrentCofiguration() % 2 == 1){
                    if(getCurrentCofiguration() == 1 && changePosition(a, 1, -1)
                        && changePosition(c, -1, 1) && changePosition(d, -1, -1)) {
                    turn = 1;

                } else if(getCurrentCofiguration() == 3 && changePosition(a, -1, 1)
                            && changePosition(c, 1, -1) && changePosition(d, 1, 1)) {
                        turn = - 1;
                    }
                    a.setX(a.getX() + turn * size);
                    a.setY(a.getY() - turn * size);
                    c.setX(c.getX() - turn * size);
                    c.setY(c.getY() + turn * size);
                    d.setX(d.getX() - turn * size);
                    d.setY(d.getY() - turn * size);

                    changeConfiguration();
                }
                else if(getCurrentCofiguration() % 2 == 0) {
                    if (getCurrentCofiguration() == 2 && changePosition(a, 1, 1)
                            && changePosition(c, -1, -1) && changePosition(d, 1, -1)) {
                        turn = 1;

                    } else if (getCurrentCofiguration() == 4 && changePosition(a, -1, -1)
                            && changePosition(c, 1, 1) && changePosition(d, -1, 1)) {
                        turn = -1;
                    }
                    a.setX(a.getX() + turn * size);
                    a.setY(a.getY() + turn * size);
                    c.setX(c.getX() - turn * size);
                    c.setY(c.getY() - turn * size);
                    d.setX(d.getX() + turn * size);
                    d.setY(d.getY() - turn * size);

                    changeConfiguration();
                }
                break;

            case 'Z':
                if(getCurrentCofiguration() % 2 == 1
                        && changePosition(c, -1, 0) && changePosition(d, -1, -2)) {
                    turn = -1;

                }else if(getCurrentCofiguration() % 2 == 0 && changePosition(c, 1, 0)
                        && changePosition(d, 1, 2)) {
                    turn = 1;
                }
                c.setX(c.getX() + turn * size);
                d.setX(d.getX() + turn * size);
                d.setY(d.getY() + turn * 2 * size);

                changeConfiguration();
                break;
        }
    }

    //возвращает true, если можно сместить переданный прямоугольник на заданное количество клеток

    public boolean changePosition(Rectangle rect, int h, int v){

        boolean horizontal = false;
        boolean vertical = false;

        if(h >= 0 && ((rect.getX() + h * size) <= (width - size))) {
            if(board.getPosition((int) (rect.getX() + h * size) / size, ((int) rect.getY() / size)) == 0)
                horizontal = true;

        } else if (h < 0 && ((rect.getX() + h * size) >= 0)) {
            if(board.getPosition((int) (rect.getX() + h * size) / size, ((int) rect.getY() / size)) == 0)
                horizontal = true;
        }

        if(v < 0 && ((rect.getY() + v * size) >= 0)) {
            if(board.getPosition((int) rect.getX() / size, (int) (rect.getY() + v * size) / size) == 0)
                vertical = true;
        } else if (v >= 0 && ((rect.getY() + v * size) <= heigth - size)) {
            if(board.getPosition((int) rect.getX() / size, (int) (rect.getY() + v * size) / size) == 0)
                vertical = true;
        }

        return (horizontal && vertical);
    }

    //возвращает номер текущей конфигурации

    public int getCurrentCofiguration(){
        return currentCofiguration;
    }

    //меняет номер текущей конфигурации

    public void changeConfiguration(){
        if (currentCofiguration != 4) currentCofiguration++;
        else currentCofiguration = 1;
    }

}