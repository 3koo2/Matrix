/*
 * A Matrix library written in Java
 * (c) 2024 Frgo and Affiliates, inc.
*/

public class Matrix{
	public int rows;
	public int columns;	
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
	public Matrix(int rows, int columns, double[] elements){
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
	public Matrix(int rows, int columns, double element){
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
	public double get(int i, int j){
		return this.elements[(i-1)*this.columns+(j-1)];
	}

	/*
	 * public void set(int i, int j, double element)
	 * replaces element i,j of matrix with double element
	 * index begins with 1
	*/
	public void set(int i, int j, double element){
		this.elements[(i-1)*this.columns+(j-1)] = element;
	}

	/*
	 * public Matrix submatrix(int i, int j)
	 * returns the submatrix not including the row i or column j
	*/
	public Matrix submatrix(int i, int j){
		double[] elements = new double[(this.rows-1)*(this.columns-1)];
		int elem = 0;
		for (int k = 0; k < this.rows; k++){
			for (int l = 0; l < this.columns; l++){
				if (k != i-1 && l != j-1){
					elements[elem] = this.get(k+1, l+1);
					elem++;
				}	
			}
		}
		Matrix submatrix = new Matrix(this.rows-1, this.columns-1, elements);
		return submatrix;
	}

	/*
	 * public double determinant()
	 * returns a double value that is the determinant of the matrix
	*/
	public double determinant() throws SquareMatrixExpected{
		if (this.rows != this.columns) throw new SquareMatrixExpected();
		if (this.rows == 1){
			//1x1 matrix
			//determinant is the element
			return this.get(1,1);
		}
		else{
			//nxn matrix
			//use cofactors method to find determinant
			double det = 0;
			for (int i = 0; i < this.rows; i++){
				Matrix submatrix = this.submatrix(1, i+1);
				det += Math.pow(-1,i%2)*this.get(1, i+1)*submatrix.determinant();
			}
			return det;
		}
	}

	/*
	 * public Matrix copy()
	 * returns a Matrix identical to this
	*/
	public Matrix copy(){
		return new Matrix(this.rows, this.columns, this.elements);
	}

	/*
	 * public Matrix transpose()
	 * returns a Matrix containing the transpose of this
	 * (switch rows and columns)
	*/
	public Matrix transpose(){
		Matrix t = new Matrix(this.columns, this.rows, 0);
		for (int i = 0; i < this.rows; i++){
			for (int j = 0; j < this.columns; j++){
				t.set(j+1, i+1, this.get(i+1, j+1));
			}
		}
		return t;
	}

	/*
	 * public Matrix det_matrix()
	 * returns a Matrix containing the determinant matrix of this
	 * (det of submatrix per element, alternating signs)
	*/
	
	public Matrix det_matrix() throws SquareMatrixExpected{
		if (this.rows != this.columns) throw new SquareMatrixExpected();
		Matrix dm = new Matrix(this.rows, this.columns, 0);
		for (int i = 0; i < this.rows; i++){
			for (int j = 0; j < this.columns; j++){
				dm.set(i+1, j+1, Math.pow(-1, i + j) * this.submatrix(i+1, j+1).determinant());
			}
		}
		return dm;
	}

	/*
	 * public Matrix inverse()
	 * returns the multiplicative inverse of this
	*/
	public Matrix inverse() throws SquareMatrixExpected{
		if (this.rows != this.columns) throw new SquareMatrixExpected();
		return Matrix.multiply(1.0/this.determinant(), this.det_matrix().transpose());
	}

	/*
	 * Static Methods:
	 *
	 *
	 * public static Matrix zero(int rows, int columns)
	 * returns the zero matrix of specified order
	*/
	public static Matrix zero(int rows, int columns){
		return new Matrix(rows, columns, 0);
	}

	/*
	 * public static Matrix identity(int size)
	 * returns the identity matrix for a given order
	*/ 
	public static Matrix identity(int size){
		Matrix matrix = Matrix.zero(size, size);
		for (int i = 1; i <= size; i++){
			matrix.set(i,i,1);
		}
		return matrix;
	}

	/*
	 * Static Operations:
	 *
	 *
	 * public static Matrix add(Matrix a, Matrix b)
	 * returns the sum of two matrixes
	*/
	public static Matrix add(Matrix a, Matrix b){
		double[] new_matrix = new double[a.rows*a.columns];
		for (int i = 0; i < a.rows; i++){
			for (int j = 0; j < a.columns; j++){
				new_matrix[j+i*a.columns] = a.get(i+1, j+1)+b.get(i+1, j+1);
			}
		}

		Matrix m = new Matrix(a.rows, a.columns, new_matrix);
		return m;
	}

	/*
	 * public static Matrix multiply(Matrix a, Matrix b)
	 * returns the product of AB
	*/
	public static Matrix multiply(Matrix a, Matrix b){
		double[] elements = new double[a.rows*b.columns];

		for (int i = 0; i < a.rows; i++){
			for (int j = 0; j < b.columns; j++){
				double element = 0;
				for (int k = 0; k < a.columns; k++){
					element += a.get(i+1, k+1) * b.get(k+1, j+1);	
				}
				elements[j + i*b.columns] = element;
			}
		}
		Matrix product = new Matrix(a.rows, b.columns, elements);
		return product;
	}

	/*
	 * public static Matrix multiply(Matrix a, double b)
	 * returns the product of Ab, where A is a matrix and b is a scalar
	*/
	public static Matrix multiply(Matrix a, double b){
		System.out.println("Multiplying By A Scalar");
		Matrix m = a.copy();
		for (int i = 0; i < a.rows; i++){
			for (int j = 0; j < a.columns; j++){
				m.set(i+1, j+1, a.get(i+1, j+1)*b);
			}
		}
		return m;
	}

	/*
	 * public static Matrix multiply(double a, Matrix b)
	 * returns multiply(b, a)
	*/
	public static Matrix multiply(double a, Matrix b){
		return Matrix.multiply(b, a);
	}
}
