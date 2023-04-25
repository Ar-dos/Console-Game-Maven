package entities;

import com.diogonunes.jcdp.color.api.Ansi;
import logic.ChaseLogic;
import logic.DisplaySettings;

import java.lang.reflect.Field;
import java.util.Random;

public class Target extends GameObject {

    private Player player;
    public Target(GameField gameField, Player player) {
        this.gameField = gameField;
        character = DisplaySettings.TARGET_CHAR;
        gameObjectType = GameObjectType.TARGET;
        gameObjectColor = Ansi.BColor.GREEN;
        this.player = player;
        generateTarget();
    }

    private void generateTarget() {
        Random random = new Random();

        while (true) {
            int x = random.nextInt(gameField.getSize() - 1) + 1;
            int y = random.nextInt(gameField.getSize() - 1) + 1;

            if (gameField.getMatrix()[y][x] == null) {
                this.coordinates = new Coordinates(x, y);
                ChaseLogic logic = new ChaseLogic(player,gameField,coordinates);
                if (logic.checkForPath()) {
                    gameField.getMatrix()[y][x] = this;
                    break;
                }
            }
        }
    }
}