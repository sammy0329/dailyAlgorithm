import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//@formatter:off
/**
 * [문제]
 * 1개의 회의실을 N개의 회의에 대해 회의실 사용 표를 만들려 한다. 이때, 각 회의가 겹치지 않게 회의의 최대 개수 찾기
 * 회의의 시작 시간과 끝나는 시간이 같을 수도 있음
 *
 * [입력]
 * 회의의 수 N (1<=N<=100,000)
 * 회의 시작 시간, 회의 끝나는 시간
 *
 * [출력]
 * 사용할 수 있는 최대 회의 개수
 *
 * [문제해결 프로세스]
 * 시작 시간과 끝나는 시간을 각각 2차원 배열에 넣는다.
 * 끝나는 시간 기준 오름차순 정렬 후, 만약 같다면 시작하는 시간 기준 오름차순 정렬한다.
 * 하나씩 배열을 조회하면 이전에 endTime에 안겹치는 startTime만 카운팅
 */
//@formatter:on
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int N = Integer.parseInt(br.readLine());
        int[][] schedules = new int[N][2];
        int currentEndTime = 0;
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int startTime = Integer.parseInt(st.nextToken());
            int endTime = Integer.parseInt(st.nextToken());
            schedules[i][0] = startTime;
            schedules[i][1] = endTime;
        }

        Arrays.sort(schedules, (o1, o2) -> {
            return o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0];
        });

        for (int i = 0; i < N; i++) {
            int startTime = schedules[i][0];
            int endTime = schedules[i][1];

            if (startTime >= currentEndTime) {
                cnt++;
                currentEndTime = endTime;
            }
        }

        System.out.println(cnt);
    }

}