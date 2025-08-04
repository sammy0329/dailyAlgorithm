import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int countStrike(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    static int countBall(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i != j && s1.charAt(i) == s2.charAt(j)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        List<String> candidates = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                if (j == i) {
                    continue;
                }
                for (int k = 1; k <= 9; k++) {
                    if (k == i || k == j) {
                        continue;
                    }
                    candidates.add("" + i + j + k);
                }
            }
        }

        for (int tc = 0; tc < n; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String guess = st.nextToken();
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            // 뒤에서부터 검사하여 조건에 맞지 않는 후보 제거
            for (int i = candidates.size() - 1; i >= 0; i--) {
                String candidate = candidates.get(i);
                int s = countStrike(candidate, guess);
                int b = countBall(candidate, guess);
                if (s != strike || b != ball) {
                    candidates.remove(i);
                }
            }
        }
        System.out.println(candidates.size());
    }
}
