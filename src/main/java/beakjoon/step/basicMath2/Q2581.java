package beakjoon.step.basicMath2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q2581 {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            String firstLine = bufferedReader.readLine();
            String secondLine = bufferedReader.readLine();

            int m = Integer.parseInt(firstLine);
            int n = Integer.parseInt(secondLine);

            solution(m, n);
        } finally {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
        }
    }

    private static void solution(int m, int n) throws Exception {
        if (m > n || m > 10000 || n > 10000) {
            System.out.print(-1);
            return;
        }

        // init sieve
        int[] sieve = new int[n + 1];
        for (int i = 0; i < n; i ++) {
            sieve[i] = i;
        }

        if (sieve.length >= 1) {
            sieve[0] = -1;
        }

        if (sieve.length >= 2) {
            sieve[1] = -1;
        }

        // filter sieve
        for (int i = 0; i < n; i ++) {
            if (sieve[i] == -1) {
                continue;
            }
            int multipleCount = n / i;
            for (int j = 2; j <= multipleCount; j ++) {
                sieve[i * j] = -1;
            }
        }

        for (int i = 0; i <= m; i ++) {
            sieve[i] = -1;
        }

        // get result
        int sum = Arrays.stream(sieve).filter(element -> element != -1).sum();
        System.out.println(sum == 0? -1 : sum);
        if (sum != 0) {
            int min = Arrays.stream(sieve).filter(element -> element != -1).min().getAsInt();
            System.out.println(min);
        }
    }
}