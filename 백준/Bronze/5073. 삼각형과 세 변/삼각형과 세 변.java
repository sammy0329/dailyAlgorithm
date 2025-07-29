import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[3];
            arr[0] = Integer.parseInt(st.nextToken());
            arr[1] = Integer.parseInt(st.nextToken());
            arr[2] = Integer.parseInt(st.nextToken());

            // 종료 조건
            if (arr[0] == 0 && arr[1] == 0 && arr[2] == 0) {
                break;
            }

            Arrays.sort(arr);

            // 삼각형이 안 되는 경우
            if (arr[2] >= arr[0] + arr[1]) {
                System.out.println("Invalid");
            }
            // 정삼각형
            else if (arr[0] == arr[1] && arr[1] == arr[2]) {
                System.out.println("Equilateral");
            }
            // 이등변삼각형
            else if (arr[0] == arr[1] || arr[1] == arr[2] || arr[0] == arr[2]) {
                System.out.println("Isosceles");
            }
            // 그 외: 부등변삼각형
            else {
                System.out.println("Scalene");
            }
        }
    }
}
