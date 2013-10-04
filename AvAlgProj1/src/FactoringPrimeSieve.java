import java.util.LinkedList;

/**
 * 
 * @author Fredric
 * Calculates all factors in all numbers up to N
 */
public class FactoringPrimeSieve {
	public Number[] numbers;
	public static int MAX_FACTORS;
	public FactoringPrimeSieve(int N) {
		MAX_FACTORS = binlog(N);

		// Here we store all our numbers and their factors
		numbers = new Number[N + 1];


		// initially assume all integers are prime
		boolean[] isPrime = new boolean[N + 1];
		for (int i = 2; i <= N; i++) {
			isPrime[i] = true;
		}
		
		for (int i = 2; i <= N; i++) {
			if (isPrime[i]) {
				numbers[i] = new Number(i);
				for (int j = 2; i*j <= N; j++) {
					
					if(numbers[i*j]==null){
						numbers[i*j] = new Number();
					}
					numbers[i*j].add(i);
					for(int k = j ; k%i==0 ; k /= i){
						numbers[i*j].add(i);
					}
					
				
					isPrime[i*j] = false;
				}
			}
		}

//		for(int t = 0; t<N+1 ; t++){
//			Number n = numbers[t];
//			System.out.print(t+": ");
//			if(n!=null){
//				for(int i : n.getFactors()){
//					if(i==0) break;
//					System.out.print(i+" ");
//				}
//				
//			}
//			System.out.println();
//		}
	}
	public static int binlog( int bits ) // returns 0 for bits=0
	{
	    int log = 0;
	    if( ( bits & 0xffff0000 ) != 0 ) { bits >>>= 16; log = 16; }
	    if( bits >= 256 ) { bits >>>= 8; log += 8; }
	    if( bits >= 16  ) { bits >>>= 4; log += 4; }
	    if( bits >= 4   ) { bits >>>= 2; log += 2; }
	    return log + ( bits >>> 1 );
	}
}

/**
 * 
 * @author Fredric
 * A number with a linked list showing what factors it has in it
 */
class Number {
	private int[] factors = new int[FactoringPrimeSieve.MAX_FACTORS];
	private int count = 0;

	public Number(){}
	public Number(int B){
		factors[0] = B;
		count = 1;
	}
	public void add(int A){
		factors[count] = A;
		count++;
	}
	public int[] getFactors(){
		int[] _factors = new int[count];
		for(int i = 0; i<count; i++){
			_factors[i] = factors[i];
		}
		return _factors;
	}

}



