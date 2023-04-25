package app;

import logic.Game;
import com.beust.jcommander.*;

public class Program {
    @Parameters(separators = "=")
    static class Settings {
        @Parameter(names = {"--enemiesCount"})
        private Integer enemiesCount;
        @Parameter(names = {"--wallsCount"})
        private Integer wallsCount;
        @Parameter(names = {"--size"})
        private Integer size;
        @Parameter(names = {"--profile"}, required = false)
        private String profile = "production";
    }
    public static void main(String[] args) {

        Settings settings = new Settings();
        try {
            new JCommander(settings, args);
        } catch (Exception e) {
            System.out.println("Wrong input!");
            System.exit(-1);
        }

        if (inputCheck(settings.size, settings.wallsCount, settings.enemiesCount)) {
            Game game = new Game();
            game.startGame(settings.size, settings.wallsCount, settings.enemiesCount,isProfileDevelopment(settings.profile));
        } else {
            throw new IllegalParametersException("Wrong parameters.");
        }

    }
    static boolean inputCheck(int size, int wallsCount, int enemiesCount) {
        return wallsCount+enemiesCount < size * size - 8;
    }

    static boolean isProfileDevelopment(String profile) {
        return profile.equals("development");
    }
    static public class IllegalParametersException extends RuntimeException {
        public IllegalParametersException(String errorMessage) {
            super(errorMessage);
        }
    }

}

