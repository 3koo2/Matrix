/*
 * A sample class exhibiting the functions of Matrix.java
*/

public class Main{
	public static void main(String[] args) throws SquareMatrixExpected{
		Matrix matrix = Matrix.identity(3); // create an identity matrix with size 3
		System.out.println("Identity Matrix 3x3:\n");
		System.out.println(matrix.toString()+"\n");

		Matrix matrix2 = Matrix.zero(3, 4); // create a zero matrix
		System.out.println("Zero Matrix 3x4:\n");
		System.out.println(matrix2.toString()+"\n");	

		Matrix matrix3 = new Matrix(2, 3, 5); // create 2x3 matrix with all elements as 5.0
		System.out.println("2x3 Matrix with elements 5.0\n");
		System.out.println(matrix3.toString()+"\n");

		Matrix matrix4 = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8}}); //create matrix by specifying all elements
		System.out.println("3x3 Matrix defined with constructor double[][]\n");
		System.out.println(matrix4.toString()+"\n");

		Matrix matrix5 = new Matrix(3, 3, new double[]{1,2,3,4,5,6,7,8}); //create matrix by specifying all elements
		System.out.println("3x3 Matrix defined with constructor int int double[]\n");
		System.out.println(matrix5.toString()+"\n");

		double matrix5_12 = matrix5.get(1,2); //get element of matrix [1,2]
		System.out.println("Matrix 5[1,2] = "+matrix5_12 + "\n");

		matrix5.set(1,2,7); //set element [1,2] of matrix5 to value 7.0
		System.out.println("Replace M5[1,2] with 7.0\n");
		System.out.println(matrix5.toString()+"\n");

		System.out.println("|M5|\n");
		System.out.println(matrix5.determinant());// print the determinant of matrix 5

		Matrix sum = Matrix.add(matrix4, matrix5);//add matrixes 4 and 5
		System.out.println("Matrix 4 + 5 = \n");
		System.out.println(sum.toString());

		Matrix A = new Matrix(2, 2, new double[]{3,7,2,6});
		Matrix B = new Matrix(2, 2, new double[]{2,6,8,4});

		System.out.println("\nA=");
		System.out.println(A.toString());
		System.out.println("\nB=");
		System.out.println(B.toString());

		Matrix a3 = Matrix.multiply(A, 3);
		System.out.println("\n3A=");
		System.out.println(a3.toString());

		Matrix product = Matrix.multiply(A, B);
		System.out.println("\nAB=");
		System.out.println(product.toString());

		Matrix product2 = Matrix.multiply(B, A);
		System.out.println("\nBA=");
		System.out.println(product2.toString());

		Matrix t = product2.transpose();
		System.out.println("\nAB Transpose");
		System.out.println(t.toString());

		System.out.println("\nDeterminant Matrix of A:");
		System.out.println(A.det_matrix().toString());
		
		System.out.println("\ntranspose of Determinant Matrix of A:");
		System.out.println(A.det_matrix().transpose().toString());

		System.out.println("\nInverse of MatrixA:");
		System.out.println(A.inverse().toString());
	}
}
