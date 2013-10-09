import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Fredric
 *
 * Here things gets real.
 */
public class Main {

	static FactoringPrimeSieve fac;
	BigInteger a, b, c;

	public static void main(String[] args2){
		new Main();
	}
	private Main(){
		/**
		 * 262144 tested, and does not run out of memory on kattis: Accepted (0) 	0.88 s 	Java
		 * (not saying 262144 is a good limit or anything, just that it's the maximum amount of memory we're allowed)
		 */
		// 2^18 = 262144, value not yet set in stone
		a = new BigInteger("262144");
		fac = new FactoringPrimeSieve(a.intValue());

		// This we want around 2^70 = 1180591620717411303424
		// 2^75 = 37778931862957161709568
		b = new BigInteger("37778931862957161709568");

		// This is 2^100
		c = new BigInteger("1267650600228229401496703205376");

		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			BigInteger in =  new BigInteger(sc.nextLine());
			getFactors(in);
			System.out.println();
		}
	}

	/**
	 * What on earth could the factors possibly be?
	 * @param rest The number we want to know what 'tis made of.
	 */
	private void getFactors(BigInteger rest){
		if(rest.isProbablePrime(100)){
			System.out.println(rest);
			return;
		}
		else if(rest.compareTo(c)>0){
			// Something is weird if we end up in here, let's crash kattis
			System.out.println("OMG LOL WHAT IS THIS");
		}
		else if(rest.compareTo(b)>0){
			// This is where we put Lenstra elliptic curve 
			System.out.println("fail\n");
			return;
		}
		else if(rest.compareTo(a)>0){
			// Yay, let's do Richard Brent's modification of Pollard's rho algorithm.
			BigInteger val = RichardPollard.rho(rest);
			if(val.isProbablePrime(100)){
				System.out.println(val);
			}
			else{
				getFactors(val);
			}
			getFactors(rest.divide(val));
		}
		else{
			// Lolwut what a small number, let's just check this table of ours.
			for(int i : fac.numbers[rest.intValue()].getFactors()){
				System.out.println(i+ " ");
			}
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
