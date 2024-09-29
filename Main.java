/*
 * A sample class exhibiting the functions of Matrix.java
*/

public class Main{
	public static void main(String[] args){
		try{
			Matrix matrix = Matrix.identity(3); // create an identity matrix with size 3
			System.out.println(matrix.toString()+"\n");

			Matrix matrix2 = Matrix.zero(3, 4); // create a zero matrix
			System.out.println(matrix2.toString()+"\n");	

			Matrix matrix3 = new Matrix(2, 3, 5); // create 2x3 matrix with all elements as 5.0
			System.out.println(matrix3.toString()+"\n");

			Matrix matrix4 = new Matrix(new double[][]{{1,2,3},{4,5,6},{7,8}}); //create matrix by specifying all elements
			System.out.println(matrix4.toString()+"\n");

			Matrix matrix5 = new Matrix(3, 3, new double[]{1,2,3,4,5,6,7,8}); //create matrix by specifying all elements
			System.out.println(matrix5.toString()+"\n");

			double matrix5_12 = matrix5.get(1,2); //get element of matrix [1,2]
			System.out.println("Matrix 5[1,2] = "+matrix5_12 + "\n");

			matrix5.set(1,2,7.0); //set element [1,2] of matrix5 to value 7.0
			System.out.println(matrix5.toString()+"\n");
		}
		catch (MatrixInvalidDimensionsException | MatrixIndexOutOfBoundsException e){
			e.printStackTrace();
		}
	}
}
