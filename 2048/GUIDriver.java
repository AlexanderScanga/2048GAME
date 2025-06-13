package game2048;

import java.util.Random;

public class GUIDriver {
	private int score = 0;
    public static final int SIZE = 4;
    private Driver[][] tiles;
    private Random random;

    public GUIDriver() {
        tiles = new Driver[SIZE][SIZE];
        random = new Random();
        init();
    }

    public void init() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                tiles[i][j] = new Driver(0);
            }
        }
        addRandomDriver();
        addRandomDriver();
    }

    public Driver getDriver(int row, int col) {
        return tiles[row][col];
    }

    public boolean move(int direction) {
        boolean moved = false;
        for (int i = 0; i < SIZE; i++) {
        	Driver[] line = getLine(i, direction);
        	Driver[] merged = mergeLine(moveLine(line));
            if (!compareLines(line, merged)) {
                setLine(i, merged, direction);
                moved = true;
            }
        }
        if (moved) {
            addRandomDriver();
        }
        return moved;
    }

    private Driver[] getLine(int index, int direction) {
        Driver[] result = new Driver[SIZE];
        for (int i = 0; i < SIZE; i++) {
            if (direction == 0) {
                result[i] = new Driver(tiles[i][index].getValue());
            } else if (direction == 1) {
                result[i] = new Driver(tiles[index][SIZE - 1 - i].getValue());
            } else if (direction == 2) {
                result[i] = new Driver(tiles[SIZE - 1 - i][index].getValue());
            } else if (direction == 3) {
                result[i] = new Driver(tiles[index][i].getValue());
            }
        }
        return result;
    }

    private void setLine(int index, Driver[] line, int direction) {
        for (int i = 0; i < SIZE; i++) {
            if (direction == 0) {
                tiles[i][index] = line[i];
            } else if (direction == 1) {
                tiles[index][SIZE - 1 - i] = line[i];
            } else if (direction == 2) {
                tiles[SIZE - 1 - i][index] = line[i];
            } else if (direction == 3) {
                tiles[index][i] = line[i];
            }
        }
    }

    private Driver[] moveLine(Driver[] oldLine) {
    	Driver[] newLine = new Driver[SIZE];
        int index = 0;
        for (int i = 0; i < SIZE; i++) {
            if (!oldLine[i].isEmpty()) {
                newLine[index] = new Driver(oldLine[i].getValue());
                index++;
            }
        }
        while (index < SIZE) {
            newLine[index] = new Driver(0);
            index++;
        }
        return newLine;
    }



    private boolean compareLines(Driver[] line1, Driver[] line2) {
        for (int i = 0; i < SIZE; i++) {
            if (line1[i].getValue() != line2[i].getValue()) {
                return false;
            }
        }
        return true;
    }

    public void addRandomDriver() {
        int count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tiles[i][j].isEmpty()) count++;
            }
        }
        if (count == 0) return;

        int randomPos = random.nextInt(count);
        count = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tiles[i][j].isEmpty()) {
                    if (count == randomPos) {
                        tiles[i][j].setValue(random.nextDouble() < 0.9 ? 2 : 4);
                        return;
                    }
                    count++;
                }
            }
        }
    }

    public boolean isGameOver() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (tiles[i][j].isEmpty()) return false;
                if (i < SIZE - 1 && tiles[i][j].getValue() == tiles[i + 1][j].getValue()) return false;
                if (j < SIZE - 1 && tiles[i][j].getValue() == tiles[i][j + 1].getValue()) return false;
            }
        }
        return true;
    }

private Driver[] mergeLine(Driver[] oldLine) {
    for (int i = 0; i < SIZE - 1; i++) {
        if (oldLine[i].getValue() != 0 && oldLine[i].getValue() == oldLine[i + 1].getValue()) {
            oldLine[i].setValue(oldLine[i].getValue() * 2);
            score += oldLine[i].getValue();  
            oldLine[i + 1].setValue(0);
            i++;
        }
    }
    return moveLine(oldLine);
}

public int getScore() {
    return score;
}
}
