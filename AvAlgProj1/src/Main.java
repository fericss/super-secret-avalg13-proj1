import java.math.BigInteger;
import java.util.Scanner;


public class Main {

	
	public static void main(String[] args2){
//		System.out.println("Hello world!");
//		BigInteger b = BigInteger.valueOf(31);
//		System.out.println(b.isProbablePrime(99));
		
		/// 262144 tested, and does not run out of memory on kattis: Accepted (0) 	0.88 s 	Java
		/// (not saying 262144 is a good limit or anything, just that it's the maximum amount of memory we're allowed)
		
		int testNumber = 262144;
		FactoringPrimeSieve fac = new FactoringPrimeSieve(testNumber);
		
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			double a = sc.nextDouble();
			if(a>testNumber){
				System.out.println("fail\n");
				continue;
			}
			int num;
			try{
				num = (int) a;
				if(num>testNumber){
					System.out.println("fail\n");
					continue;
				}
			}
			catch(Exception e){
				System.out.println("fail\n");
				continue;
			}
			for(int i :  fac.numbers[num].getFactors()){
				System.out.println(i);
			}
			System.out.println();
		}
		
		
//		System.out.print(testNumber+": ");
//		for(int i : fac.numbers[testNumber].getFactors()){
//			System.out.print(i+ " ");
//		}
		
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
