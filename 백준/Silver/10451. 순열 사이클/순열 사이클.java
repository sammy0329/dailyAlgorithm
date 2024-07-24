import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] arr;
    static boolean[] isVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int t = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < t; tc++) {
            int n = Integer.parseInt(br.readLine());
            int cycleCnt = 0;
            arr = new int[n + 1];
            isVisited = new boolean[n + 1];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < n + 1; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            for (int i = 1; i < n + 1; i++) {
                if (!isVisited[i]) {
                    dfs(i);
                    cycleCnt++;
                }
            }
            System.out.println(cycleCnt);
        }
    }

    private static void dfs(int idx) {
        isVisited[idx] = true;

        int nextIdx = arr[idx];
        if (!isVisited[nextIdx]) {
            dfs(arr[idx]);
        }

    }
}