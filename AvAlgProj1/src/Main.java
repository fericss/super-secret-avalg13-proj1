import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;


public class Main {

	static FactoringPrimeSieve fac;
	public static void main(String[] args2){
		//		System.out.println("Hello world!");
		//		BigInteger b = BigInteger.valueOf(31);
		//		System.out.println(b.isProbablePrime(99));

		/// 262144 tested, and does not run out of memory on kattis: Accepted (0) 	0.88 s 	Java
		/// (not saying 262144 is a good limit or anything, just that it's the maximum amount of memory we're allowed)

		int testNumber = 262146;
		fac = new FactoringPrimeSieve(testNumber);
		Main m = new Main();
		
		BigInteger b = new BigInteger("37778931862957161709568");
		
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			BigInteger a =  new BigInteger(sc.nextLine());
			if(a.compareTo(b)>=0){
				System.out.println("fail\n");
				continue;
			}

			m.getFactors(a);
			System.out.println();
		}
		 

		//		System.out.print(testNumber-2+": ");
		//		for(int i : fac.numbers[testNumber-2].getFactors()){
		//			System.out.print(i+ " ");
		//		}

//		BigInteger b = new BigInteger(70,new Random());
//		Main m = new Main();
//		System.out.println(b+": ");
//		m.getFactors(b);


	}
	private static BigInteger LOOKUP_LIMIT = new BigInteger("262146");
	private void getFactors(BigInteger _rest){
			if(_rest.isProbablePrime(100)){
				System.out.println(_rest+" ");
				return;
			}
			BigInteger val = RichardPollard.rho(_rest);
			
			if(val.compareTo(LOOKUP_LIMIT)<1){
				for(int i : fac.numbers[val.intValue()].getFactors()){
					System.out.println(i+ " ");
				}
			}
			else{
				if(val.isProbablePrime(100)){
					System.out.println(val+" ");
				}
				else{
					getFactors(val);
				}
			}
			
			getFactors(_rest.divide(val));
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
