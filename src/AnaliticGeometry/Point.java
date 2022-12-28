package AnaliticGeometry;

import Vector.Vector;

public class Point {
    private double[] coordinates;

    private final String name;

    public Point( String name,double[] coordinates) {
        this.coordinates = coordinates;
        this.name = name;
    }

    public static double distanceFromTwoPoints(Point p,Point q){
        Vector vector= new Vector("", p.coordinates, q.coordinates);
        return new Vector("",new double[vector.getCoordinatesRelativeToOrigin().length], vector.getCoordinatesRelativeToOrigin()).getMagnitude();
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public double[] getCoordinates() {
        return coordinates;
    }


    public String toString(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(name);
        stringBuilder.append(" (");
        for (int i = 0; i < coordinates.length-1; i++) {
            stringBuilder.append(coordinates[i]+",");
        }
        stringBuilder.append(coordinates[coordinates.length-1]+")\n");
        return stringBuilder.toString();
    }
}
