package AnaliticGeometry;

import Matrix.Matrix;
import Vector.Vector;

public class Plane extends Line{


    public Plane(String name, Point point, Vector nVector) {
        super(new Line(name,point,nVector));
    }

    public static double distanceBetweenPointAndPlane(Point point, Plane plane) {
        return Vector.projectionOnOtherVector("", new Vector("", plane.getPoint(), point), plane.getVector()).getMagnitude();
    }
    public static Line commonLineBetweenTwoPlanes(Plane plane1,Plane plane2){
        Matrix m=fillMatrix(plane1,plane2);

        double[] point=new double[m.getNumOfRows()+1];

        for (int i = 0; i < point.length-1; i++) {
            point[i]=m.getMatrix()[i][m.getNumOfColumns()-1];
        }

        point[point.length-1]=0;

        Vector vector1=Vector.vectorProduct(plane1.getVector(),plane2.getVector());
        //System.out.println(vector1);

        Point point1=new Point(plane1.getName()+" ∩ "+plane2.getName(),point);
        //System.out.println(point1);

        return new Line("Common line between "+plane1.getName()+" and "+plane2.getName(),point1,vector1);
    }

    //fills a matrix with 2 equations of 2 planes
    private static Matrix fillMatrix(Plane plane1, Plane plane2) {
        double[][] temp=new double[2][plane1.getVector().getCoordinatesRelativeToOrigin().length+1];
        double d1=Vector.dotProduct(plane1.getVector(), new Vector("", new double[temp[0].length-1], plane1.getPoint().getCoordinates()));
        double d2=Vector.dotProduct(plane2.getVector(), new Vector("", new double[temp[0].length-1], plane2.getPoint().getCoordinates()));
        for (int i = 0; i < temp[0].length-1; i++) {
            temp[0][i]=plane1.getVector().getCoordinatesRelativeToOrigin()[i];
            temp[1][i]=plane2.getVector().getCoordinatesRelativeToOrigin()[i];
        }
        temp[0][temp[0].length-1]=d1;
        temp[1][temp[0].length-1]=d2;
        Matrix m=new Matrix("",temp);
        m.gausJordan(false);
        return m;
    }

    public static double distanceBetweenLineAndPlane(Line line, Plane plane) {
        if (Vector.dotProduct(line.getVector(), plane.getVector()) == 0) {
            return distanceBetweenPointAndPlane(line.getPoint(), plane);
        }
        return 0;
    }

    public static double distanceBetweenTwoPlanes(Plane plane1, Plane plane2) {
        System.out.println(plane1);
        System.out.println(plane2);
        Point point0 = new Point("", new double[plane1.getVector().getCoordinatesRelativeToOrigin().length]);
        Line line1 = new Line("line1", point0, plane1.getVector());
        return Point.distanceFromTwoPoints(commonPointBetweenLineAndPlane(line1, plane1), commonPointBetweenLineAndPlane(line1, plane2));
    }

    public static Point commonPointBetweenLineAndPlane(Line line, Plane plane) {
        double[] temp = new double[line.getPoint().getCoordinates().length];

        double t = Vector.dotProduct(line.getVector(), plane.getVector());
        double d = Vector.dotProduct(plane.getVector(), new Vector("", new double[temp.length], plane.getPoint().getCoordinates()));

        for (int i = 0; i < temp.length; i++) {
            temp[i] = line.getPoint().getCoordinates()[i] + line.getVector().getCoordinatesRelativeToOrigin()[i] * (d/t);
        }
        return new Point("Common point between " + line.getName() + " and " + plane.getName() + ":", temp);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getName() + ":");
        double sum = 0;
        for (int i = 0; i < getVector().getCoordinatesRelativeToOrigin().length; i++) {
            if (getVector().getCoordinatesRelativeToOrigin()[i] >= 0) {
                stringBuilder.append(" +" + getVector().getCoordinatesRelativeToOrigin()[i] + "" + Character.toString(88 + i));
            } else {
                stringBuilder.append(" " + getVector().getCoordinatesRelativeToOrigin()[i] + "" + Character.toString(88 + i));
            }
        }
        double d=Vector.dotProduct(getVector(), new Vector("", new double[getVector().getCoordinatesRelativeToOrigin().length], getPoint().getCoordinates()));
        if (d >= 0) {
            stringBuilder.append(" -" + d + " = 0\n");
        } else {
            d=Math.abs(d);
            stringBuilder.append(" +" + d + " = 0\n");
        }
        return stringBuilder.toString();
    }
}
