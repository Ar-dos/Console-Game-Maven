package logic;

import com.diogonunes.jcdp.color.api.Ansi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;

public class DisplaySettings {
    public static char PLAYER_CHAR = 'o';
    public static char ENEMY_CHAR = 'x';
    public static char WALL_CHAR = '#';
    public static char TARGET_CHAR = 'O';
    public static char EMPTY_CHAR = ' ';

    public static Ansi.BColor PLAYER_COLOR = Ansi.BColor.GREEN;
    public static Ansi.BColor ENEMY_COLOR = Ansi.BColor.RED;
    public static Ansi.BColor WALL_COLOR = Ansi.BColor.MAGENTA;
    public static Ansi.BColor TARGET_COLOR = Ansi.BColor.BLUE;
    public static Ansi.BColor EMPTY_COLOR = Ansi.BColor.YELLOW;

    public static void loadSettings(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            while (reader.ready()) {
                String line = reader.readLine();
                String[] params = line.split("=");
                if (params[0].trim().equals("player.char") && params.length == 2) {
                    DisplaySettings.PLAYER_CHAR = params[1].trim().charAt(0);
                } else if (params[0].trim().equals("enemy.char") && params.length == 2) {
                    DisplaySettings.ENEMY_CHAR = params[1].trim().charAt(0);
                } else if (params[0].trim().equals("empty.char") && params.length == 2) {
                    DisplaySettings.EMPTY_CHAR = params[1].trim().charAt(0);
                } else if (params[0].trim().equals("wall.char") && params.length == 2) {
                    DisplaySettings.WALL_CHAR = params[1].trim().charAt(0);
                } else if (params[0].trim().equals("target.char") && params.length == 2) {
                    DisplaySettings.TARGET_CHAR = params[1].trim().charAt(0);
                } else if (params[0].trim().equals("enemy.color") && params.length == 2) {
                    DisplaySettings.ENEMY_COLOR = Ansi.BColor.valueOf(params[1].trim());
                } else if (params[0].trim().equals("player.color") && params.length == 2) {
                    DisplaySettings.PLAYER_COLOR = Ansi.BColor.valueOf(params[1].trim());
                } else if (params[0].trim().equals("wall.color") && params.length == 2) {
                    DisplaySettings.WALL_COLOR = Ansi.BColor.valueOf(params[1].trim());
                } else if (params[0].trim().equals("empty.color") && params.length == 2) {
                    DisplaySettings.EMPTY_COLOR = Ansi.BColor.valueOf(params[1].trim());
                } else if (params[0].trim().equals("target.color") && params.length == 2) {
                    DisplaySettings.TARGET_COLOR = Ansi.BColor.valueOf(params[1].trim());
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
