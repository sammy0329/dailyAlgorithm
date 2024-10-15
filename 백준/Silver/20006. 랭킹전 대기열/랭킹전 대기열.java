//@formatter:off
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * [문제]
 * 1. 플레이어가 입장을 신청했을 때, 매칭이 가능한 방이 없다면 새로운 방을 생성하고 입장시킴. 이때 해당 방에는 처음 입장한 플레이어의 레벨을 기준으로 -10부터 +10까지 입장 가능
 * 2. 입장 가능한 방이 있다면 입장시킨 후 방의 정원이 모두 찰때까지 대기 - 입장이 가능한 방이 여러개라면 먼저 생성된 방에 입장
 * 3. 방의 정원이 모두 차면 게임 시작
 *
 * [입력]
 * 플레이어수[1<=p<=300], 방의 정원[1<=m<=300]
 * 플레이어의 레벨[1<=l<=500], 닉네임[n]
 * 입력된 순서대로 게임 시작
 *
 * [출력]
 * 모든 생성된 방에 대해서 게임의 시작 유무와 방에 들어있는 플레이어들의 레벨,아이디 출력
 * 방은 생성된 순서대로 출력
 * 방에 있는 플레이어들의 정보는 닉네임이 사전순으로 앞서는 플레이부터 출력
 * 방이 시작되었으면 Started!, 대기중이면 Waiting! 출력
 *
 * [문제해결 프로세스]
 * 1. 플레이어수 / 방의 정원을 입력 받는다.
 * 2. 가장 첫번째 플레이어는 무조건 방을 만든다. 해당 플레이어의 레벨에서 +-10이 들어올 수 있는 방을 만든다.
 * - 만든방의 순서가 있어야함 -> 배열 or 리스트
 * - player 정보를 담고 있는 Player 객체, room 정보를 담고 있는 Room 객체 만들고, 각 방은 리스트로 관리
 */
//@formatter:on
public class Main {

    static class Player implements Comparable<Player> {

        int level;
        String nickname;

        Player(int level, String nickname) {
            this.level = level;
            this.nickname = nickname;
        }

        @Override
        public int compareTo(Player o) {
            return this.nickname.compareTo(o.nickname);
        }
    }

    static class Room {

        int standardLevel;
        int currentPlayersCnt;
        List<Player> playerList;

        Room(int standardLevel, Player player) {
            this.standardLevel = standardLevel;
            this.currentPlayersCnt = 1;
            this.playerList = new ArrayList<>();
            playerList.add(player);
        }

        void printPlayers() {
            Collections.sort(playerList);
            if (currentPlayersCnt == m) {
                System.out.println("Started!");
            } else {
                System.out.println("Waiting!");
            }
            for (Player player : playerList) {
                System.out.println(player.level + " " + player.nickname);
            }
        }

    }

    static int m;
    static int p;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        p = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        List<Room> roomList = new ArrayList<>();

        A:
        for (int i = 0; i < p; i++) {
            st = new StringTokenizer(br.readLine());

            int level = Integer.parseInt(st.nextToken());
            String nickname = st.nextToken();

            Player player = new Player(level, nickname);

            for (int j = 0; j < roomList.size(); j++) {
                if (roomList.get(j).currentPlayersCnt == m) {
                    continue;
                }
                if (roomList.get(j).standardLevel - 10 <= level
                    && level <= roomList.get(j).standardLevel + 10) {
                    roomList.get(j).currentPlayersCnt++;
                    roomList.get(j).playerList.add(player);
                    continue A;
                }
            }

            Room room = new Room(level, player);
            roomList.add(room);
        }

        for (int i = 0; i < roomList.size(); i++) {
            roomList.get(i).printPlayers();
        }
    }
}