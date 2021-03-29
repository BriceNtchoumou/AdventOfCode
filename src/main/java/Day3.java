import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 {
    public static void main(String[] args) throws IOException {
        final List<String> lines = Files.lines(Path.of("C:\\inputDay3.txt")).collect(Collectors.toUnmodifiableList());

        boolean[][] grid = new boolean[lines.size()][];
        for (int r = 0; r < lines.size(); r++) {
            grid[r] = new boolean[lines.get(r).length()];
            for (int c = 0; c < lines.get(r).length(); c++) {
                grid[r][c] = lines.get(r).charAt(c) == '#';
            }
        }
        System.out.println("Part One: " + getTreeCount(grid, 3, 1));
       System.out.println("Part Two: " + getTreeCount(grid, 1, 1) * getTreeCount(grid, 3, 1) * getTreeCount(grid, 5, 1) * getTreeCount(grid, 7, 1) * getTreeCount(grid, 1, 2));
    }

    private static long getTreeCount(boolean[][] grid, int x, int y) {
        long trees = 0;
        for (int r = 0, c = 0; r < grid.length; r += y, c += x) {
            trees += grid[r][c % grid[r].length] ? 1 : 0;
        }
        return trees;
    }

}
