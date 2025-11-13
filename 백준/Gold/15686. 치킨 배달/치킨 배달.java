import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static List<Node> houses = new ArrayList<>();
    static List<Node> chickenHouses = new ArrayList<>();
    static Node[] selectedChicken;
    static int minCityDistance = Integer.MAX_VALUE;

    static class Node {

        int r, c;

        Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selectedChicken = new Node[M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 1) {
                    houses.add(new Node(i, j));
                } else if (val == 2) {
                    chickenHouses.add(new Node(i, j));
                }
            }
        }

        dfs(0, 0);

        System.out.println(minCityDistance);
    }

    static void dfs(int start, int count) {
        if (count == M) {
            int currentCityDist = calculateCityDistance();
            minCityDistance = Math.min(minCityDistance, currentCityDist);
            return;
        }

        for (int i = start; i < chickenHouses.size(); i++) {
            selectedChicken[count] = chickenHouses.get(i);
            dfs(i + 1, count + 1);
        }
    }

    static int calculateCityDistance() {
        int totalCityDistance = 0;

        for (Node house : houses) {
            int houseToChickenDistance = Integer.MAX_VALUE;
            
            for (Node chicken : selectedChicken) {
                int distance = Math.abs(house.r - chicken.r) + Math.abs(house.c - chicken.c);
                houseToChickenDistance = Math.min(houseToChickenDistance, distance);
            }
            
            totalCityDistance += houseToChickenDistance;
        }
        return totalCityDistance;
    }
}
