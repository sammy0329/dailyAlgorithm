import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제
 * K개의 박스를 그릴 때, 나눠지는 영역 갯수, 영역의 넓이를 오름차순으로 출력하기
 *
 * 입력
 * M,N,K : 행,열,직사각형 갯수
 * K개의 줄에는 한 줄에 하나씩 직사각형의 왼쪽 아래 꼭짓점의 x, y좌표값과 오른쪽 위 꼭짓점의 x, y좌표값
 * 모눈종이의 왼쪽 아래 꼭짓점의 좌표는 (0,0)이고, 오른쪽 위 꼭짓점의 좌표는(N,M)
 */

public class Main {

    static int M,N,K,cnt;
    static int[][] arr;
    static List<Integer> result = new ArrayList<>();
    static int[] dr = {1, 0, -1, 0};
    static int[] dc = {0, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[M][N];

        for(int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            for(int y=y1; y<y2; y++){
                for(int x=x1; x<x2; x++){
                    arr[y][x] = 1;
                }
            }
        }

        for(int i=0; i<M; i++){
            for(int j=0; j<N; j++){
                if(arr[i][j]==1) continue;
                cnt=1;
                dfs(i,j);
                result.add(cnt);
            }
        }
        Collections.sort(result);
        sb.append(result.size()).append("\n");
        for(Integer e: result){
            sb.append(e).append(" ");
        }
        System.out.println(sb);
    }

    static void dfs(int r, int c) {
        arr[r][c] = 1;

        for (int d = 0; d < 4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if (nr >= M || nr < 0 || nc >= N || nc < 0 || arr[nr][nc]==1) continue;
            cnt++;
            dfs(nr, nc);
        }
    }
}