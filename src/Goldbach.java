/**
 * Created by senni on 17/08/2017.
 */
import java.util.*;
import java.lang.*;

class Goldbach {
    public static void main(String[] args) {
        int targetSum = 100000;
        if (args.length > 0) {
            targetSum = Integer.parseInt(args[0]);
        }

        ArrayList<Integer> primes = getPrimes(targetSum);
        ArrayList<int[]> primeTerms = getPrimeTerms(primes, targetSum);

        if (validateSums(primeTerms, targetSum)) {
            primeTerms.forEach(terms ->
                    System.out.println(String.format("%d + %d", terms[0], terms[1])));

            System.out.println(
                    String.format("\nThere are %d different ways to get a sum of %d from two positive prime numbers",
                            primeTerms.size(),
                            targetSum));
        } else {
            System.out.println("Sums are not valid!");
        }
    }

    private static boolean validateSums(ArrayList<int[]> primeTerms, int sum) {
        for (int i = 0; i < primeTerms.size(); ++i) {
            int[] terms = primeTerms.get(i);
            if (terms[0] + terms [1] != sum) {
                return false;
            }
        }
        return true;
    }

    private static ArrayList<int[]> getPrimeTerms(ArrayList<Integer> primes, int targetSum) {
        ArrayList<int[]> primeTermCandidates = new ArrayList<>(targetSum);
        for(int i = 0; i < primes.size() && primes.get(i) <= targetSum / 2; ++i) {
            int[] candidate = binarySearchCandidate(primes, targetSum, i);
            if (candidate != null) {
                primeTermCandidates.add(candidate);
            }
        }
        return primeTermCandidates;
    }

    private static int[] binarySearchCandidate(ArrayList<Integer> primes, int targetSum, int i) {
        int left = i + 1;
        int right = primes.size();
        for (int j = (left + right) / 2; left <= right && j < primes.size(); j = (left + right) / 2) {
            int sum = primes.get(i) + primes.get(j);
            if (sum == targetSum) {
                int[] terms = {primes.get(i), primes.get(j)};
                return terms;
            }
            if (sum < targetSum) {
                left = j + 1;
            } else {
                right = j - 1;
            }
        }
        return null;
    }

    // Handy for verifying the result
    private static int[] bruteForceSearchCandidate(ArrayList<Integer> primes, int targetSum, int i) {
        for (int j = i + 1; j < primes.size(); ++j) {
            int sum = primes.get(i) + primes.get(j);
            if (sum == targetSum) {
                int[] terms = {primes.get(i), primes.get(j)};
                return terms;
            } else if (sum > targetSum) {
                break;
            }
        }
        return null;
    }

    private static ArrayList<Integer> getPrimes(int max) {
        ArrayList<Integer> primes = new ArrayList<>(max);
        primes.add(1);
        for (int n = 2; n < max; ++n) {
            if (isPrime(n)) {
                primes.add(n);
            }
        }
        return primes;
    }

    private static boolean isPrime(int n) {
        for (int i = 2; i <= n / 2; ++i) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
