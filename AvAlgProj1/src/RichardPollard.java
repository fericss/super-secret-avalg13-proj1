import java.math.BigInteger;
import java.util.Random;

/**
 * 
 * @author Fredric
 *
 *
 *http://comeoncodeon.wordpress.com/2010/09/18/pollard-rho-brent-integer-factorization/
 */
public class RichardPollard {
	
	private static BigInteger ZERO = new BigInteger("0");
	private static BigInteger ONE = new BigInteger("1");
	private static BigInteger TWO = new BigInteger("2");
	private static Random rand = new Random();
	/**
	 * TODO: This needs major rewriting.
	 * @param N
	 * @return
	 */
	public static BigInteger rho(BigInteger N){
		BigInteger ys = null, x = null;
		BigInteger 
				y = new BigInteger(N.bitLength(),rand), 
				c = new BigInteger(N.bitLength(),rand), 
				m = new BigInteger(N.bitLength(),rand);
		BigInteger 	g = RichardPollard.ONE,
					r = RichardPollard.ONE,
					q = RichardPollard.ONE;
		while(g.compareTo(RichardPollard.ONE)==0){
//			System.out.println("wa");
			x = y;
			for(int i = 0; i<r.doubleValue();i++){
				y = y.multiply(y).remainder(N).add(c).remainder(N);
			}
			BigInteger k = RichardPollard.ZERO;
			while(k.compareTo(r)<1 && g.equals(RichardPollard.ONE)){
				ys = y;
				BigInteger mrk = r.subtract(k).min(m);
				for(BigInteger i = RichardPollard.ZERO; i.compareTo(mrk)<1; i=i.add(RichardPollard.ONE)){
					y = y.multiply(y).remainder(N).add(c).remainder(N);
					q = q.multiply(x.subtract(y).abs()).remainder(N);
				}
				g = q.gcd(N);
				k = k.add(m);
			}
			r = r.multiply(RichardPollard.TWO);
			
		}
		if(g.equals(N)){
			while(true) {
				ys = ys.multiply(ys).remainder(N).add(c).remainder(N);
				g = x.subtract(ys).abs().gcd(N);
				if(g.compareTo(RichardPollard.ONE)==1){
					break;
				}
			}
		}
		
//		System.out.println(y.doubleValue()+" "+r.doubleValue());
		return g;
		
	}

}

/**
	
In 1980, Richard Brent published a faster variant of the rho algorithm. 
He used the same core ideas as Pollard, 
but he used a different method of cycle detection that was faster than Floyd's original algorithm.

Brent's algorithm is as follows:

Input: n, the integer to be factored; x0, such that 0 ≤ x0 ≤ n; 
m such that m > 0; and f(x), a pseudo-random function modulo n.

Output: a non-trivial factor of n, or failure.

    y ← x0, r ← 1, q ← 1.
    Do:
        x ← y
        For i = 1 To r:
            y ← f(y)
        k ← 0
        Do:
            ys ← y
            For i = 1 To min(m, r − k):
                y ← f(y), q ← (q × |x − y|) mod n
            g ← GCD(q, n), k ← k + i 
        Until (k ≥ r or g > 1)
        r ← 2r
    Until g > 1
    If g = n then
        Do:
            ys ← f(ys), g ← GCD(|x − ys|, n)
        Until g > 1
    If g = n then return failure, else return g



*/