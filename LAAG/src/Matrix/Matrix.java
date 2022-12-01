package Matrix;

public class Matrix {
    private double[][] matrix;
    private String name;

    private int numberOfEmptyRows;

    public Matrix(double[][] matrix, String name) {
        this.matrix = matrix;
        this.name = name;
        numberOfEmptyRows = 0;
    }

    //<editor-fold desc="Matrix Operations">
    public void addMatrix(Matrix a) {
        if (matrix.length == a.matrix.length && matrix[0].length == a.matrix[0].length) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] += a.matrix[i][j];
                }
            }
        }
    }

    public void subMatrix(Matrix a) {
        if (matrix.length == a.matrix.length && matrix[0].length == a.matrix[0].length) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] -= a.matrix[i][j];
                }
            }
        }
    }

    public void multiplyScalarToMatrix(int scalar) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] *= scalar;
            }
        }
    }

    public static Matrix multiplyMatrix(Matrix a, Matrix b, String name) {
        if (a.matrix[0].length == b.matrix.length) {

            double[][] matrixO = new double[a.matrix.length][b.matrix[0].length];

            for (int i = 0; i < matrixO.length; i++) {
                for (int j = 0; j < matrixO[0].length; j++) {
                    double entry = 0;
                    for (int k = 0; k < a.matrix[0].length; k++) {
                        entry += a.matrix[i][k] * b.matrix[k][j];
                    }
                    matrixO[i][j] = entry;
                }
            }

            return new Matrix(matrixO, name);
        }
        return null;
    }

    public static Matrix powerOfMatrix(Matrix a, String name, int power) {
        Matrix temp = new Matrix(a.matrix, name);
        for (int i = 0; i < power - 1; i++) {
            temp = Matrix.multiplyMatrix(temp, a, name);
        }
        return temp;
    }

    public static double[][] createIdentityMatrix(int length){
        double[][] output =new double[length][length];
        for (int i = 0; i < length; i++) {
            output[i][i]=1;
        }
        return output;
    }

    public static Matrix addIdentityMatrix(Matrix a, String name){
        double[][] matrixO=new  double[a.matrix.length][2*a.matrix.length];
        double[][] identityMatrix=createIdentityMatrix(a.matrix.length);
        for (int i = 0; i < matrixO.length; i++) {
            for (int j = 0; j < matrixO[0].length; j++) {
                if (j<a.matrix.length){
                    matrixO[i][j]=a.matrix[i][j];
                }else{
                    matrixO[i][j]=identityMatrix[i][j-a.matrix.length];
                }
            }
        }
        Matrix temp=new Matrix(matrixO,name);
        return temp;
    }

    public static Matrix makeTransposeMatrix(Matrix a, String name){
        double[][] temp=new double[a.matrix.length][a.matrix.length];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp.length; j++) {
                temp[i][j]=a.matrix[j][i];
            }
        }
        Matrix output=new Matrix(temp,name);
        return output;
    }

    /*public static  CMatrix makeAdjoinMatrix(CMatrix a){

    }*/

    public static Matrix invertedMatrix(Matrix a, String name){
        Matrix input= Matrix.addIdentityMatrix(a,name);
        input.gausJordan();
        double[][] temp=new double[a.matrix.length][a.matrix.length];
        for (int i = 0; i < a.matrix.length; i++) {
            for (int j = 0; j < a.matrix.length; j++) {
                temp[i][j]=input.matrix[i][j+a.matrix.length];
            }
        }
        Matrix output=new Matrix(temp,name);
        return output;
    }

    //</editor-fold>


    //<editor-fold desc="GAUS-JORDAN">
    public void gaus() {
        makeEmptyRowsOnBottom();
        for (int i = 0; i < matrix.length - numberOfEmptyRows; i++) {
            System.out.println(this);
            makeLeadingOneOnTop(i);
            System.out.println(this);
            makeLeadingOnesOne(i);
            System.out.println(this);
            makeNonZerosBeneathLeadingOneZeros(i);
            System.out.println(this);
            makeEmptyRowsOnBottom();
        }
    }
    public void gausJordan() {
        gaus();
        for (int i = matrix.length - 1 - numberOfEmptyRows; i >= 0; i--) {
            makeNonZerosAboveLeadingOneZero(i);
            makeEmptyRowsOnBottom();
        }
    }

    private void makeLeadingOneOnTop(int subMatrixLevel) {
        switchRows(findLeftMostLeadingOne(subMatrixLevel).getY(), subMatrixLevel);
    }

    private Cordinates findLeftMostLeadingOne(int subMatrixLevel) {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = subMatrixLevel; j < matrix.length; j++) {
                if (matrix[j][i] != 0) {
                    return new Cordinates(i, j);
                }
            }
        }
        return new Cordinates(-1, 0);
    }

    private void makeLeadingOnesOne(int subMatrixLevel) {
        if (matrix[subMatrixLevel][findLeadingOne(subMatrixLevel).getY()] != 1) {
            double coefficient = 1 / matrix[subMatrixLevel][findLeadingOne(subMatrixLevel).getY()];
            for (int i = subMatrixLevel; i < matrix[0].length; i++) {
                matrix[subMatrixLevel][i] = matrix[subMatrixLevel][i] * coefficient;
            }
        }
    }

    private Cordinates findLeadingOne(int row) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[row][i] != 0) {
                return new Cordinates(row, i);
            }
        }
        return new Cordinates(-1, 0);
    }

    private void makeNonZerosBeneathLeadingOneZeros(int subMatrixLevel) {
        while (findNonZerosBeneathLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY()) != Integer.MAX_VALUE) {
            rowOperation(
                    subMatrixLevel,
                    -matrix[findNonZerosBeneathLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY())][findLeadingOne(subMatrixLevel).getY()],
                    findNonZerosBeneathLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY())
            );
        }
    }

    private int findNonZerosBeneathLeadingOne(int subMatrixLevel, int colourOfLeadingOne) {
        for (int i = subMatrixLevel + 1; i < matrix.length; i++) {
            if (matrix[i][colourOfLeadingOne] != 0) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }






    public void makeNonZerosAboveLeadingOneZero(int subMatrixLevel) {
        while (findNonZerosAboveLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY()) != Integer.MAX_VALUE) {
            rowOperation(
                    subMatrixLevel,
                    -matrix[findNonZerosAboveLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY())][findLeadingOne(subMatrixLevel).getY()],
                    findNonZerosAboveLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY())
            );
            System.out.println(this);
        }
    }

    public int findNonZerosAboveLeadingOne(int subMatrixLevel, int columnOfSubMatrixLevelLeadingOne) {
        for (int i = subMatrixLevel - 1; i >= 0; i--) {
            if (matrix[i][columnOfSubMatrixLevelLeadingOne] != 0) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }







    private void rowOperation(int sourceRow, double coefficient, int destinationRow) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[destinationRow][i] += matrix[sourceRow][i] * coefficient;
        }
    }











    private void switchRows(int source, int destination) {
        double[] temp = matrix[source];
        matrix[source] = matrix[destination];
        matrix[destination] = temp;
    }

    private void makeEmptyRowsOnBottom() {
        int numberOfZeros = 0;
        for (int i = 0; i < matrix.length - numberOfEmptyRows; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    numberOfZeros++;
                } else {
                    numberOfZeros = 0;
                    break;
                }
            }
            if (numberOfZeros == matrix[0].length) {
                switchRows(i, matrix.length - numberOfEmptyRows - 1);
                numberOfEmptyRows++;
            }
        }
    }
    //</editor-fold>
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + "=\n{\n");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                stringBuilder.append("\t" + String.format("%4.1f ", matrix[i][j]));
            }

            stringBuilder.append("\n");
        }
        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }
}
