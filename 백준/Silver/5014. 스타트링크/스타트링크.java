import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int F = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int G = Integer.parseInt(st.nextToken());
        int U = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());

        int[] counts = new int[F + 1];

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(S);

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == G) {
                System.out.println(counts[current]);
                return;
            }

            if (U>0 && current + U <= F && counts[current + U] == 0) {
                counts[current + U] = counts[current] + 1;
                q.offer(current + U);
            }

            if (D>0 && current - D > 0 && counts[current - D] == 0) {
                counts[current - D] = counts[current] + 1;
                q.offer(current - D);
            }
        }

        if (counts[G] == 0) {
            System.out.println("use the stairs");
        }
    }
}
