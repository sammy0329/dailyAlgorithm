package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 입력
 * N (10 ≤ N < 100,000)수열과 S (0 < S ≤ 100,000,000) 합
 *
 * 출력
 * 최소 길이 (합을 만들 수 없다면 0 출력)
 *
 * 문제해결 프로세스
 * 투포인터를 이용하여 sum이 s보다 작으면 sum+=arr[end++], 크거나 같으면 sum-=arr[start++]
 */

// https://www.acmicpc.net/problem/1806
public class BJ_1806_부분합 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int length = Integer.MAX_VALUE;

        while(start<=end && end<=n){
            if(sum<s) sum+=arr[end++];
            else{
                length = Math.min(length,end-start);
                sum-=arr[start++];
            }
        }
        if(length==Integer.MAX_VALUE) System.out.println(0);
        else System.out.println(length);
    }
}
