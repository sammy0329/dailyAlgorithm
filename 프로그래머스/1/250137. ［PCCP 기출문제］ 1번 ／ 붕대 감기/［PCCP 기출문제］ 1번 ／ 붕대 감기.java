// 붕대감기 -> t초 동안 붕대를 감으면서 1초마다 x 만큼 체력 회복
// t초 연속으로 붕대를 감는데 성공하면 y만큼의 체력을 추가로 회복
// 기술을 쓰는 도중 몬스터에게 공격을 당함녀 기술이 취소
// 공격을 당하는 순간에는 체력을 회복할 수 없음
// 몬스터에게 공격당해 기술이 취소당하거나 기술이 끝나면 그 즉시 붕대감기를 다시 사용하며, 연속 성공시간이 0으로 초기화
// 몬스터에게 공격받으면 체력 줄어듦
// 체력 0이하가 되면 캐릭터가 죽어 체력회복 불가

class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int hpMaxTime = bandage[0]; // 시전 시간
        int hp = bandage[1]; // 초당 회복량
        int extraHp = bandage[2]; // 추가 회복량
        
        int curHp = health; // 현재 체력
        int curAttackIdx = 0; // 현재 확인해야하는 공격 인덱스
        
        int hpCurTime = 0; // 현재 hp 시간
        
        for(int t=1; t<=attacks[attacks.length-1][0]; t++){
            if(t == attacks[curAttackIdx][0]){
                hpCurTime = 0;  // 현재 hp 시간 0으로 초기화
                curHp -= attacks[curAttackIdx][1]; // 공격해서 curHp 깎기
                if(curHp<=0){ // 현재 체력이 0 이하이면 죽음 
                    return -1; 
                }
                curAttackIdx++; // 공격했으니 다음 인덱스로 넘김
            }else{
                hpCurTime++;
                
                if(curHp != health){ // 현재가 최대 체력이 아니라면
                    int plusHp = hp;
                    if(hpCurTime == hpMaxTime){
                        plusHp += extraHp;
                        hpCurTime=0;
                    }
                    curHp = Math.min(health, curHp+plusHp); // 체력 회복 진행
                }
                
            }
        }
        
        return curHp;
    }
}