package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15654
public class BJ_15654_N과M5 {
    static int n,m;
    static int[] picked;
    static boolean[] isVisited;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;

    public static void dfs(int cnt){
        if(cnt == m){
            for(int pick: picked){
                sb.append(pick).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0; i<n; i++){
            if (!isVisited[i]) {
                isVisited[i] = true;
                picked[cnt] = arr[i];
                dfs(cnt + 1);
                isVisited[i] = false;
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        picked = new int[m];
        arr = new int[n];
        isVisited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        dfs(0);

        System.out.println(sb);
    }
}
