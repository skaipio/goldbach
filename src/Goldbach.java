/**
 * Created by senni on 17/08/2017.
 */
import java.util.*;
import java.lang.*;

class Goldbach {
    public static void main(String[] args) {
        Options options = new Options(args);

        ArrayList<Integer> primes = getPrimes(options.getTargetSum());

        PrimeTermFinder termFinder = new PrimeTermFinder(primes, options.getTargetSum());

        long start = System.currentTimeMillis();
        ArrayList<int[]> primeTermsByBruteForce = termFinder.getPrimeTermsByBruteForce();
        long bruteForceSearchTime = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        ArrayList<int[]> primeTermsByBinarySearch = termFinder.getPrimeTermsByBinarySearch();
        long binarySearchTime = System.currentTimeMillis() - start;

        ResultPrinter printer = new ResultPrinter(options);
        printer.printResult(primeTermsByBruteForce);
        System.out.println(String.format("Brute force search took %d ms", bruteForceSearchTime));

        printer.printResult(primeTermsByBinarySearch);
        System.out.println(String.format("Binary search took %d ms", binarySearchTime));
    }

    private static class ResultPrinter {
        private Options options;

        public ResultPrinter(Options options) {
            this.options = options;
        }

        public void printResult(ArrayList<int[]> primeTerms) {
            if (validateSums(primeTerms)) {
                if (options.isPrintSumsEnabled()) {
                    primeTerms.forEach(terms ->
                            System.out.println(String.format("%d + %d", terms[0], terms[1])));
                }

                System.out.println(
                        String.format("\nThere are %d different ways to get a sum of %d from two positive prime numbers",
                                primeTerms.size(),
                                options.getTargetSum()));
            } else {
                System.out.println("Sums are not valid!");
            }
        }

        private boolean validateSums(ArrayList<int[]> primeTerms) {
            for (int i = 0; i < primeTerms.size(); ++i) {
                int[] terms = primeTerms.get(i);
                if (terms[0] + terms [1] != options.getTargetSum()) {
                    return false;
                }
            }
            return true;
        }
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
