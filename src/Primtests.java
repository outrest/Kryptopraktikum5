	public class Primtests {

		private java.security.SecureRandom random;




		public Primtests() {
			this.random = new java.security.SecureRandom();
		}


		/**
		 * Tests if n is a prime number using Trial division.
		 */
		public boolean isPrime(long n) {
			if (n < 2) return false;
			if (n == 2 || n == 3) return true;
			if (n % 2 == 0 || n % 3 == 0) return false;
			long sqrtN = (long) Math.sqrt(n) + 1;
			for (long i = 6L; i <= sqrtN; i += 6) {
				if (n % (i - 1) == 0 || n % (i + 1) == 0) {
					return false;
				}
			}
			return true;
		}


		/**
		 * Tests if n is a prime number using Fermat primality test with certainty k.
		 */
		public boolean isProbablePrime1(long n, long k) {
			for (int i = 0; i < k; i++) {
				int alloc = 0;
				long bound = n;
				if (n > 4) {
					alloc = 2;
					bound = n - 4;
				}
				long v = alloc + randomPositiveLong(bound);
				if (Math.pow(v, n - 1) % n != 1) {
					return false;
				}
			}
			return true;
		}


		/**
		 * Tests if n is a prime number using Miller-Rabin primality test with certainty k.
		 */
		public boolean isProbablePrime2(long n, long k) {
			if (n < 2) return false;
			if (n == 2 || n == 3) return true;
			if (n % 2 == 0 || n % 3 == 0) return false;
			for (long i = 0; i < k; i++) {
				long v = 2 + randomBoundedLong(2, n - 2);
				if (Math.pow(v, n - 1) % n != 1) {
					return false;
				}
			}
			return true;
		}


		public long randomBoundedLong(long minBound, long maxBound) {
			return normalize(random.nextLong(), Long.MIN_VALUE, Long.MAX_VALUE, minBound, maxBound);
		}


		public long randomPositiveLong(long maxBound) {
			long l = random.nextLong();
			return normalize(l, Long.MIN_VALUE, Long.MAX_VALUE, 0, maxBound);
		}


		public static long normalize(long value, long oldMin, long oldMax, long newMin, long newMax) {
			double percent = ((double) value - (double) oldMin) / ((double) oldMax - (double) oldMin);
			return (long) ((percent * ((double) newMax - (double) newMin)) + newMin);
		}
	}

