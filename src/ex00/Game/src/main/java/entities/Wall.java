package entities;

import com.diogonunes.jcdp.color.api.Ansi;
import logic.DisplaySettings;

public class Wall extends GameObject {
    public Wall(int x, int y, GameField gameField) {
        this.gameField = gameField;
        character = DisplaySettings.WALL_CHAR;
        coordinates = new Coordinates(x, y);
        gameObjectType = GameObjectType.WALL;
        gameObjectColor = DisplaySettings.WALL_COLOR;
    }
}