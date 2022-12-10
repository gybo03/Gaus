package AnaliticGeometry;

import Vector.Vector;

import java.sql.SQLOutput;

public class Plane {

    private final String name;
    private final Point point;
    private final Vector nVector;

    public Plane(String name, Point point, Vector nVector) {
        this.name = name;
        this.point = point;
        this.nVector = nVector;
    }

    public static double distanceBetweenPointAndPlane(Point point,Plane plane){
        return Vector.projectionOnOtherVector("",new Vector("",plane.point,point),plane.nVector).getMagnitude();
    }

    public static double distanceBetweenLineAndPlane(Line line,Plane plane){
        if (Vector.dotProduct(line.getVector(),plane.nVector)==0){
            return distanceBetweenPointAndPlane(line.getPoint(),plane);
        }
        return 0;
    }
    public static double distanceBetweenTwoPlanes(Plane plane1,Plane plane2){
        Point point0=new Point("",new double[plane1.nVector.getCoordinatesRelativeToOrigin().length]);
        Line line1=new Line("line1",point0,plane1.nVector);
        Line line2=new Line("line2",point0,plane2.nVector);

        Point point1=commonPointBetweenLineAndPlane(line1,plane1);
        System.out.println(point1);
        Point point2=commonPointBetweenLineAndPlane(line1,plane2);
        System.out.println(point2);

        if (line1.isPointPartOfLine(point2)){
            return Point.distanceFromTwoPoints(point1,point2);
        }
        return 0;
    }
    public static Point commonPointBetweenLineAndPlane(Line line,Plane plane){
        double[] temp=new double[line.getPoint().getCoordinates().length];
        double[] t=new double[2];
        t[0]=Vector.dotProduct(line.getVector(),plane.nVector);
        //t[1]=Vector.dotProduct(plane.nVector,new Vector("",new double[temp.length],line.getPoint().getCoordinates()));
        for (int i = 0; i < temp.length; i++) {
            t[1]+=plane.nVector.getCoordinatesRelativeToOrigin()[i]*plane.point.getCoordinates()[i];
        }
        double te=t[1]/t[0];
        for (int i = 0; i < temp.length; i++) {
            temp[i]=line.getPoint().getCoordinates()[i]+line.getVector().getCoordinatesRelativeToOrigin()[i]*te;
        }
        return new Point("Common point between "+line.getName()+" and "+plane.name+":",temp);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(name+":");
        double sum=0;
        for (int i = 0; i < nVector.getCoordinatesRelativeToOrigin().length; i++) {
            if (nVector.getCoordinatesRelativeToOrigin()[i]>=0){
                stringBuilder.append(" +"+nVector.getCoordinatesRelativeToOrigin()[i]+""+Character.toString(88+i));
            }else{
                stringBuilder.append(" "+nVector.getCoordinatesRelativeToOrigin()[i]+""+Character.toString(88+i));
            }
            sum+=nVector.getCoordinatesRelativeToOrigin()[i]*point.getCoordinates()[i];
        }
        if (sum>=0){
            stringBuilder.append(" "+-sum+" = 0\n");
        }else{
            stringBuilder.append(" "+sum+" = 0\n");
        }
        return stringBuilder.toString();
    }
}
