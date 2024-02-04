package _230810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 맵에 집(r,c) key값으로 치킨집 사이에 각각의 거리를 저장
 * 인덱스를 조합진행
 * M일 때 가장 최소
 * 
 * @author SSAFY
 *
 */

class Point{ // row, col 저장할 클래스 
	int r;
	int c;

	public Point() { }
	public Point(int r,int c) {
		this.r=r;
		this.c=c;
	}
}

public class Main_BJ_15686_치킨배달_안성재 {
	public static int N,M,picked[];
	public static int result=Integer.MAX_VALUE;
	public static List<Point> house = new ArrayList<Point>();
	public static List<Point> chickens = new ArrayList<Point>();

	// 치킨집 임의 번호 - key, 치킨집에서 집까지 거리 - value
	public static HashMap<Point, ArrayList<Integer>> map = new HashMap<>();

	public static void combi(int start,int cnt) {
		if(cnt==M) {
			int checkResult=0; // 조합으로 pick된 치킨집과 집 사이의 모든 최소거리 저장할 변수 
			
			for(Point key:map.keySet()) {
				int checkMin=Integer.MAX_VALUE; // 선택된 치킨집 중 가까운 치킨집 찾아 거리 저장할 변수 
				for(int pickIdx:picked) { // 치킨집과의 최소거리 찾기 진행
					checkMin=Math.min(checkMin, map.get(key).get(pickIdx));
				}
				checkResult+=checkMin; 
				if(checkResult>result) return; // result 보다 커지면 return 
			}
			
			result=Math.min(result, checkResult);
			return;
		}
		
		for(int i=start;i<chickens.size();i++) { // 치킨집 인덱스만큼 조합 진행 
			picked[cnt]=i;
			combi(i+1,cnt+1);
		}
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st=new StringTokenizer(br.readLine());

		N =Integer.parseInt(st.nextToken()); // N x N
		M =Integer.parseInt(st.nextToken()); // 최대 치킨집 개수 
		picked = new int[M]; // 조합에서 선택된 값 넣기 
		
		for(int r=0;r<N;r++) { // 값 입력받기
			st=new StringTokenizer(br.readLine());
			for(int c=0;c<N;c++) {
				int check=Integer.parseInt(st.nextToken());

				if(check==0) continue; // 0이면 continue

				else if(check==1) { // 1이면 house 리스트에 Point 객체 추가 후, 해당 포인트를 key 값으로 map 추가  
					Point p = new Point(r,c);
					house.add(p);
					map.put(p, new ArrayList<Integer>());
				}

				else if(check==2) { // 2이면 chickens리스트에 Point 객체 추가 
					chickens.add(new Point(r,c));
				}
			}
		}
		
		// 거리 차이 넣어주기
		for(Point ph: house) {
			for(Point pc:chickens) {
				map.get(ph).add(Math.abs(ph.r-pc.r)+Math.abs(ph.c-pc.c));
			}
		}
		
		combi(0,0);
		System.out.println(result);

	}
}
