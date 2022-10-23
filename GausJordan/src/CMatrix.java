import java.util.Arrays;

public class CMatrix {
    public double[][] matrix;
    public String name;

    public CMatrix(int x, int y, String name) {
        matrix = new double[x][y];
        this.name = name;
    }

    public CMatrix(double[][] matrix, String name) {
        this.matrix = matrix;
        this.name = name;
    }

    public Cordinates findLeadingOne(int row) {
        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[row][i] != 0) {
                return new Cordinates(row, i);
            }
        }
        return new Cordinates(-1, 0);
    }

    public void Gaus() {
        for (int i = 0; i < matrix.length; i++) {
            makeLeadingOneOnTop(i);
            makeLeadingOnesOne(i);
            if (i > 0 && matrix[i][0] > 0) {
                //rowOperation();
            }
        }
    }

    public void rowOperation(int sourceRow, double coefficient, int destinationRow) {
        for (int i = 0; i < matrix[0].length; i++) {
            matrix[destinationRow][i] += matrix[sourceRow][i] * coefficient;
        }
    }
    //<editor-fold desc="THESE WORK">
    public void makeLeadingOneOnTop(int subMatrixLevel) {
        switchRows(findLeftMostLeadingOne(subMatrixLevel).getY(), subMatrixLevel);
    }

    public Cordinates findLeftMostLeadingOne(int subMatrixLevel) {
        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = subMatrixLevel; j < matrix.length; j++) {
                if (matrix[j][i] != 0) {
                    return new Cordinates(i, j);
                }
            }
        }
        return new Cordinates(-1, 0);
    }

    public void makeLeadingOnesOne(int subMatrixLevel) {
        if (matrix[subMatrixLevel][0] != 1) {
            double coefficient = 1 / matrix[subMatrixLevel][0];
            for (int i = subMatrixLevel; i < matrix[0].length; i++) {
                matrix[subMatrixLevel][i] = matrix[subMatrixLevel][i] * coefficient;
            }
        }
    }

    public void switchRows(int source, int destination) {
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
