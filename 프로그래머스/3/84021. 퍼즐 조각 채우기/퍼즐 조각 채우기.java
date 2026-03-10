import java.util.*;

class Solution {
    class Point implements Comparable<Point> {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // x좌표 오름차순, x가 같으면 y좌표 오름차순 정렬
        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) {
                return Integer.compare(this.y, o.y);
            }
            return Integer.compare(this.x, o.x);
        }
    }

    // 상하좌우 이동을 위한 배열
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        int n = game_board.length;

        boolean[][] visitedBoard = new boolean[n][n];
        boolean[][] visitedTable = new boolean[n][n];

        List<List<Point>> emptySpaces = new ArrayList<>(); // 게임 보드의 빈칸들 모음
        List<List<Point>> puzzlePieces = new ArrayList<>(); // 테이블의 퍼즐 조각들 모음

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 1. game_board에서 빈칸(0) 추출
                if (game_board[i][j] == 0 && !visitedBoard[i][j]) {
                    // 추출 직후 바로 기준점을 (0,0)으로 정규화
                    emptySpaces.add(normalize(extractBlock(game_board, visitedBoard, i, j, 0)));
                }

                // 2. table에서 퍼즐 조각(1) 추출
                if (table[i][j] == 1 && !visitedTable[i][j]) {
                    // 추출 직후 바로 기준점을 (0,0)으로 정규화
                    puzzlePieces.add(normalize(extractBlock(table, visitedTable, i, j, 1)));
                }
            }
        }

        // 3. 빈칸에 퍼즐 조각 맞춰보기
        boolean[] usedPiece = new boolean[puzzlePieces.size()]; // 퍼즐 사용 여부 체크

        for (List<Point> space : emptySpaces) {
            for (int j = 0; j < puzzlePieces.size(); j++) {
                if (usedPiece[j]) continue; // 이미 사용한 조각 패스

                List<Point> piece = puzzlePieces.get(j);
                
                // 덩어리 개수가 다르면 애초에 맞지 않음
                if (space.size() != piece.size()) continue;

                boolean isMatched = false;
                
                // 4방향(0도, 90도, 180도, 270도) 회전하며 확인
                for (int r = 0; r < 4; r++) {
                    if (isMatch(space, piece)) {
                        isMatched = true;
                        break;
                    }
                    // 맞지 않으면 90도 회전
                    piece = rotate(piece); 
                }

                // 조각이 들어맞았다면
                if (isMatched) {
                    usedPiece[j] = true; // 사용 처리
                    answer += space.size(); // 채운 칸 수 더하기
                    break; // 다음 빈칸으로 넘어감
                }
            }
        }

        return answer;
    }

    // 특정 타겟(0 또는 1)이 연결된 하나의 덩어리 좌표들을 추출하는 메서드
    public List<Point> extractBlock(int[][] grid, boolean[][] visited, int startX, int startY, int target) {
        List<Point> block = new ArrayList<>();
        Queue<Point> queue = new LinkedList<>();

        int n = grid.length;

        queue.offer(new Point(startX, startY));
        visited[startX][startY] = true;
        block.add(new Point(startX, startY));

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
                    if (!visited[nx][ny] && grid[nx][ny] == target) {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny));
                        block.add(new Point(nx, ny));
                    }
                }
            }
        }

        return block;
    }

    // 추출한 덩어리를 (0,0) 기준으로 당기고 정렬하는 정규화 메서드
    public List<Point> normalize(List<Point> block) {
        if (block.isEmpty()) return block;

        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;

        // 가장 작은 x, y 좌표 찾기
        for (Point p : block) {
            minX = Math.min(minX, p.x);
            minY = Math.min(minY, p.y);
        }

        List<Point> normalized = new ArrayList<>();
        for (Point p : block) {
            // 최소 좌표를 빼서 원점(0,0)에 가깝게 이동
            normalized.add(new Point(p.x - minX, p.y - minY));
        }

        // 비교를 위해 항상 동일한 순서가 되도록 정렬
        Collections.sort(normalized);
        return normalized;
    }

    // 덩어리를 시계방향으로 90도 회전시키는 메서드
    public List<Point> rotate(List<Point> block) {
        List<Point> rotated = new ArrayList<>();
        for (Point p : block) {
            // (x, y)를 90도 시계방향 회전하면 (y, -x)가 됨
            rotated.add(new Point(p.y, -p.x));
        }
        // 회전 후 좌표가 음수가 될 수 있으므로 다시 정규화
        return normalize(rotated); 
    }

    // [핵심 로직 3] 빈칸과 퍼즐 조각이 완전히 똑같은지 비교하는 메서드
    public boolean isMatch(List<Point> space, List<Point> piece) {
        // 이미 크기가 같은지 검사하고 들어왔지만 안전장치
        if (space.size() != piece.size()) return false;

        // 정렬된 상태이므로 순서대로 1:1 비교
        for (int i = 0; i < space.size(); i++) {
            if (space.get(i).x != piece.get(i).x || space.get(i).y != piece.get(i).y) {
                return false;
            }
        }
        return true;
    }
}