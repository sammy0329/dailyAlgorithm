//@formatter:off

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * https://www.acmicpc.net/problem/20437
 *
 * [문제]
 * 1. 문자를 정확히 K개 포함하는 가장 짧은 연속 문자열의 길이 구하기
 * 2. 어떤 문자를 정확히 K개를 포함하고, 문자열의 첫번째와 마지막 글자가 해당 문자로 같은 가장 긴 연속 문자열의 길이를 구하기
 *
 * [입력]
 * 테스트 케이수 수 T
 * 문자열 W, 정수 K
 *
 * [출력]
 * 1,2번 조건을 공백을 두고 출력
 * 만약, 만족하는 연속 문자열이 없다면, -1 출력
 *
 * [문제해결 프로세스]
 * 1. T, W, K 입력을 받는다.
 * 2. 1번 정답(가장 짧은 문자열 길이)과 2번 정답(가장 긴 문자열 길이)을 담을 변수를 만든다.
 * 3. 각 알파벳(a-z)의 등장 위치를 저장할 리스트 배열을 만든다.
 * - 입력받은 문자열을 순차적으로 순회하며 각 알파벳이 나온 위치를 리스트에 저장한다.
 * 4. 각 알파벳이 등장한 위치 리스트를 순회하며, 해당 알파벳이 정확히 K번 포함된 연속 부분 문자열의 길이를 계산한다.
 * - 리스트에서 K개의 연속된 위치를 찾고, 그 구간의 길이를 계산하여 shortAnswer와 longAnswer를 갱신한다.
 * 5. 각 테스트 케이스의 결과를 StringBuilder에 저장하고, 마지막에 한 번에 출력한다.
 */
//@formatter:on
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {

            String str = br.readLine();
            int k = Integer.parseInt(br.readLine());

            // 1번 조건에서, k가 1인 경우 바로 답을 출력
            if (k == 1) {
                sb.append(1).append(" ").append(1).append("\n");
                continue;
            }

            // 각 알파벳 위치를 저장할 리스트 배열
            ArrayList<Integer>[] indexList = new ArrayList[26];
            for (int i = 0; i < 26; i++) {
                indexList[i] = new ArrayList<>();
            }

            // 문자열을 돌면서 각 알파벳의 등장 위치를 기록
            for (int i = 0; i < str.length(); i++) {
                indexList[str.charAt(i) - 'a'].add(i);
            }

            int shortAnswer = Integer.MAX_VALUE;
            int longAnswer = -1;

            // 각 알파벳에 대해 처리
            for (int i = 0; i < 26; i++) {
                if (indexList[i].size() < k) {
                    continue; // 알파벳이 k번 이상 등장하지 않으면 넘어감
                }

                // 각 알파벳이 등장한 위치 목록을 순회하며 서브 문자열 길이를 계산
                for (int j = 0; j <= indexList[i].size() - k; j++) {
                    int length = indexList[i].get(j + k - 1) - indexList[i].get(j) + 1;
                    shortAnswer = Math.min(shortAnswer, length);
                    longAnswer = Math.max(longAnswer, length);
                }
            }

            if (shortAnswer == Integer.MAX_VALUE || longAnswer == -1) {
                sb.append(-1).append("\n");
            } else {
                sb.append(shortAnswer).append(" ").append(longAnswer).append("\n");
            }
        }

        System.out.println(sb);
    }
}