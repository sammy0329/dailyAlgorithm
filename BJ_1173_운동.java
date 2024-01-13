package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 이해
 * 욷옹하는 과정이 1분 단위로 나눠져 있음.
 * 매 분마다 운동과 휴식 중 하나를 선택해야 함.
 * 운동 선택 후, 영식이의 맥박이 T만큼 증가
 * 영식이의 맥박이 X였다면, 1분 동안 운동을 한 후 맥박이 X+T가 됨.
 * 영식이는 맥박이 M을 넘는 것을 원치 않으므로 X+T가 M보다 작거나 같을 때만 운동을 할 수 있음.
 * 휴식을 선택하면 맥박이 R만큼 감소
 * 맥박은 m보다 낮아지면 안됨. X-R이 ,m보다 작으면 맥박은 m이 됨.
 *
 * 입력
 * N,m,M,T,R
 *
 * 출력
 * 운동을 N분하는데 필요한 시간의 최솟 값을 출ㅇ력 만약 운동을 n분 할 수 없다면 -1
 *
 * 문제해결 프로세스
 * 5분, 최소 맥박수 70, 최대 맥박수 120, 운동시 증가 맥박수 25, 휴식하면 떨어질 맥박수 15
 * 시작 맥박 : 70
 * 1분 운동 : 95
 * 1분 운동 : 120
 * 휴식 : 105
 * 휴식 : 90
 * 1분 운동 : 115
 * 휴식 : 100
 * 휴식 : 85
 * 1분 운동 : 110
 * 휴식 : 95
 * 1분 운동 : 120
 * 총 10분 소요
 */

// https://www.acmicpc.net/problem/1173
public class BJ_1173_운동 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int myMacBac = m;
        int time=0;
        int cnt=0;

        if(T > M-m) System.out.println(-1);
        else{
            while(cnt!=N){
                time++;
                if(myMacBac+T>M) {
                    myMacBac-=R;
                    if(myMacBac<m) myMacBac = m;
                }else {
                    myMacBac+=T;
                    cnt++;
                }
            }
            System.out.println(time);
        }
    }
}
