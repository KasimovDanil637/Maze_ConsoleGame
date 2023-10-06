package maze;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Maze {
    private int[][] maze;
    private int[][] maze1 = {
            {1, 1, 1, 0, 1, 1, 1},
            {1, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 1, 1, 1},
            {1, 0, 0, 1, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1}
    };

    private int[][] maze2 = {
            {1, 1, 1, 1, 1, 1, 0},
            {1, 1, 1, 1, 0, 0, 0},
            {1, 1, 1, 1, 0, 1, 1},
            {1, 1, 0, 0, 0, 1, 1},
            {1, 1, 0, 0, 1, 1, 1},
            {1, 1, 0, 0, 1, 1, 1},
            {1, 1, 0, 1, 1, 1, 1}
    };

    private int[][] maze3 = {
            {0, 1, 1, 1, 1, 1, 1},
            {0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 1}
    };
    private int[][] maze4 = {
            {1, 0, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 0, 1},
            {1, 1, 1, 1, 1, 0, 1}
    };
    private int[][] maze5 = {
            {1, 1, 0, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 1, 0, 1, 1},
            {1, 0, 0, 0, 0, 1, 1},
            {1, 1, 0, 1, 1, 1, 1}
    };

    public void rangomMaze(int r) {
        switch (r) {
            case 1:
                maze = maze1;
                break;
            case 2:
                maze = maze2;
                break;
            case 3:
                maze = maze3;
                break;
            case 4:
                maze = maze4;
                break;
            case 5:
                maze = maze5;
                break;
        }

    }

    int []cursor = new int[2];
    int[] startPoint = new int[2];

    public int[] getStartPoint() {
        int[] array = new int[2];
        array[0] = 1;
        for (int j = 0; j < maze[0].length; j++) {
            if (maze[0][j] == 0) {
                array[1] = j + 1;
            }
        }
        cursor[0] = 0;
        cursor[1] = array[1]-1;
        startPoint[0] = 0;
        startPoint[1] = array[1] - 1;
        return array;
    }

    private int[] getEndPointForLength() {
        int[] array = new int[2];
        array[0] = 6;
        for (int j = 0; j < maze[6].length; j++) {
            if (maze[6][j] == 0) {
                array[1] = j ;
            }
        }
        return array;
    }
    public  int getMin() {
        int[] arr1 = startPoint;
        int[] arr2 = getEndPointForLength();

        int n = maze.length;
        int m = maze[0].length;

        Point start = new Point(arr1[0],arr1[1]);
        Point end = new Point(arr2[0],arr2[1]);

        Queue<Point> queue = new LinkedList<>();
        queue.add(start);

        int[][] distances = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE); //устанваливаем каждому элементу бесконечность
        }
        distances[start.x][start.y] = 0;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int x = current.x;
            int y = current.y;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && maze[nx][ny] == 0 && distances[nx][ny] == Integer.MAX_VALUE) {
                    queue.add(new Point(nx, ny));
                    distances[nx][ny] = distances[x][y] + 1;
                }
            }
        }

        return distances[end.x][end.y];
    }


    public int getPoint(String direction) {
        int [] newCursor = cursor;
        int i;
        int[] endArray = getEndPointForLength();
        if (cursor[0] == endArray[0] && cursor[1] == endArray[1]){
            return 2;
        }
        switch (direction) {
            case "u":
                if (newCursor[0] == 0) {
                    System.out.println("You cannot move in that direction.");
                } else {
                    i = maze[newCursor[0] - 1][newCursor[1]];
                    if (i == 0){
                        cursor[0] = newCursor[0] - 1;
                    }
                    return i;
                }
            case "d":
                if (newCursor[0] == 6) {
                    System.out.println("You cannot move in that direction.");
                } else {
                    i = maze[newCursor[0] + 1][newCursor[1]];
                    if (i == 0){
                        cursor[0] = newCursor[0] + 1;
                    }
                    return i;
                }
            case "l":
                if (newCursor[1] == 0) {
                    System.out.println("You cannot move in that direction.");
                } else {
                    i = maze[newCursor[0]][newCursor[1] - 1];
                    if (i == 0){
                        cursor[1] = newCursor[1] - 1;
                    }
                    return i;
                }
            case "r":
                if (newCursor[1] == 6) {
                    System.out.println("You cannot move in that direction.");
                } else {
                    i = maze[newCursor[0]][newCursor[1] + 1];
                    if (i == 0){
                        cursor[1] = newCursor[1] + 1;
                    }
                    return i;
                }
            default:
                System.out.println("Invalid direction. Please enter u, d, r or l");

        }
        return -1;
    }

}
