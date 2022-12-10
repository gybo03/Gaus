package Vector;

import AnaliticGeometry.Point;
import Matrix.Matrix;

public class Vector {

    private final String name;
    private final int nSpace;
    private  double[] initialCoordinates;
    private  double[] terminalCoordinates;
    private double[] coordinatesRelativeToOrigin;
    private double magnitude;

    public Vector(String name, double[] initialCoordinates, double[] terminalCoordinates) {
        this.name = name;
        this.initialCoordinates = initialCoordinates;
        this.terminalCoordinates = terminalCoordinates;

        nSpace = initialCoordinates.length;
        coordinatesRelativeToOrigin = new double[nSpace];
        coordinatesRelativeToOrigin = getCoordinatesRelativeToOrigin();
        magnitude = getMagnitude();
    }
    public Vector(String name, Point initialCoordinates, Point terminalCoordinates) {
        this.name = name;
        this.initialCoordinates = initialCoordinates.getCoordinates();
        this.terminalCoordinates = terminalCoordinates.getCoordinates();

        nSpace = initialCoordinates.getCoordinates().length;
        coordinatesRelativeToOrigin = new double[nSpace];
        coordinatesRelativeToOrigin = getCoordinatesRelativeToOrigin();
        magnitude = getMagnitude();
    }

    public double[] getCoordinatesRelativeToOrigin() {
        for (int i = 0; i < nSpace; i++) {
            coordinatesRelativeToOrigin[i] = terminalCoordinates[i] - initialCoordinates[i];
        }
        return coordinatesRelativeToOrigin;
    }

    public static Vector projectionOnOtherVector(String name, Vector v, Vector w) {
        return new Vector(name, new double[v.nSpace], Vector.multiplyVectorByScalar(name, w, Vector.dotProduct(v, w) / w.getMagnitude() / w.getMagnitude()).coordinatesRelativeToOrigin);
    }

    public double getMagnitude() {
        double temp = 0;
        for (int i = 0; i < nSpace; i++) {
            temp += Math.pow(coordinatesRelativeToOrigin[i], 2);
        }
        return Math.sqrt(temp);
    }

    public static Vector addVectors(String name, Vector v, Vector w) {
        double[] tC = new double[v.nSpace];
        for (int i = 0; i < v.nSpace; i++) {
            tC[i] = v.coordinatesRelativeToOrigin[i] + w.coordinatesRelativeToOrigin[i];
        }
        return new Vector(name, new double[v.nSpace], tC);
    }

    public static Vector subtractVectors(String name, Vector v, Vector w) {
        double[] tC = new double[v.nSpace];
        for (int i = 0; i < v.nSpace; i++) {
            tC[i] = v.coordinatesRelativeToOrigin[i] - w.coordinatesRelativeToOrigin[i];
        }
        return new Vector(name, new double[v.nSpace], tC);
    }

    public static Vector multiplyVectorByScalar(String name, Vector v, double scalar) {
        Vector w = new Vector(name, new double[v.nSpace], new double[v.nSpace]);
        for (int i = 0; i < v.nSpace; i++) {
            w.terminalCoordinates[i] = v.coordinatesRelativeToOrigin[i] * scalar;
        }
        w.coordinatesRelativeToOrigin = w.getCoordinatesRelativeToOrigin();
        w.magnitude = w.getMagnitude();
        return w;
    }

    public static double dotProduct(Vector v, Vector w, int angle) {
        return Math.round(v.magnitude * w.magnitude * Math.cos(angle * 0.0174532925));
    }

    public static double dotProduct(Vector v, Vector w) {
        double dotP = 0;
        for (int i = 0; i < v.nSpace; i++) {
            dotP += v.coordinatesRelativeToOrigin[i] * w.coordinatesRelativeToOrigin[i];
        }
        return dotP;
    }

    public static int angleBetweenTwoVectors(Vector v, Vector w) {
        return (int) Math.round(Math.acos(Vector.dotProduct(v, w) / (v.getMagnitude() * w.getMagnitude())) * 57.2957795131);

    }

    public double[] getTerminalCoordinates() {
        return terminalCoordinates;
    }

    public void setCoordinatesRelativeToOrigin(double[] coordinatesRelativeToOrigin) {
        this.coordinatesRelativeToOrigin = coordinatesRelativeToOrigin;
    }

    public void setInitialCoordinates(double[] initialCoordinates) {
        this.initialCoordinates = initialCoordinates;
    }

    public void setTerminalCoordinates(double[] terminalCoordinates) {
        this.terminalCoordinates = terminalCoordinates;
    }

    public static Vector vectorProduct(Vector v, Vector w) {
        Vector n=new Vector("n",v.initialCoordinates,new double[v.nSpace]);
        n.terminalCoordinates[0]=v.coordinatesRelativeToOrigin[1]*w.coordinatesRelativeToOrigin[2]-v.coordinatesRelativeToOrigin[2]*w.coordinatesRelativeToOrigin[1];
        n.terminalCoordinates[1]-=v.coordinatesRelativeToOrigin[0]*w.coordinatesRelativeToOrigin[2]-v.coordinatesRelativeToOrigin[2]*w.coordinatesRelativeToOrigin[0];
        n.terminalCoordinates[2]=v.coordinatesRelativeToOrigin[0]*w.coordinatesRelativeToOrigin[1]-v.coordinatesRelativeToOrigin[1]*w.coordinatesRelativeToOrigin[0];
        n.getCoordinatesRelativeToOrigin();
        n.magnitude=n.getMagnitude();
        return n;
    }

    private static double[][] fillMatrix(Vector[] arr) {
        double[][] out = new double[arr[0].nSpace][arr[0].nSpace];
        for (int i = 0; i < arr[0].nSpace; i++) {
            out[0][i] = Math.pow(-1, i);
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < arr[0].nSpace; j++) {
                out[i][j] = arr[i].coordinatesRelativeToOrigin[j];
            }
        }
        return out;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + "= ");
        stringBuilder.append('(');
        for (int i = 0; i < nSpace; i++) {
            stringBuilder.append(String.format("%.1f", coordinatesRelativeToOrigin[i]));
            if (i + 1 != nSpace) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(")\n");
        stringBuilder.append("magnitude = " + String.format("%.2f", magnitude));
        return stringBuilder.toString();
    }
}
