package entities;

import com.diogonunes.jcdp.color.api.Ansi.BColor;

public class GameObject {
    protected GameField gameField;
    protected Coordinates coordinates;
    protected Character character;
    protected GameObjectType gameObjectType;
    protected BColor gameObjectColor;

    public GameObject() {
        gameField = null;
        coordinates = null;
        character = null;
        gameObjectType = null;
    }

    public BColor getGameObjectColor() {return gameObjectColor;}

    public GameField getGameField() {
        return gameField;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public Character getCharacter() {
        return character;
    }

    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }
}