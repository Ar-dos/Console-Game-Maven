package logic;

import entities.Enemy;
import entities.GameField;
import entities.Player;
import entities.Target;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private GameField gameField;
    private Player player;
    private Target target;
    private ArrayList<Enemy> enemies;
    private boolean development;

    public Game() {
        gameField = null;
        player = null;
        enemies = new ArrayList<>();
        development = false;
    }

    public void startGame(int size, int wallsCount, int enemiesCount, boolean development) {
        if (development) {
            DisplaySettings.loadSettings("target/classes/application-dev.properties");
        } else {
            DisplaySettings.loadSettings("target/classes/application-production.properties");
        }

        this.development = development;
        gameField = new GameField(size, wallsCount, enemiesCount);
        player = new Player(gameField);
        gameField.generateWalls(player);
        for (int i = 0; i < enemiesCount; i++) {
            Enemy enemy = new Enemy(gameField, player);
            enemies.add(enemy);
        }
        target = new Target(gameField,player);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            gameField.print();
            defeat();

            boolean check = false;
            while (!check) {
                String input = scanner.nextLine();

                if (input.equals("exit")) {
                    scanner.close();
                    System.exit(1);
                } else {
                    check = player.move(input);
                }
            }

            if (player.getCoordinates().getX() == target.getCoordinates().getX() &&
                    player.getCoordinates().getY() == target.getCoordinates().getY()) {
                System.out.println("VICTORY!");
                System.exit(1);
            }

            for (int i = 0; i < enemiesCount; i++) {
                enemies.get(i).move();
                gameField.print();
                System.out.println();
                defeat();
            }
        }
    }

    private void defeat() {
        if (player.isDefeat()) {
            System.out.println("DEFEAT!");
            System.exit(1);
        }
    }
}
