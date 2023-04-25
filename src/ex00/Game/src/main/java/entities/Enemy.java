package entities;

import com.diogonunes.jcdp.color.api.Ansi;
import logic.ChaseLogic;
import logic.DisplaySettings;

import java.util.Random;

public class Enemy extends GameMovableObject {
    private Player player;
    private ChaseLogic logic;

    public Enemy(GameField gameField, Player player) {
        this.gameField = gameField;
        character = DisplaySettings.ENEMY_CHAR;
        gameObjectType = GameObjectType.ENEMY;
        this.player = player;
        generateCoordinates();
        logic = new ChaseLogic(player, this.gameField, this.coordinates);
        gameObjectColor = Ansi.BColor.RED;
    }

    public boolean move() {
        String direction = logic.findPath();
        return move(direction);
    }

    @Override
    protected boolean moveToCoordinates(int x, int y, char coordinateToChange) {
        boolean isMove = false;

        if (gameField.getMatrix()[y][x] == null) {
            isMove = true;
            moveToCell(x, y);
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
        boolean isObstacle = false;

        if (gameField.getMatrix()[y][x] != null &&
                (gameField.getMatrix()[y][x].gameObjectType == GameObjectType.TARGET
                        || gameField.getMatrix()[y][x].gameObjectType == GameObjectType.WALL)) {
            isObstacle = true;
        }
        return isObstacle;
    }

    private void generateCoordinates() {
        Random random = new Random();
        while (true) {
            int x = random.nextInt(gameField.getSize());
            int y = random.nextInt(gameField.getSize());
            if (gameField.getMatrix()[y][x] == null && player.checkPositionToPlayer(x, y)) {
                coordinates = new Coordinates(x, y);
                gameField.getMatrix()[y][x] = this;
                break;
            }
        }
    }
}