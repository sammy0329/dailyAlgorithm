package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BJ_14225_부분수열의합 {
    static int N;
    static int[] S;
    static Set<Integer> set = new HashSet<>();
    static int result = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        S = new int[N];

        for(int i=0; i<N; i++){
            S[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0,0);

        while (true){
            if(set.contains(result)) result++;
            else break;
        }

        System.out.println(result);

    }

    static void dfs(int idx,int sum){
        if(idx == N){
            if(sum>0)
                set.add(sum);
        }
        else{
            dfs(idx+1,sum+S[idx]);
            dfs(idx+1,sum);
        }
    }
}
