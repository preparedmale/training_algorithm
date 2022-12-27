package beakjoon.step.basicMath2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Q1978 {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            /* input data */
            String firstLine = reader.readLine();
            String secondLine = reader.readLine();

            /* refining data */
            int lengthOfNumbers = Integer.parseInt(firstLine);
            int[] numbers = Arrays.stream(secondLine.split(" "))
                    .mapToInt(element -> Integer.parseInt(element))
                    .toArray();

            /* processing */
            String result = String.valueOf(solution(numbers));

            /* print result */
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static int solution(int[] numbers) throws Exception {
        int maxValue = Arrays.stream(numbers).max().getAsInt();

        /* init sieve */
        int[] sieve = new int[maxValue + 1];
        for (int i = 1; i < sieve.length; i ++) {
            sieve[i] = i;
        }

        sieve[0] = -1;
        sieve[1] = -1;

        /* leave only primeNumber */
        for (int i = 0; i < sieve.length; i ++) {
            if (sieve[i] == -1) {
                continue;
            }

            int multipleCount = maxValue / i;
            for (int j = 2; j <= multipleCount; j ++) {
                sieve[i * j] = -1;
            }
        }

        /* get prime numbers */
        int result = 0;
        int [] primeNumbers = Arrays.stream(sieve).filter(element -> element != -1).toArray();

        /* get result */
        for (int number : numbers) {
            if (Arrays.stream(primeNumbers).anyMatch(element -> element == number)) {
                result++;
            };
        }

        return result;
    }
}