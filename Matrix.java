/*
 * A Matrix library written in Java
 * (c) 2024 Frgo and Affiliates, inc.
*/

public class Matrix{
	private int rows;
	private int columns;	
	private double[] elements;


	/*
	 * Constructors:
	 *
	 *
	 * public Matrix(double[][] matrixElements)
	 * Creates a matrix from a two-dimensional array of doubles
	 * Blank elements are replaced by ZEROS
	*/ 
	public Matrix(double[][] matrixElements){
		int rows = matrixElements.length;
		int columns = 0;
		for (int i = 0; i < rows; i++){
			columns = Math.max(columns, matrixElements[i].length);
		}

		this.rows = rows;
		this.columns = columns;

		this.elements = new double[rows * columns];

		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++){
				this.elements[j + i*columns] = (j < matrixElements[i].length)?matrixElements[i][j]:0.0;
			}
		}
	}

	/*
	 * public Matrix(int rows, int columns, double[] elements)
	 * Creates a matrix with order rows,columns with elements in elements
	 * Blank elements are replaced by ZEROES
	*/
	public Matrix(int rows, int columns, double[] elements) throws MatrixInvalidDimensionsException{
		if (rows < 0 || columns < 0 || elements.length > rows*columns) throw new MatrixInvalidDimensionsException();
		this.rows = rows;
		this.columns = columns;
		this.elements = new double[rows*columns];	
		for (int i = 0; i < elements.length; i++){
			this.elements[i] = elements[i];
		}
	}

	/*
	 * public Matrix(int rows, int columns, double element)
	 * Creates a matrix of order rows,columns with all elements element
	*/ 
	public Matrix(int rows, int columns, double element) throws MatrixInvalidDimensionsException{
		if (rows < 0 || columns < 0) throw new MatrixInvalidDimensionsException();
		this.elements = new double[rows*columns];
		this.rows = rows;
		this.columns = columns;
		for (int i = 0; i < rows*columns; i++){
			this.elements[i] = element;
		}
	}

	/*
	 * Methods:
	 *
	 *
	 * public String toString()
	 * returns the matrix formatted as a string
	 */
	public String toString(){
		int maxwidth = 0;	
		for (int i = 0; i < this.elements.length; i++){
			maxwidth = Math.max(String.valueOf(this.elements[i]).length(), maxwidth);
		}
		String matrix_string = "";
		for (int i = 0; i < this.rows; i++){
			matrix_string += "[";
			for (int j = 0; j < this.columns; j++){
				matrix_string += " ";
				String this_element = String.valueOf(this.elements[j + i*this.columns]);
				while (this_element.length() < maxwidth) this_element = " " + this_element;	
				matrix_string += this_element;
			}
			matrix_string += " ]";
			if (i != this.rows-1) matrix_string += "\n";
		}

		return matrix_string;
	}

	/*
	 * public double get(int i, int j)
	 * returns the element of the matrix at i,j
	 * begins with 1, as it does in mathematics
	*/
	public double get(int i, int j) throws MatrixIndexOutOfBoundsException{
		if (i < 1 || i > this.rows || j < 1 || j > this.columns) throw new MatrixIndexOutOfBoundsException();
		return this.elements[(i-1)*this.columns+(j-1)];
	}

	/*
	 * public void set(int i, int j, double element)
	 * replaces element i,j of matrix with double element
	 * index begins with 1
	*/
	public void set(int i, int j, double element) throws MatrixIndexOutOfBoundsException{
		if (i < 1 || i > this.rows || j < 1 || j > this.columns) throw new MatrixIndexOutOfBoundsException();
		this.elements[(i-1)*this.columns+(j-1)] = element;
	}

	/*
	 * Static Methods:
	 *
	 *
	 * public static Matrix zero(int rows, int columns)
	 * returns the zero matrix of specified order
	*/
	public static Matrix zero(int rows, int columns) throws MatrixInvalidDimensionsException{
		return new Matrix(rows, columns, 0);
	}

	/*
	 * public static Matrix identity(int size)
	 * returns the identity matrix for a given order
	*/ 
	public static Matrix identity(int size) throws MatrixInvalidDimensionsException{
		Matrix matrix = Matrix.zero(size, size);
		for (int i = 1; i <= size; i++){
			try{
				matrix.set(i,i,1);
			}
			catch (MatrixIndexOutOfBoundsException e){
				System.out.println("This line should never be executed.");
			}
		}
		return matrix;
	}
}
