import java.util.*;

class Solution {
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r; this.c = c;
        }
    }

    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, 1, -1};

    public int solution(int[][] maps) {
        return bfs(maps);
    }

    private int bfs(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;

        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        q.offer(new Point(0, 0));
        visited[0][0] = true;

        int depth = 1;

        while (!q.isEmpty()) {
            int cnt = q.size();

            for (int i = 0; i < cnt; i++) {
                Point p = q.poll();

                if (p.r == n - 1 && p.c == m - 1){
                    return depth;
                }
                
                for (int d = 0; d < 4; d++) {
                    int nr = p.r + dr[d];
                    int nc = p.c + dc[d];

                    if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                    if (maps[nr][nc] == 0 || visited[nr][nc]) continue;

                    visited[nr][nc] = true;
                    q.offer(new Point(nr, nc));
                }
            }
            depth++;
        }
        return -1;
    }
}
