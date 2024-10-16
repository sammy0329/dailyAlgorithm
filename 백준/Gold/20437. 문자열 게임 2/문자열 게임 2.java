import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

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
                if (indexList[i].size() < k) continue; // 알파벳이 k번 이상 등장하지 않으면 넘어감

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