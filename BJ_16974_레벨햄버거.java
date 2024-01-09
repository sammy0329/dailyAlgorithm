package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 레벨-0 버거 : 패티만 이루어짐 P
 * 레벨-1 버거 : B P P P B
 * 레벨-2 버거 : B BPPPB P BPPPB B
 * 레벨-L 버거 : 햄버거번, 레벨-(L-1)버거, 패티, 레벨-(L-1)버거
 *
 * 입력
 * N : 레벨-N버거, X : 햄버거 아래 X장 먹음
 *
 * 출력
 * 상도가 먹은 패티 수
 *
 * 문제해결 프로세스
 * 각 레벨에서의 햄버거 총 재료 수 : 1(빵) + burgers[i-1] (이전 레벨 햄버거 재료) + 1(패티) + burgers[i-1](이전 레벨 햄버거 재료) + 1(빵)
 * 각 레벨에서의 햄버거 총 패티 수 : patty[i-1](이전 레벨 햄버거 패티) + 1(중간 패티) + patty[i-1](이전 레벨 햄버거 패티)
 *
 * 패티를 몇개 먹었는가??
 * 1. X==1 : return 0
 * 2. 1<X<중간 패티 위치 : return 재귀함수(N-1, X-1)
 * 3. X==중간 패티 위치 : return patty[N-1] + 1
 * 4. 중간 패티 수<X<전체 재료 수 : return patty[Ņ-1] + 1 + 재귀함수(N-1, X-(1+burgers[N-1]+1))
 * 5. 총 재료 수 == X : return 2*patty[N-1]+1
 */

// https://www.acmicpc.net/problem/16974
public class BJ_16974_레벨햄버거 {
    static long[] burger,patty;
    public static long recursiveCalculation(int n, long x){
        if(n==0){
            if(x==0) return 0;
            else return 1;
        }
        if(x==1) return 0; // 가장 첫번째 재료는 항상 빵임
        else if(x<burger[n-1]+2) return recursiveCalculation(n-1, x-1); // x가 중간 패티 위치보다 작으면 첫번째 번을 빼고 이전 레벨 햄버거 다시 확인
        else if(x==burger[n-1]+2) return patty[n-1]+1; // 중간 패티 위치면 이전 레벨 패티 수 + 1
        else if(x<burger[n-1]*2+3) return patty[n-1]+1+recursiveCalculation(n-1,x-(burger[n-1]+2)); // 중간 패티 위치보다 크면 중간 패티까지의 패티 수 + 이전 레벨의 햄버거 다시 확인
        else return patty[n-1]*2+1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long X = Long.parseLong(st.nextToken());

        burger = new long[N+1];
        patty = new long[N+1];

        burger[0]=patty[0]=1; // Level - 0 은 패티 한장
        for(int i=1; i<=N; i++){ // Level - 1~N 햄버거 재료, 패티 개수 셋팅
            burger[i] = burger[i-1]*2 + 3;
            patty[i] = patty[i-1]*2 + 1;
        }

        System.out.println(recursiveCalculation(N,X));

    }
}
