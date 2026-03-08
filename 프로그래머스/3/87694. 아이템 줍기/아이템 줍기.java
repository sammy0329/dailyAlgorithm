import java.util.*;

class Solution {
    static class Point{
        int x;
        int y;
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] map = new int[101][101];

        // 1. 맵 그리기
        for (int[] rect : rectangle) {
            int x1 = rect[0] * 2, y1 = rect[1] * 2;
            int x2 = rect[2] * 2, y2 = rect[3] * 2;
            for (int i = x1; i <= x2; i++) {
                for (int j = y1; j <= y2; j++) {
                    if (map[i][j] == -1) continue;
                    if (i == x1 || i == x2 || j == y1 || j == y2) map[i][j] = 1;
                    else map[i][j] = -1;
                }
            }
        }

        // 2. BFS 진행
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[101][101];
        int depth = 0;

        q.offer(new Point(characterX * 2, characterY * 2));
        visited[characterX * 2][characterY * 2] = true;

        int[] dx = {1, -1, 0, 0};
        int[] dy = {0, 0, 1, -1};

        while (!q.isEmpty()) {
            int qSize = q.size();

            for (int i = 0; i < qSize; i++) {
                Point current = q.poll();

                if (current.x == itemX * 2 && current.y == itemY * 2) {
                    return depth / 2;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = current.x + dx[d];
                    int ny = current.y + dy[d];

                    if (nx >= 0 && nx <= 100 && ny >= 0 && ny <= 100) {
                        if (map[nx][ny] == 1 && !visited[nx][ny]) {
                            visited[nx][ny] = true;
                            q.offer(new Point(nx, ny));
                        }
                    }
                }
            }
            depth++;
        }
        return 0;
    }
}