package entities;

import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.*;


import java.util.Random;


public class GameField {
    private final int size;
    private final int wallsCount;
    private final int enemiesCount;

    private GameObject[][] matrix;

    private ColoredPrinter cp ;

    public GameField(int size, int wallsCount, int enemiesCount) {
        this.size = size;
        this.wallsCount = wallsCount;
        this.enemiesCount = enemiesCount;
        matrix = new GameObject[size][size];
        cp = new ColoredPrinter.Builder(1, false).foreground(FColor.BLACK)
                .background(BColor.YELLOW)
                .build();
    }

    public void print() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                if (matrix[y][x] == null)
                    cp.print("   ", Attribute.BOLD, FColor.YELLOW, BColor.YELLOW);
                else
                    cp.print(" " + matrix[y][x].getCharacter() + " ", Attribute.BOLD, FColor.BLACK, matrix[y][x].getGameObjectColor());

            }
            cp.clear();
            System.out.println();
        }
    }

    public int getSize() {
        return size;
    }

    public GameObject[][] getMatrix() {
        return matrix;
    }

    public void generateWalls(Player p) {
        Random random = new Random();

        for (int i = 0; i < wallsCount; ) {
            int x = random.nextInt(size );
            int y = random.nextInt(size );

            if (matrix[y][x] == null && p.checkPositionToPlayer(x,y)) {
                matrix[y][x] = new Wall(x, y, this);
                i++;
            }
        }
    }
}
