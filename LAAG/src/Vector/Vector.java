package Vector;

public class Vector {

    private final String name;
    private final int nSpace;
    private final double[] initialCoordinates;
    private final double[] terminalCoordinates;
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

    public double[] getCoordinatesRelativeToOrigin() {
        for (int i = 0; i < nSpace; i++) {
            coordinatesRelativeToOrigin[i] = terminalCoordinates[i] - initialCoordinates[i];
        }
        return coordinatesRelativeToOrigin;
    }

    public static Vector projectionOnOtherVector(String name, Vector v, Vector w) {
        return new Vector(name,new double[v.nSpace],Vector.multiplyVectorByScalar(name,w,Vector.dotProduct(v,w)/w.getMagnitude()/w.getMagnitude()).coordinatesRelativeToOrigin);
    }

    public double getMagnitude() {
        double temp = 0;
        for (int i = 0; i < nSpace; i++) {
            temp += Math.pow(initialCoordinates[i] + terminalCoordinates[i], 2);
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

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + "= ");
        stringBuilder.append('(');
        for (int i = 0; i < nSpace; i++) {
            stringBuilder.append(String.format("%.2f", coordinatesRelativeToOrigin[i]));
            if (i + 1 != nSpace) {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append(")\n");
        stringBuilder.append("magnitude = " + String.format("%.2f", magnitude));
        return stringBuilder.toString();
    }
}
