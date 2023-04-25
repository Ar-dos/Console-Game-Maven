package entities;

public abstract class GameMovableObject extends GameObject implements Movable {
    @Override
    public boolean move(String direction) {
        boolean isMove = false;
        int x = coordinates.getX();
        int y = coordinates.getY();

        switch (direction) {
            case "left":
            case "a":
                if (x != 0) {
                    isMove = moveToCoordinates(x - 1, y, 'x');
                }
                break;
            case "right":
            case "d":
                if (x != gameField.getSize() - 1) {
                    isMove = moveToCoordinates(x + 1, y, 'x');
                }
                break;
            case "bot":
            case "s":
                if (y != gameField.getSize() - 1) {
                    isMove = moveToCoordinates(x, y + 1, 'y');
                }
                break;
            case "top":
            case "w":
                if (y != 0) {
                    isMove = moveToCoordinates(x, y - 1, 'y');
                }
                break;
        }
        return isMove;
    }

    protected abstract boolean moveToCoordinates(int x, int y, char coordinateToChange);

    protected void moveToCell(int x, int y) {
        gameField.getMatrix()[y][x] = gameField.getMatrix()[coordinates.getY()][coordinates.getX()];
        gameField.getMatrix()[coordinates.getY()][coordinates.getX()] = null;
    }

    protected int countObstacles() {
        int countObstacles = 0;
        int x = coordinates.getX();
        int y = coordinates.getY();

        if (isLeftBorder() || checkForObstacles(x - 1, y)) {
            countObstacles++;
        }
        if (isRightBorder() || checkForObstacles(x + 1, y)) {
            countObstacles++;
        }

        if (isUpperBorder() || checkForObstacles(x, y - 1)) {
            countObstacles++;
        }
        if (isLowerBorder() || checkForObstacles(x, y + 1)) {
            countObstacles++;
        }

        return countObstacles;
    }

    protected boolean isLeftBorder() {
        return coordinates.getX() == 0;
    }

    protected boolean isRightBorder() {
        return coordinates.getX() == gameField.getSize() - 1;
    }

    protected boolean isUpperBorder() {
        return coordinates.getY() == 0;
    }

    protected boolean isLowerBorder() {
        return coordinates.getY() == gameField.getSize() - 1;
    }

    protected abstract boolean checkForObstacles(int x, int y);
}
