import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int sum = 0;
        int cnt = 0;
        int start = 0;
        int end = 0;

        while (end <= n) {
            if (sum == n) {
                cnt++;
                end++;
                sum += end;
            } else if (sum < n) {
                end++;
                sum += end;
            } else if (sum > n) {
                sum -= start;
                start++;
            }
        }

        System.out.println(cnt);
    }
}

