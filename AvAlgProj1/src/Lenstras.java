import java.math.BigInteger;


/**
 * 
 * 
 * 
 * 
 * 
 * 
 *  *  THIS CODE DOES NOT WORK!!!!
 *   *  THIS CODE DOES NOT WORK!!!!
 *    *  THIS CODE DOES NOT WORK!!!!
 *     *  THIS CODE DOES NOT WORK!!!!
 *      *  THIS CODE DOES NOT WORK!!!!
 *       *  THIS CODE DOES NOT WORK!!!!
 *        *  THIS CODE DOES NOT WORK!!!!
 *         *  THIS CODE DOES NOT WORK!!!!
 *          *  THIS CODE DOES NOT WORK!!!!
 *           *  THIS CODE DOES NOT WORK!!!!
 *            *  THIS CODE DOES NOT WORK!!!!
 *             *  THIS CODE DOES NOT WORK!!!!
 *              *  THIS CODE DOES NOT WORK!!!!
 *               *  THIS CODE DOES NOT WORK!!!!
 *                *  THIS CODE DOES NOT WORK!!!!
 * 
 *  THIS CODE DOES NOT WORK!!!!
 *  Cannot find any god damn documentation on how to properly implement this.
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author Fredric
 *
 */

public class Lenstras {

	private static BigInteger ZERO = new BigInteger("0");
	private static BigInteger ONE = new BigInteger("1");
	private static BigInteger TWO = new BigInteger("2");
	private static BigInteger THREE = new BigInteger("3");
	private static BigInteger FOUR = new BigInteger("4");
	private static BigInteger VAL_27 = new BigInteger("27");

	public Lenstras(){

	}


	/**
	 * Finds a factor for the number N
	 * @param N The number we want a factor for
	 * @return The factor for the number N
	 * 
	 * I'm awesome at comments.
	 */
	public BigInteger factorize(BigInteger N, BigInteger a, BigInteger b){
		for(BigInteger i = ZERO; i.compareTo(a)<1; i=i.add(ONE)){
			if(a.pow(3).multiply(FOUR).add(VAL_27).mod(N).equals(ZERO)){
				continue;
			}
			BigInteger [] point = { ZERO.mod(N), ONE.mod(N), ONE};
			for(BigInteger j = ZERO; j.compareTo(b)<1; j=j.add(ONE)){
				point = mult(b.add(ONE),point,a);
				if(point[2].equals(ZERO)){
					break;
				}
				else if(point[2].compareTo(ONE)>0){
					return point[2].gcd(N);
				}
			}
		}

		System.out.println("lolwut increase a and b");
		return null;

	}

	public BigInteger [] mult(BigInteger N, BigInteger [] P, BigInteger a){
		BigInteger [] result = { ZERO, ONE, ZERO};
		BigInteger [] pow = P;
		while(!N.equals(ZERO)){
			if(N.remainder(TWO).equals(ONE)){
				result = add(pow, pow, a);
			}
			N = N.divide(TWO);
			pow = add(pow, pow, N);
		}
		
		
		return result;
	}

	public BigInteger[] add(BigInteger[] P,BigInteger[] Q,BigInteger a){
		BigInteger m = ONE;
		if(!P[2].equals(ONE)){
			if(P[1].equals(ONE)){
				return Q;
			}
			return P;
		}
		if(!Q[2].equals(ONE)){
			if(Q[1].equals(ONE)){
				return P;
			}
			return Q;
		}
		if(P[0].equals(Q[0]) && P[1].equals(Q[1].negate())){
			return new BigInteger[]{ZERO,ONE,ZERO};
		}
		if(P[0].equals(Q[0]) && P[1].equals(Q[1])){
			m = (THREE.multiply(P[0].pow(2))).add(a).divide(TWO).divide(P[1]);
		}
		else{
			m = Q[1].subtract(P[1]).divide(Q[0].subtract(P[0]));
		}
		BigInteger x3 = m.pow(2).subtract(P[0].subtract(Q[0]));
		return new BigInteger [] {x3, m.multiply(P[0].subtract(x3)).subtract(P[1]),ONE};
	}

	/**
	 * http://www.uam.es/personal_pdi/ciencias/fchamizo/asignaturas/cripto1011/lenstra.pdf
	 */
}

