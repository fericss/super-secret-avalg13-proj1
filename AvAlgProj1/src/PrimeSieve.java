/**
 * 
 * @author Fredric
 * This calculates and provides a list of all prime numbers up to N
 */
public class PrimeSieve {
	private int[] primeNumbers;
    public PrimeSieve(int N) {

        // initially assume all integers are prime
        boolean[] isPrime = new boolean[N + 1];
        for (int i = 2; i <= N; i++) {
            isPrime[i] = true;
        }

        // mark non-primes <= N using Sieve of Eratosthenes
        for (int i = 2; i*i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i; i*j <= N; j++) {
                    isPrime[i*j] = false;
                }
            }
        }

        // count primes
        int primes = 0;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]) primes++;
        }
        
        // Store all primes in an array
        primeNumbers = new int[primes];
        primes = 0;
        for (int i = 2; i <= N; i++) {
            if (isPrime[i]){
            	primeNumbers[primes] = i;
            	primes++;
            }
        }
        
        //System.out.println("The number of primes <= " + N + " is " + primes);
    }
	/**
	 * @return the primeNumbers
	 */
	public int[] getPrimeNumbers() {
		return primeNumbers;
	}
}