package Matrix;

public class Matrix {
    private double[][] matrix;
    private String name;

    private int numberOfEmptyRows;

    public Matrix(String name, double[][] matrix) {
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

    public Cordinates findTheVectorWithMostZeros() {
        int[] row = new int[matrix.length];
        int[] column = new int[matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[i][j] == 0) {
                    row[i]++;
                    column[j]++;
                }
            }
        }

        int max = 0;
        Cordinates maxCoordinate = new Cordinates(-1, -1);

        for (int i = 0; i < row.length; i++) {
            if (row[i] > max) {
                max = row[i];
                maxCoordinate.setX(i);
                maxCoordinate.setY(-1);
            }
        }
        for (int i = 0; i < column.length; i++) {
            if (column[i] > max) {
                max = column[i];
                maxCoordinate.setY(i);
                maxCoordinate.setX(-1);
            }
        }
        return maxCoordinate;
    }
    public int getNumOfRows(){
        return matrix.length;
    }
    public int getNumOfColumns(){
        return matrix[0].length;
    }

    public double[][] getMatrix() {
        return matrix;
    }

    public double[] returnColumn(int column){
        double[] out=new double[matrix.length];
        for (int i = 0; i < out.length; i++) {
            out[i]=matrix[i][column];
        }
        if (!isMatrixConsistent()){
            return null;
        }
        return out;
    }
    private boolean isMatrixConsistent(){
        boolean itsAllZeros=true;
        for (int i = 0; i < matrix[0].length-1; i++) {
            if (matrix[matrix.length-1][i]!=0){
                itsAllZeros=false;
            }
        }
        if(itsAllZeros&&matrix[matrix.length-1][matrix[0].length-1]!=0){
            return false;
        } else {
            return true;
        }
    }
    public double getDeterminant(double[][] matrix) {
        double determinant = 0;
        if (matrix.length == 2) {
            return matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0];
        }
        double[][][] m = new double[matrix.length][matrix.length - 1][matrix.length - 1];
        int row = findTheVectorWithMostZeros().getX();
        int column = findTheVectorWithMostZeros().getY();
        if (row==-1){
            row=0;
        }
        if (column==-1){
            column=0;
        }
        if (column>row){
            matrix=Matrix.makeTransposeMatrix("temp",new Matrix("temp",matrix)).matrix;
            row=column;
            column=0;
        }
        int x = 0, y = 0;
        for (int i = 0; i < m.length; i++) {
            if (matrix[row][column]==0){
                column++;
                continue;
            }

            for (int j = 0; j < matrix.length; j++) {
                if (row == j) {
                    x = j - 1;
                    continue;
                } else {
                    if (j==0){
                        x=0;
                    }else{
                        x++;
                    }
                    if (x == matrix.length - 1) {
                        x = 0;
                    }
                }

                for (int k = 0; k < matrix.length; k++) {
                    if (column == k) {
                        y = k - 1;
                        continue;
                    } else {
                        if (k==0){
                            y=0;
                        }else{
                            y++;
                        }
                        if (y == matrix.length - 1) {
                            y = 0;
                        }
                    }
                    m[i][x][y] = matrix[j][k];
                }
            }
            column++;
        }
        matrixIn3d(m);
        row = findTheVectorWithMostZeros().getX();
        column = findTheVectorWithMostZeros().getY();
        if (row==-1){
            row=0;
        }
        if (column==-1){
            column=0;
        }
        if (column>row){
            //matrix=Matrix.makeTransposeMatrix("temp",new Matrix("temp",matrix)).matrix;
            row=column;
        }
        for (int i = 0; i < m.length; i++) {
                determinant += Math.pow(-1,row+i)*matrix[row][i] * getDeterminant(m[i]);

        }
        return determinant;
    }

    private void matrixIn3d(double[][][] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                for (int k = 0; k < a[0].length; k++) {
                    System.out.print(String.format("%4.1f ",a[i][j][k]));
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static Matrix multiplyMatrix(String name, Matrix a, Matrix b) {
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

            return new Matrix(name, matrixO);
        }
        return null;
    }

    public static Matrix powerOfMatrix(String name, Matrix a, int power) {
        Matrix temp = new Matrix(name, a.matrix);
        for (int i = 0; i < power - 1; i++) {
            temp = Matrix.multiplyMatrix(name, temp, a);
        }
        return temp;
    }

    public static double[][] createIdentityMatrix(int length) {
        double[][] output = new double[length][length];
        for (int i = 0; i < length; i++) {
            output[i][i] = 1;
        }
        return output;
    }

    public static Matrix addIdentityMatrix(String name, Matrix a) {
        double[][] matrixO = new double[a.matrix.length][2 * a.matrix.length];
        double[][] identityMatrix = createIdentityMatrix(a.matrix.length);
        for (int i = 0; i < matrixO.length; i++) {
            for (int j = 0; j < matrixO[0].length; j++) {
                if (j < a.matrix.length) {
                    matrixO[i][j] = a.matrix[i][j];
                } else {
                    matrixO[i][j] = identityMatrix[i][j - a.matrix.length];
                }
            }
        }
        Matrix temp = new Matrix(name, matrixO);
        return temp;
    }

    public static Matrix makeTransposeMatrix(String name, Matrix a) {
        double[][] temp = new double[a.matrix[0].length][a.matrix.length];
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < temp[0].length; j++) {
                temp[i][j] = a.matrix[j][i];
            }
        }
        Matrix output = new Matrix(name, temp);
        return output;
    }

    public static Matrix invertedMatrix(String name, Matrix a) {
        Matrix input = Matrix.addIdentityMatrix(name, a);
        input.gausJordan(false);
        double[][] temp = new double[a.matrix.length][a.matrix.length];
        for (int i = 0; i < a.matrix.length; i++) {
            for (int j = 0; j < a.matrix.length; j++) {
                temp[i][j] = input.matrix[i][j + a.matrix.length];
            }
        }
        Matrix output = new Matrix(name, temp);
        return output;
    }

    public boolean areMatricesSame(Matrix a,Matrix b){
        for (int i = 0; i < a.matrix.length; i++) {
            for (int j = 0; j < a.matrix[0].length; j++) {
                if(a.matrix[i][j]!=b.matrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    //</editor-fold>


    //<editor-fold desc="GAUS-JORDAN">
    public void gaus(boolean printOutput) {
        makeEmptyRowsOnBottom();
        if (printOutput){
            System.out.println(this);
        }
        for (int i = 0; i < matrix.length - numberOfEmptyRows; i++) {
            Matrix temp=this;
            makeLeadingOneOnTop(i);
            if (!areMatricesSame(temp,this)&&printOutput){
                System.out.println(this);
            }
            makeLeadingOnesOne(i);
            if (!areMatricesSame(temp,this)&&printOutput){
                System.out.println(this);
            }
            makeNonZerosBeneathLeadingOneZeros(i,printOutput);

            makeEmptyRowsOnBottom();
        }
        if (printOutput){
            System.out.println(this);
        }
    }

    public void gausJordan(boolean printOutput) {
        gaus(printOutput);
        for (int i = matrix.length - 1 - numberOfEmptyRows; i >= 0; i--) {
            makeNonZerosAboveLeadingOneZero(i,printOutput);
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

    private void makeNonZerosBeneathLeadingOneZeros(int subMatrixLevel,boolean printOutput) {
        while (findNonZerosBeneathLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY()) != Integer.MAX_VALUE) {
            rowOperation(
                    subMatrixLevel,
                    -matrix[findNonZerosBeneathLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY())][findLeadingOne(subMatrixLevel).getY()],
                    findNonZerosBeneathLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY())
            );
            if (printOutput){
                System.out.println(this);
            }
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


    public void makeNonZerosAboveLeadingOneZero(int subMatrixLevel,boolean printOutput) {
        while (findNonZerosAboveLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY()) != Integer.MAX_VALUE) {
            rowOperation(
                    subMatrixLevel,
                    -matrix[findNonZerosAboveLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY())][findLeadingOne(subMatrixLevel).getY()],
                    findNonZerosAboveLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY())
            );
            if (printOutput){
                System.out.println(this);
            }
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
                if(matrix[i][j]==-0.0){
                    matrix[i][j]=0;
                }
                stringBuilder.append("\t" + String.format("%4.1f ", matrix[i][j]));
            }

            stringBuilder.append("\n");
        }
        stringBuilder.append("}\n");

        return stringBuilder.toString();
    }
}
