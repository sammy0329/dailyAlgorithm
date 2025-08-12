import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> left = new Stack<>();
        Stack<Character> right = new Stack<>();

        for (char c : br.readLine().toCharArray()) {
            left.push(c);
        }
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            String cmd = br.readLine();
            if (cmd.charAt(0) == 'L' && !left.isEmpty()) {
                right.push(left.pop());
            } else if (cmd.charAt(0) == 'D' && !right.isEmpty()) {
                left.push(right.pop());
            } else if (cmd.charAt(0) == 'B' && !left.isEmpty()) {
                left.pop();
            } else if (cmd.charAt(0) == 'P') {
                left.push(cmd.charAt(2));
            }
        }

        StringBuilder sb = new StringBuilder();
        for (char c : left) {
            sb.append(c);
        }
        while (!right.isEmpty()) {
            sb.append(right.pop());
        }

        System.out.println(sb);
    }
}
