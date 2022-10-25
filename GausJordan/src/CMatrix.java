public class CMatrix {
    private double[][] matrix;
    private String name;

    public CMatrix(int x, int y, String name) {
        matrix = new double[x][y];
        this.name = name;
    }

    public CMatrix(double[][] matrix, String name) {
        this.matrix = matrix;
        this.name = name;
    }
    //<editor-fold desc="THESE WORK">
    public void gaus() {
        for (int i = 0; i < matrix.length; i++) {
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
    private int findNonZerosBeneathLeadingOne(int subMatrixLevel,int coloumOfLeadingOne){
        for (int i = subMatrixLevel+1; i < matrix.length; i++) {
            if (matrix[i][coloumOfLeadingOne]!=0){
                return i;
            }
        }
        return 0;
    }

    private void makeNonZerosBeneathLeadingOneZeros(int subMatrixLevel){
        while(findNonZerosBeneathLeadingOne(subMatrixLevel,findLeadingOne(subMatrixLevel).getY())!=0){
            rowOperation(subMatrixLevel,
                    -matrix[findNonZerosBeneathLeadingOne(subMatrixLevel,findLeadingOne(subMatrixLevel).getY())][findLeadingOne(subMatrixLevel).getY()],
                    findNonZerosBeneathLeadingOne(subMatrixLevel,findLeadingOne(subMatrixLevel).getY())
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
