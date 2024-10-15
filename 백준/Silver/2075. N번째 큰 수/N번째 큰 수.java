//@formatter:off
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * maxHeap을 사용해서 n번째 뽑는 값을 출력하기
 *
 */
//@formatter:on
public class Main {

    static int n;
    static int[][] matrix;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -Integer.compare(o1, o2);
            }
        });

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                maxHeap.add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 0; i < n - 1; i++) {
            maxHeap.poll();
        }

        System.out.println(maxHeap.poll());
    }

}