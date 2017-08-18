import java.util.ArrayList;

/**
 * Created by senni on 18/08/2017.
 */
public class PrimeTermFinder {
    private final ArrayList<Integer> primes;
    private final int targetSum;

    public PrimeTermFinder(ArrayList<Integer> primes, int targetSum) {
        this.primes = primes;
        this.targetSum = targetSum;
    }

    public ArrayList<int[]> getPrimeTermsByBinarySearch() {
        return getPrimeTerms(this::binarySearchCandidate);
    }

    public ArrayList<int[]> getPrimeTermsByBruteForce() {
        return getPrimeTerms(this::bruteForceSearchCandidate);
    }

    private ArrayList<int[]> getPrimeTerms(SearchAlgorithm searchAlgorithm) {
        ArrayList<int[]> primeTermCandidates = new ArrayList<>(targetSum);
        for(int i = 0; i < primes.size() && primes.get(i) <= targetSum / 2; ++i) {
            int[] candidate = searchAlgorithm.findTerm(i);
            if (candidate != null) {
                primeTermCandidates.add(candidate);
            }
        }
        return primeTermCandidates;
    }

    private int[] binarySearchCandidate(int firstTermIndex) {
        int minBoundary = firstTermIndex + 1;
        int maxBoundary = primes.size();
        for (int secondTermIndex = (minBoundary + maxBoundary) / 2;             // start search from middle
             minBoundary <= maxBoundary && secondTermIndex < primes.size();
             secondTermIndex = (minBoundary + maxBoundary) / 2) {
            int sum = primes.get(firstTermIndex) + primes.get(secondTermIndex);
            if (sum == targetSum) {
                int[] terms = {primes.get(firstTermIndex), primes.get(secondTermIndex)};
                return terms;
            }
            if (sum < targetSum) {
                minBoundary = secondTermIndex + 1;      // sum is too small, move min boundary to checked index + 1
            } else {
                maxBoundary = secondTermIndex - 1;      // sum is too big, move max boundary to checked index - 1
            }
        }
        return null;
    }

    // Handy for verifying the result
    private int[] bruteForceSearchCandidate(int i) {
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

    private interface SearchAlgorithm {
        int[] findTerm(int i);
    }
}
