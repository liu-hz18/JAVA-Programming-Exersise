
class BigInteger implements EasyInteger {
	// TODO define user specified fields
	// Warning 1: For Open and Close Principle, non-private fields are illegal.
	// Warning 2: References, arrays contain High-precision Numbers are illegal.
	//          For extension, all non-primitive or non-Number types are illegal.
	// Example for 1: public int len;(error) -> private int len;(correct)
	// Example for 2: java.math.BigInteger $233;(error) -> Number $233;(error) -> Object $233;(error)
	// Example for 2(extension): java.util.Vector $233;(error)
	// Example(primitive type): int[] num, nn[];(correct)
	// Example(primitive boxed in Number object): Integer[] num, nn[];(correct)
	private int[] number;
	/**
	 * This constructor convert String to BigInteger.
	 * @param s the input String, only contains character 0-9
	 */
	BigInteger(final String s) {
		// TODO implements this constructor
		int mlength = s.length()-1;
		if (mlength >= 0){
			this.number = new int[mlength+1];
			for (int i = mlength; i >= 0; i--) {
				this.number[mlength-i] = s.charAt(i) - '0';
			}
		}	
	}
	/**
	 * Calculate the value of (this + val).
	 * This method don't change input arguments.
	 * @param val The value to add to this
	 * @return a new Integer whose value is (this + val)
	 */
	@Override
	public EasyInteger add(final EasyInteger val) {
		final BigInteger v = (BigInteger)val;
		BigInteger result = new BigInteger("");
		// TODO let result <- this + v
		int this_len = this.number.length;
		int v_len = v.number.length;
		int max_len = Math.max(this_len, v_len) + 1;
		int[] temp_number = new int[max_len];
		//copy to maybe-longer array
		for (int i = 0; i < this_len; i++) {
			temp_number[i] = this.number[i];
		}
		//bit-wise add
		for (int i = 0; i < v_len; i++) {
			temp_number[i] += v.number[i];
		}
		//solve overflow
		for (int i = 0; i < max_len-1; i++) {
			temp_number[i+1] += temp_number[i] / 10;
			temp_number[i] %= 10;
		}
		//ignore prefix zeros
		for (; temp_number[max_len-1]==0 && max_len>1; max_len--){
			//blank
		}
		//copy to result
		result.number = new int[max_len];
		for (int i = 0; i < max_len; i++) {
			result.number[i] = temp_number[i];
		}
		return result;
	}
	/**
	 * Convert this to String.
	 * @return the String representation of this
	 */
	@Override
	public String toString() {
		final StringBuilder s=new StringBuilder();
		// TODO use s.append(...) to build the string
		for (int i = this.number.length-1; i >= 0; i--) {
			s.append((char)(this.number[i] + '0'));
		}
		return s.toString();
	}
	
	// TODO define user specified methods
}
