package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/15651
public class BJ_15651_N과M3 {
    static int n,m;
    static int[] picked;
    static StringBuilder sb = new StringBuilder();
    public static void dfs(int cnt){
        if(cnt == m){
            for(int num: picked){
                sb.append(num).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<=n; i++){
            picked[cnt]=i;
            dfs(cnt+1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        picked = new int[m];

        dfs(0);
        System.out.println(sb);
    }
}
