package entities;

import com.diogonunes.jcdp.color.api.Ansi;
import logic.DisplaySettings;

import java.util.Random;

public class Player extends GameMovableObject {
    public Player(GameField gameField) {

        this.gameField = gameField;
        character = DisplaySettings.PLAYER_CHAR;
        gameObjectType = GameObjectType.PLAYER;
        gameObjectColor = Ansi.BColor.GREEN;
        generateCoordinates();
    }

    public boolean isDefeat() {
        return countObstacles() == 4;
    }

    @Override
    protected boolean moveToCoordinates(int x, int y, char coordinateToChange) {
        boolean isMove = false;

        if (gameField.getMatrix()[y][x] == null) {
            isMove = true;
            moveToCell(x, y);
        } else if (gameField.getMatrix()[y][x].gameObjectType == GameObjectType.TARGET) {
            isMove = true;
        }

        if (isMove) {
            if (coordinateToChange == 'x') {
                coordinates.setX(x);
            } else {
                coordinates.setY(y);
            }
        }
        return isMove;
    }

    @Override
    protected boolean checkForObstacles(int x, int y) {
        return gameField.getMatrix()[y][x] != null &&
                (gameField.getMatrix()[y][x].gameObjectType == GameObjectType.ENEMY
                        || gameField.getMatrix()[y][x].gameObjectType == GameObjectType.WALL);
    }

    private void generateCoordinates() {
        Random random = new Random();
        while (true) {
            int x = random.nextInt(gameField.getSize());
            int y = random.nextInt(gameField.getSize());
            if (gameField.getMatrix()[y][x] == null) {
                coordinates = new Coordinates(x, y);
                gameField.getMatrix()[y][x] = this;
                break;
            }
        }

    }
    public boolean checkPositionToPlayer(int x, int y) {
        return Math.abs(x - getCoordinates().getX()) > 1 ||
                Math.abs(y - getCoordinates().getY()) > 1 ;
    }
}