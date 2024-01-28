package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2609_최대공약수와최소공배수 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int max = Math.max(n, m);
        int min = Math.min(n, m);
        int a = max;
        int b = min;

        // 최대 공약수
        while(a % b != 0) {
            int c = a;
            a = b;
            b = c % b;
        }
        System.out.println(b);

        //최소공배수
        int i = max;
        while(max % min != 0) {
            max += i;
        }
        System.out.println(max);
    }
}
