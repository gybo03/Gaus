public class CMatrix {
    private double[][] matrix;
    private String name;

    private int numberOfEmptyRows;

    public CMatrix(int x, int y, String name) {
        matrix = new double[x][y];
        this.name = name;
        numberOfEmptyRows = 0;
    }

    public CMatrix(double[][] matrix, String name) {
        this.matrix = matrix;
        this.name = name;
        numberOfEmptyRows = 0;
    }

    public void addMatrix(double[][] matrixI) {
        if (matrix.length == matrixI.length && matrix[0].length == matrixI[0].length) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] += matrixI[i][j];
                }
            }
        }
    }

    public void subMatrix(double[][] matrixI) {
        if (matrix.length == matrixI.length && matrix[0].length == matrixI[0].length) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] -= matrixI[i][j];
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


    public double[][] multiplyMatrix(double[][] matrixI) {
        if (matrix[0].length == matrixI.length) {

            double[][] matrixO = new double[matrix.length][matrixI[0].length];

            for (int i = 0; i < matrixO.length; i++) {
                for (int j = 0; j < matrixO[0].length; j++) {
                    int entry = 0;
                    for (int k = 0; k < matrix[0].length; k++) {
                        entry += matrix[i][k] * matrixI[k][j];
                    }
                    matrixO[i][j] = entry;
                }
            }

            return matrixO;
        }
        return null;
    }

    public CMatrix powerOfMatrix(int power) {
        CMatrix temp=new CMatrix(this.matrix,"temp");



        return temp;
    }

    //<editor-fold desc="GAUS-JORDAN">
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

    public void gausJordan() {
        gaus();
        for (int i = matrix.length - 1 - numberOfEmptyRows; i >= 0; i--) {
            makeNonZerosAboveLeadingOneZero(i);
        }
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

    private void rowOperation(int sourceRow, double coefficient, int destinationRow) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[destinationRow][i] += matrix[sourceRow][i] * coefficient;
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

    private void makeNonZerosBeneathLeadingOneZeros(int subMatrixLevel) {
        while (findNonZerosBeneathLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY()) != Integer.MAX_VALUE) {
            rowOperation(
                    subMatrixLevel,
                    -matrix[findNonZerosBeneathLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY())][findLeadingOne(subMatrixLevel).getY()],
                    findNonZerosBeneathLeadingOne(subMatrixLevel, findLeadingOne(subMatrixLevel).getY())
            );
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

    private void switchRows(int source, int destination) {
        double[] temp = matrix[source];
        matrix[source] = matrix[destination];
        matrix[destination] = temp;
    }

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
    //</editor-fold>
}
