import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * 문제
 * 교실 (1,1) ~ (N,N)
 * |r1 - r2| + |c1 - c2| = 1을 만족하는 두 칸이 (r1, c1)과 (r2, c2)를 인접하다고 함 -> 4방 탐색
 * 비어있는 칸 중 좋아하는 학생이 인접한 칸에 가장 많은 칸 -> 인접한 칸 중에서 비어 있는 칸이 가장 많은 칸
 * -> 행의 번호가 가장 작은 칸 -> 열의 번호가 가장 작은 칸
 * 인접한 칸에 앉은 좋아하는 학생의 수가 0이면 학생의 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000
 * 즉, 10^(n-1)
 *
 * 입력
 * 교실 NxN
 * N^2 줄에 학생의 번호와 좋아하는 학생 4명의 번호가 자리 정할 순서대로 주어짐
 * 자기 자신을 좋아하는 경우는 X
 *
 * 출력
 * 학생의 만족도의 총 합
 *
 * 문제해결 프로세스
 * 1. 인접 행렬을 만들어 좋아하는 학생 수요 조사 진행하며, 큐에 학생 순서를 넣어준다.
 * 2. 큐를 순서대로 뽑아 이중 for문으로 놓은 지역 완전
 */
public class Main {

    static int N;
    static int[][] arr;
    static Map<Integer, Set<Integer>> friends;
    static Queue<Integer> q = new ArrayDeque<>();
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Seat implements Comparable<Seat> {

        int r;
        int c;
        int friendCnt;
        int emptyCnt;

        public Seat(int r, int c, int friendCnt, int emptyCnt) {
            this.r = r;
            this.c = c;
            this.friendCnt = friendCnt;
            this.emptyCnt = emptyCnt;
        }

        @Override
        public int compareTo(Seat seat) {
            if (friendCnt != seat.friendCnt) {
                return seat.friendCnt - friendCnt; // 인접 좋아하는 학생 수 비교
            }
            if (emptyCnt != seat.emptyCnt) {
                return seat.emptyCnt - emptyCnt; // 빈칸 수 비교
            }
            if (r != seat.r) {
                return r - seat.r; // 행 비교
            }
            return c - seat.c; // 열 비교
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1][N + 1];
        friends = new HashMap<>();

        for (int i = 1; i <= N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int no = Integer.parseInt(st.nextToken());

            // 학생 순서 저장
            q.offer(no);

            friends.put(no, new HashSet<>());

            // 좋아하는 학생 배열 채우기
            for (int j = 0; j < 4; j++) {
                friends.get(no).add(Integer.parseInt(st.nextToken()));
            }
        }

        while (!q.isEmpty()) {
            int studentNo = q.poll();

            // 초기 자리 null로 지정
            Seat seat = null;

            for (int r = 1; r < N + 1; r++) {
                for (int c = 1; c < N + 1; c++) {
                    if (arr[r][c] != 0) {
                        continue;
                    }

                    Seat curSeat = new Seat(r, c, getTotalFriends(r, c, studentNo),
                        getTotalEmpty(r, c));

                    if (seat == null) { // 자리 미정이면 해당 자리에 앉히기
                        seat = curSeat;
                        continue;
                    }

                    if (seat.compareTo(curSeat) > 0) { // compareTo를 통해 자리교체
                        seat = curSeat;
                    }
                }
            }
            arr[seat.r][seat.c] = studentNo; // 최종 자리 지정하기
        }

        System.out.println(getResult());

    }

    static int getResult() {
        int result = 0;

        for (int r = 1; r < N + 1; r++) {
            for (int c = 1; c < N + 1; c++) {
                result += Math.pow(10, getTotalFriends(r, c, arr[r][c]) - 1);
            }
        }

        return result;
    }

    static int getTotalFriends(int r, int c, int studentNo) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr <= 0 || nr > N || nc <= 0 || nc > N) {
                continue;
            }
            if (friends.get(studentNo).contains(arr[nr][nc])) {
                count++;
            }
        }
        return count;
    }

    static int getTotalEmpty(int r, int c) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr <= 0 || nr > N || nc <= 0 || nc > N) {
                continue;
            }
            if (arr[nr][nc] == 0) {
                count++;
            }
        }
        return count;
    }
}