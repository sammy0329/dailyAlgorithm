import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        ArrayList<String> channels = new ArrayList<>();
        int kbs1Idx = -1, kbs2Idx = -1;

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            channels.add(s);
            if (s.equals("KBS1")) {
                kbs1Idx = i;
            }
            if (s.equals("KBS2")) {
                kbs2Idx = i;
            }
        }

        StringBuilder sb = new StringBuilder();

        if (kbs1Idx < kbs2Idx) {
            for (int i = 0; i < kbs1Idx; i++) {
                sb.append("1");
            }
            for (int i = 0; i < kbs1Idx; i++) {
                sb.append("4");
            }
            for (int i = 0; i < kbs2Idx; i++) {
                sb.append("1");
            }
            for (int i = 0; i < kbs2Idx - 1; i++) {
                sb.append("4");
            }
        } else if (kbs1Idx > kbs2Idx) {
            for (int i = 0; i < kbs1Idx; i++) {
                sb.append("1");
            }
            for (int i = 0; i < kbs1Idx; i++) {
                sb.append("4");
            }
            for (int i = 0; i < kbs2Idx + 1; i++) {
                sb.append("1");
            }
            for (int i = 0; i < kbs2Idx; i++) {
                sb.append("4");
            }
        }

        System.out.println(sb);
    }
}
