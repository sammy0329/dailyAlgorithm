/**
* 1x3 -> 새로운 조합(1) -> 1
* 2x3 -> 새로운 조합(2) + 기존 조합 [1x3|1x3: 1*1=1] -> 3
* 3x3 -> 새로운 조합(5) + 기존 조합 [1x3|2x3: 1*3=3, 2x3|1x3: 1*1=1] -> 10
* 4x3 -> 새로운 조합(2) + 기존 조합 [1x3|3x3: 1*10=10, 2x3|2x3: 2*3=6, 3x3|1x3: 5*1=5] -> 23
* 5x3 -> 새로운 조합(2) + 기존 조합 [1x3|4x3: 1*23=23, 2x3|3x3: 2*10=20, 3x3|2x3: 5*3=15, 4x3|1x3: 2*1=2] -> 62
* 6x3 -> 새로운 조합(4) + 기존 조합 [1x3|5x3: 1*62=62, 2x3|4x3: 2*23=46, 3x3|3x3: 5*10=50, 4x3|2x3: 2*3=6, 5x3|1x3: 2*1=2] -> 170
* 7,8,9 -> 4,5,6과 동일하게 새로운 조합 늘어남
* DP(x) = DP(x-1) + 2*DP(x-2) + 5*DP(x-3) + 2*DP(x-4) + 2*DP(x-5) + 4*DP(x-6)
              + 2*DP(x-7) + 2*DP(x-8) + 4*DP(x-9)...
* -> DP(x) = DP(x-1) + 2*DP(x-2) + 6*DP(x-3) + DP(x-4) - DP(x-6)
**/
class Solution {
    public int solution(int n) {
        final int MOD = 1_000_000_007;
        long[] dp = new long[n+7];
        
        dp[1] = 1;
        dp[2] = 3;
        dp[3] = 10;
        dp[4] = 23;
        dp[5] = 62;
        dp[6] = 170;
        
        if(n < 7){
            return (int)dp[n];
        }
        
        for(int i = 7; i <= n; i++) {
            // 각 항마다 모듈러 연산을 적용하여 오버플로우 방지
            long term1 = dp[i-1];
            long term2 = (2 * dp[i-2]) % MOD;
            long term3 = (6 * dp[i-3]) % MOD;
            long term4 = dp[i-4];
            long term5 = dp[i-6];
            
            // 최종 계산 시 음수 처리를 위해 MOD를 더함
            dp[i] = ((((term1 + term2) % MOD + term3) % MOD + term4) % MOD - term5 + MOD) % MOD;
        }
        
        return (int)dp[n];
    }
}