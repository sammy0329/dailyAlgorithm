import java.util.*;

class Solution {
    
    // Point 객체
    static class Point {
        int r;
        int c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    
    public int solution(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        boolean[][] isVisited = new boolean[n][m];
        
        // 방향 배열 (상, 하, 좌, 우)
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(0, 0));
        isVisited[0][0] = true;
        
        int answer = 1; // 시작 지점도 포함되므로 거리 1로 초기화
        
        while (!q.isEmpty()) {
            int qSize = q.size();
            for (int i = 0; i < qSize; i++) {
                Point current = q.poll();
                
                // 목표 지점에 도달하면 반환
                if (current.r == n - 1 && current.c == m - 1) {
                    return answer;
                }
                
                // 4방향 탐색
                for (int d = 0; d < 4; d++) {
                    int nr = current.r + dr[d];
                    int nc = current.c + dc[d];
                    
                    // 범위 밖이거나 벽이거나 이미 방문한 경우 스킵
                    if (nr < 0 || nr >= n || nc < 0 || nc >= m || maps[nr][nc] == 0 || isVisited[nr][nc]) {
                        continue;
                    }
                    
                    // 큐에 넣을 때 방문 처리
                    q.offer(new Point(nr, nc));
                    isVisited[nr][nc] = true;
                }
            }
            answer++;
        }
        
        return -1; // 목표 지점에 도달할 수 없는 경우
    }
}
