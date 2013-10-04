import java.math.BigInteger;


public class Main {

	
	public static void main(String[] args){
		System.out.println("Hello world!");
		BigInteger b = BigInteger.valueOf(31);
		System.out.println(b.isProbablePrime(99));
		
		PrimeSieve p = new PrimeSieve(65536);
		FactoringPrimeSieve fac = new FactoringPrimeSieve(65536);
		System.out.print("65536: ");
		for(int i : fac.numbers[65536].getFactors()){
			System.out.print(i+ " ");
		}
		
	}
	
    /**
      "How big are your numbers?" determines the method to use:

        Less than 2^16 or so: Lookup table.
        Less than 2^70 or so: Richard Brent's modification of Pollard's rho algorithm.
        Less than 10^50: Lenstra elliptic curve factorization
        Less than 10^100: Quadratic Sieve
        More than 10^100: General Number Field Sieve

     */
}
