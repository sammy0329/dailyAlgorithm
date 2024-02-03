package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15657
public class BJ_15655_N과M8 {

    static int n, m;
    static int[] picked;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;

    public static void dfs(int start, int cnt) {
        if (cnt == m) {
            for (int pick : picked) {
                sb.append(pick).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i < n; i++) {

            picked[cnt] = arr[i];
            dfs(i,cnt + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        picked = new int[m];
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0,0);

        System.out.println(sb);
    }
}
