package AnaliticGeometry;

import Matrix.Matrix;
import Vector.Vector;

public class Plane {

    private final String name;
    private final Point point;
    private final Vector nVector;

    public Plane(String name, Point point, Vector nVector) {
        this.name = name;
        this.point = point;
        this.nVector = nVector;
    }

    public static double distanceBetweenPointAndPlane(Point point, Plane plane) {
        return Vector.projectionOnOtherVector("", new Vector("", plane.point, point), plane.nVector).getMagnitude();
    }
    public static Line commonLineBetweenTwoPlanes(Plane plane1,Plane plane2){
        Matrix m=fillMatrix(plane1,plane2);

        double[] point=new double[m.getNumOfRows()+1];

        for (int i = 0; i < point.length-1; i++) {
            point[i]=m.getMatrix()[i][m.getNumOfColumns()-1];
        }

        point[point.length-1]=0;

        Vector vector1=Vector.vectorProduct(plane1.nVector,plane2.nVector);
        System.out.println(vector1);

        Point point1=new Point("point",point);
        System.out.println(point1);

        return new Line("Common line between "+plane1.name+" and "+plane2.name,point1,vector1);
    }

    //fills a matrix with 2 equations of 2 planes
    private static Matrix fillMatrix(Plane plane1, Plane plane2) {
        double[][] temp=new double[2][plane1.nVector.getCoordinatesRelativeToOrigin().length+1];
        double d1=Vector.dotProduct(plane1.nVector, new Vector("", new double[temp[0].length-1], plane1.point.getCoordinates()));
        double d2=Vector.dotProduct(plane2.nVector, new Vector("", new double[temp[0].length-1], plane2.point.getCoordinates()));
        for (int i = 0; i < temp[0].length-1; i++) {
            temp[0][i]=plane1.nVector.getCoordinatesRelativeToOrigin()[i];
            temp[1][i]=plane2.nVector.getCoordinatesRelativeToOrigin()[i];
        }
        temp[0][temp[0].length-1]=d1;
        temp[1][temp[0].length-1]=d2;
        Matrix m=new Matrix("",temp);
        m.gausJordan(false);
        return m;
    }

    public static double distanceBetweenLineAndPlane(Line line, Plane plane) {
        if (Vector.dotProduct(line.getVector(), plane.nVector) == 0) {
            return distanceBetweenPointAndPlane(line.getPoint(), plane);
        }
        return 0;
    }

    public static double distanceBetweenTwoPlanes(Plane plane1, Plane plane2) {
        System.out.println(plane1);
        System.out.println(plane2);
        Point point0 = new Point("", new double[plane1.nVector.getCoordinatesRelativeToOrigin().length]);
        Line line1 = new Line("line1", point0, plane1.nVector);
        return Point.distanceFromTwoPoints(commonPointBetweenLineAndPlane(line1, plane1), commonPointBetweenLineAndPlane(line1, plane2));
    }

    public static Point commonPointBetweenLineAndPlane(Line line, Plane plane) {
        double[] temp = new double[line.getPoint().getCoordinates().length];

        double t = Vector.dotProduct(line.getVector(), plane.nVector);
        double d = Vector.dotProduct(plane.nVector, new Vector("", new double[temp.length], plane.point.getCoordinates()));

        for (int i = 0; i < temp.length; i++) {
            temp[i] = line.getPoint().getCoordinates()[i] + line.getVector().getCoordinatesRelativeToOrigin()[i] * (d/t);
        }
        return new Point("Common point between " + line.getName() + " and " + plane.name + ":", temp);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name + ":");
        double sum = 0;
        for (int i = 0; i < nVector.getCoordinatesRelativeToOrigin().length; i++) {
            if (nVector.getCoordinatesRelativeToOrigin()[i] >= 0) {
                stringBuilder.append(" +" + nVector.getCoordinatesRelativeToOrigin()[i] + "" + Character.toString(88 + i));
            } else {
                stringBuilder.append(" " + nVector.getCoordinatesRelativeToOrigin()[i] + "" + Character.toString(88 + i));
            }
        }
        double d=Vector.dotProduct(nVector, new Vector("", new double[nVector.getCoordinatesRelativeToOrigin().length], point.getCoordinates()));
        if (d >= 0) {
            stringBuilder.append(" -" + d + " = 0\n");
        } else {
            d=Math.abs(d);
            stringBuilder.append(" +" + d + " = 0\n");
        }
        return stringBuilder.toString();
    }
}
