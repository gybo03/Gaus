package AnaliticGeometry;

import Matrix.Matrix;
import Vector.Vector;

public class Line {
    private final String name;
    private final Point point;
    private final Vector vector;

    public Line(String name, Point point, Vector vector) {
        this.name = name;
        this.point = point;
        this.vector = vector;
    }

    public Line(String name, Point point1, Point point2) {
        this.name = name;
        this.point = point1;
        this.vector = new Vector("",point1.getCoordinates(),point2.getCoordinates());
        this.vector.setTerminalCoordinates(this.vector.getCoordinatesRelativeToOrigin());
        this.vector.setInitialCoordinates(new double[point1.getCoordinates().length]);
    }

    private static double[][] fillMatrix(Line line1, Line line2){
        double[][] matrix=new double[line1.point.getCoordinates().length][line1.point.getCoordinates().length];
        for (int i = 0; i < line1.point.getCoordinates().length; i++) {
            matrix[i][0]=line1.vector.getCoordinatesRelativeToOrigin()[i];
        }
        for (int i = 0; i < line1.point.getCoordinates().length; i++) {
            matrix[i][1]-=line2.vector.getCoordinatesRelativeToOrigin()[i];
        }
        for (int i = 0; i < line1.point.getCoordinates().length; i++) {
            matrix[i][2]-=line1.point.getCoordinates()[i]-line2.point.getCoordinates()[i];
        }
        return matrix;
    }
    public static Point commonPointBetweenTwoLines(Line line1,Line line2){
        Matrix m=new Matrix("",fillMatrix(line1, line2));
        m.gausJordan(false);
        Point out=new Point("Common Point Between "+line1.name+" and "+line2.name,new double[line1.point.getCoordinates().length]);
        if (m.returnColumn(m.getNumOfColumns()-1)==null){
            return null;
        }else{
            out.setCoordinates(m.returnColumn(m.getNumOfColumns()-1));
        }
        return out;
    }
    public boolean isPointPartOfLine(Point p){
        boolean itIs=true;
        double t=(p.getCoordinates()[0]-point.getCoordinates()[0])/vector.getCoordinatesRelativeToOrigin()[0];
        if (Double.isNaN(t)){
            t=(p.getCoordinates()[1]-point.getCoordinates()[1])/vector.getCoordinatesRelativeToOrigin()[1];;
        }
        for (int i = 1; i < p.getCoordinates().length; i++) {
            if (t!=(p.getCoordinates()[i]-point.getCoordinates()[i])/vector.getCoordinatesRelativeToOrigin()[i]){
                if(Double.isNaN((p.getCoordinates()[i]-point.getCoordinates()[i])/vector.getCoordinatesRelativeToOrigin()[i])){
                    continue;
                }
                itIs=false;
            }
        }
        return itIs;
    }

    public static double distanceBetweenPointAndLine(Point point, Line line){
        return Math.sin(Vector.angleBetweenTwoVectors(line.vector,new Vector("",line.point.getCoordinates(), point.getCoordinates()))*0.0174532925)*new Vector("",line.point.getCoordinates(), point.getCoordinates()).getMagnitude();
    }

    public Point getPoint() {
        return point;
    }

    public Vector getVector() {
        return vector;
    }

    public String getName() {
        return name;
    }

    public static double distanceBetweenTwoLines(Line line1, Line line2){
        if (line2.vector.getCoordinatesRelativeToOrigin().length==2){
            return Line.distanceBetweenPointAndLine(line2.point,line1);
        }
        Vector vector1=Vector.vectorProduct(line1.vector,line2.vector);
        if (vector1.getMagnitude()==0){
            return Line.distanceBetweenPointAndLine(line2.point,line1);
        }
        vector1=Vector.multiplyVectorByScalar("",Vector.vectorProduct(line1.vector,line2.vector),1/vector1.getMagnitude());
        return Math.abs(Vector.dotProduct(vector1,new Vector("",line1.point.getCoordinates(),line2.point.getCoordinates())));
    }

    public String toString(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(name+":\n");
        for (int i = 0; i < point.getCoordinates().length; i++) {
            if (vector.getCoordinatesRelativeToOrigin()[i]<0){
                stringBuilder.append(Character.toString(88+i)+" = "+point.getCoordinates()[i]+" - "+Math.abs(vector.getCoordinatesRelativeToOrigin()[i]) +"t\n");
            }else{
                stringBuilder.append(Character.toString(88+i)+" = "+point.getCoordinates()[i]+" + "+vector.getCoordinatesRelativeToOrigin()[i]+"t\n");
            }

        }
        return stringBuilder.toString();
    }
}
