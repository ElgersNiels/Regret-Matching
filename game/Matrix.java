package game;
import java.util.Arrays;
import java.util.Iterator;

public class Matrix<T> implements Iterable<T> {

	private int offset; //0 if 1-index. 1 if 0-index.
	
	private int[] lengths; //The lengths of the dimensions.
	private Object[] values; //Array holding the values.
	
	/**
	 * Multidimensional array.
	 * @param zeroIndex Indicates if used indexing is zero based or not.
	 * @param lengths The lengths of each dimension.
	 */
	public Matrix(boolean zeroIndex, int[] lengths) {
		this.offset = zeroIndex ? 0 : 1;
		
		this.lengths = lengths;
		
		//May throw exception if length/size is too big for VM to handle.
		this.values = new Object[
		                         Arrays.stream(lengths)
		                         .reduce(1, (a,b) -> a*b) 
		                         ];
	}
	
	/**
	 * Multidimensional array with zero indexing.
	 * @param lengths The lengths of each dimension.
	 */
	public Matrix(int[] lengths) {
		this(true, lengths);
	}
	
	/**
	 * Get the element stored at given indices.
	 * @param indices The indices.
	 * @return The element stored at given indices.
	 */
	@SuppressWarnings("unchecked")
	public T get(int[] indices) {
		return (T) this.values[getIndex(indices)];
	}
	
	/**
	 * Put a value at given indices.
	 * @param indices The indices.
	 * @param value The value.
	 */
	public void put(int[] indices, T value) {
		this.values[getIndex(indices)] = value;
	}
	
	/**
	 * Get the length of given dimension. Dimensions are zero based.
	 * @param dimension The dimension.
	 * @return The length of given dimension.
	 */
	public int getLengthOfDim(int dimension) {
		try {
			return lengths[dimension];
		} catch (IndexOutOfBoundsException e) {
			throw new IndexOutOfBoundsException("Given index is " + dimension + " but range is (0-" + (lengths.length-1) + ").");
		}
	}

	/**
	 * Get the number of dimensions.
	 * @return The number of dimensions.
	 */
	public int getNbOfDim() {
		return lengths.length;
	}
	
	/**
	 * Checks if the Matrix is zero-indexed or not.
	 * @return true if the Matrix is zero-indexed. false if it is one-indexed.
	 */
	public boolean isZeroIndexed() {
		return offset == 0;
	}
	
	/**
	 * Switch to zero indexing.
	 */
	public void toZeroIndexing() {
		this.offset = 0;
	}
	
	/**
	 * Switch to one indexing.
	 */
	public void toOneIndexing() {
		this.offset = 1;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<T> iterator() {
		return (Iterator<T>) Arrays.stream(values).iterator();
	}

	private int getIndex(int[] indices) {
		isInBounds(indices);
		
		int index = indices[0] - offset;
       	
       	int mult = 1;
       	for (int i = 1; i <= indices.length - 1; i++) {
       		mult *= lengths[i-1];
       		index += (indices[i]-offset)*mult;
       	}
       	
       	return index;
	}
	
	private boolean isInBounds(int[] indices) {
		for (int i = 0; i < indices.length; i++)
			if (indices[i] - offset >= lengths[i])
				throw new IndexOutOfBoundsException(
						"Given indices are " + Arrays.toString(indices) 
						+ " but range is (" + offset + "-" + Arrays.toString(Arrays.stream(lengths).map(l -> l - (1-offset)).toArray()) +")"
						);
		return true;
	}
}