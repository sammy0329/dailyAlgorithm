package dailyProblems;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * 입력
 * 수빈이가 이동하려는 채널 N (0 <= N <= 500,000)
 * 고장난 버튼의 개수 M (0 <= M <= 10)
 * 고장난 버튼이 주어지며, 같은 버튼이 여러 번 주어지는 경우는 없음.
 *
 * 출력
 * 채널 N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지
 *
 * 문제 해결 프로세스
 * abs(N-100) 값과 0~999,999 값을 입력하고 +- 누른 값 사이의 최솟값을 채택 단, 고장난 버튼 클릭시 continue
 */

// https://www.acmicpc.net/problem/1107
public class BJ_1107_리모컨 {
    static int N,M;
    static int[] checkList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        checkList = new int[M];

        if(M>0){
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<M;i++){
                int input = Integer.parseInt(st.nextToken());
                checkList[i]=input;
            }
        }

        int result = Math.abs(100-N); // 100에서 +- 만으로 움직일 경우

        A: for(int i=0;i<=999999;i++){
            String check = String.valueOf(i);
            for(int j=0;j<M;j++){
                String notButton = String.valueOf(checkList[j]);
                if(check.contains(notButton)) continue A;
            }
            result = Math.min(result,Math.abs(Integer.parseInt(check)-N)+check.length());
        }

        System.out.println(result);

    }
}
