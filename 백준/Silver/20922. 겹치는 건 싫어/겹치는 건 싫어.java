//@formatter:off
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer; /**
 * [문제]
 * 같은 원소가 K개 이하로 들어 있는 최장 연속 부분 수열의 길이 구하기
 * 100,000 이하의 양의 정수로 이루어진 길이가 N인 수열이 주어짐. 이 수열에서 같은 정수를 K개 이하로 포함하는 연속 부분 수열의 길이를 구하기
 *
 * [입력]
 * N, K
 * 수열
 *
 * [출력]
 * 최장 연속 부분 수열의 길이
 *
 * [문제해결 프로세스]
 * 1. N, K 입력 받고, 수열을 입력 받는다.
 * 2. 1~200,000까지 카운팅 값을 저장하는 counting 배열을 만든다.
 * 3. start, end 포인터를 0,0 셋팅해준다.
 * 4. arr[end] 값에 해당하는 count 갯수가 k개 미만이면 end++, k개를 넘어가면 수열 갯수를 answer에 최댓값 갱신 후, arr[start]에 해당하는 count-- 하고, start ++
 * - 이것을 start < N && end <N 일때까지 진행
 */
//@formatter:on
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int answer = 0;
        int startIdx = 0;
        int endIdx = 0;
        
        int[] countingArr = new int[200_001];
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        while (startIdx < n && endIdx < n && startIdx <= endIdx) {
            if (countingArr[arr[endIdx]] < k) {
                countingArr[arr[endIdx]]++;
                endIdx++;
                answer = Math.max(answer, endIdx - startIdx);
            } else {
                countingArr[arr[startIdx]]--;
                startIdx++;
            }
        }

        System.out.println(answer);
    }

}