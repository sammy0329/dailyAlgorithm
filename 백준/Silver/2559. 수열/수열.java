import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 처음 윈도우 k개의 합 구하기
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += arr[i];
        }
        int max = sum;

        // 슬라이딩 윈도우 적용하여 최대값 갱신
        for (int i = k; i < n; i++) {
            sum = sum - arr[i - k] + arr[i];
            if (sum > max) {
                max = sum;
            }
        }

        System.out.println(max);  // 결과 출력
    }
}
