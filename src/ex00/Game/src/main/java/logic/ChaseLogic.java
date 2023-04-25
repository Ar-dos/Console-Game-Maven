package logic;

import entities.*;

public class ChaseLogic {
    private int[][] matrix;
    private Coordinates from;
    private Coordinates to;
    private int x;
    private int y;
    private int size;
    private GameField field;

    public ChaseLogic(Player player, GameField f , Coordinates coordinates) {
        from = coordinates;
        to = player.getCoordinates();
        field = f;
        size = field.getSize();
        matrix = new int[size][size];
    }

    void scanMap(GameField gameField) {
        x = from.getX();
        y = from.getY();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (gameField.getMatrix()[y][x] == null) {
                    matrix[y][x] = 0;
                } else if (gameField.getMatrix()[y][x].getGameObjectType() == GameObjectType.PLAYER) {
                    matrix[y][x] = 0;
                } else {
                    matrix[y][x] = 2;
                }
            }
        }
    }

    void print() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.printf("%d", matrix[y][x]);
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean checkForPath() {
        boolean res = true;
        scanMap(field);
        int count = 0;
        while ((x != to.getX() || y != to.getY()) && count < size * size) {
            searchForWay();
            count++;
        }
        if (count == size * size)
            res = false;
        return res;
    }

    public String findPath() {
        checkForPath();
        String res = "";
        if (getRightWay(from.getX(), from.getY(), 1)) {
            res = "right";
        } else if (getDownWay(from.getX(), from.getY(), 1)) {
            res = "bot";
        } else if (getLeftWay(from.getX(), from.getY(), 1)) {
            res = "left";
        } else if (getUpWay(from.getX(), from.getY(), 1)) {
            res = "top";
        }
        return res;
    }

    boolean getLeftWay(int dx, int dy, int v) {
        boolean res = false;
        if (dx != 0) {
            if (matrix[dy][dx - 1] == v) {
                res = true;
            }
        }
        return res;
    }

    boolean getUpWay(int dx, int dy, int v) {
        boolean res = false;
        if (dy != 0) {
            if (matrix[dy - 1][dx] == v) {
                res = true;
            }
        }
        return res;
    }

    boolean getRightWay(int dx, int dy, int v) {
        boolean res = false;
        if (dx != size - 1) {
            if (matrix[dy][dx + 1] == v) {
                res = true;
            }
        }
        return res;
    }

    boolean getDownWay(int dx, int dy, int v) {
        boolean res = false;
        if (dy != size - 1) {
            if (matrix[dy + 1][dx] == v) {
                res = true;
            }
        }
        return res;
    }

    void searchForWay() {
        int v = 0;
        matrix[y][x] = 1;
        if (chooseDestinationPriority(v)) {
            v++;
            matrix[y][x] = 2;
            chooseDestinationPriority(v);
        }
    }

    boolean chooseDestinationPriority(int v) {
        boolean res = true;
        int x = to.getX();
        int y = to.getY();

        if (x > this.x && y == this.y) {
            res = R(v);
        } else if (x > this.x && y > this.y) {
            res = RD(v);
        } else if (x == this.x && y > this.y) {
            res = D(v);
        } else if (x < this.x && y > this.y) {
            res = LD(v);
        } else if (x < this.x && y == this.y) {
            res = L(v);
        } else if (x < this.x && y < this.y) {
            res = LT(v);
        } else if (x == this.x && y < this.y) {
            res = T(v);
        } else if (x > this.x && y < this.y) {
            res = RT(v);
        }

        return res;
    }

    boolean R(int v) {
        boolean res = true;
        if (getRightWay(this.x, this.y, v)) {
            x++;
            res = false;
        } else if (getDownWay(this.x, this.y, v)) {
            y++;
            res = false;
        } else if (getUpWay(this.x, this.y, v)) {
            y--;
            res = false;
        } else if (getLeftWay(this.x, this.y, v)) {
            x--;
            res = false;
        }
        return res;
    }

    boolean RD(int v) {
        boolean res = true;
        if (getRightWay(this.x, this.y, v)) {
            x++;
            res = false;
        } else if (getDownWay(this.x, this.y, v)) {
            y++;
            res = false;
        } else if (getLeftWay(this.x, this.y, v)) {
            x--;
            res = false;
        } else if (getUpWay(this.x, this.y, v)) {
            y--;
            res = false;
        }
        return res;
    }

    boolean D(int v) {
        boolean res = true;
        if (getDownWay(this.x, this.y, v)) {
            y++;
            res = false;
        } else if (getRightWay(this.x, this.y, v)) {
            x++;
            res = false;
        } else if (getLeftWay(this.x, this.y, v)) {
            x--;
            res = false;
        } else if (getUpWay(this.x, this.y, v)) {
            y--;
            res = false;
        }
        return res;
    }

    boolean DR(int v) {
        boolean res = true;
        if (getDownWay(this.x, this.y, v)) {
            y++;
            res = false;
        } else if (getRightWay(this.x, this.y, v)) {
            x++;
            res = false;
        } else if (getLeftWay(this.x, this.y, v)) {
            x--;
            res = false;
        } else if (getUpWay(this.x, this.y, v)) {
            y--;
            res = false;
        }
        return res;
    }

    boolean LD(int v) {
        boolean res = true;
        if (getLeftWay(this.x, this.y, v)) {
            x--;
            res = false;
        } else if (getDownWay(this.x, this.y, v)) {
            y++;
            res = false;
        } else if (getRightWay(this.x, this.y, v)) {
            x++;
            res = false;
        } else if (getUpWay(this.x, this.y, v)) {
            y--;
            res = false;
        }
        return res;
    }

    boolean L(int v) {
        boolean res = true;
        if (getLeftWay(this.x, this.y, v)) {
            x--;
            res = false;
        } else if (getDownWay(this.x, this.y, v)) {
            y++;
            res = false;
        } else if (getUpWay(this.x, this.y, v)) {
            y--;
            res = false;
        } else if (getRightWay(this.x, this.y, v)) {
            x++;
            res = false;
        }
        return res;
    }

    boolean LT(int v) {
        boolean res = true;
        if (getLeftWay(this.x, this.y, v)) {
            x--;
            res = false;
        } else if (getUpWay(this.x, this.y, v)) {
            y--;
            res = false;
        } else if (getRightWay(this.x, this.y, v)) {
            x++;
            res = false;
        } else if (getDownWay(this.x, this.y, v)) {
            y++;
            res = false;
        }
        return res;
    }

    boolean T(int v) {
        boolean res = true;
        if (getUpWay(this.x, this.y, v)) {
            y--;
            res = false;
        } else if (getLeftWay(this.x, this.y, v)) {
            x--;
            res = false;
        } else if (getRightWay(this.x, this.y, v)) {
            x++;
            res = false;
        } else if (getDownWay(this.x, this.y, v)) {
            y++;
            res = false;
        }
        return res;
    }

    boolean RT(int v) {
        boolean res = true;
        if (getRightWay(this.x, this.y, v)) {
            x++;
            res = false;
        } else if (getUpWay(this.x, this.y, v)) {
            y--;
            res = false;
        } else if (getLeftWay(this.x, this.y, v)) {
            x--;
            res = false;
        } else if (getDownWay(this.x, this.y, v)) {
            y++;
            res = false;
        }
        return res;
    }
}