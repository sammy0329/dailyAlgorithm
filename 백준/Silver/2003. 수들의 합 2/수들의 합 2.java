import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<n;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = 0;
        int sum = 0;
        int cnt = 0;

        while (start<=end && end<=n){
            if(sum<m) sum += arr[end++];
            else if(sum>m) sum -= arr[start++];
            else{
                cnt++;
                sum -= arr[start++];
            }
        }

        System.out.println(cnt);

    }
}