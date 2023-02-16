package AnaliticGeometry;

import Matrix.Matrix;
import Vector.Vector;

public class Line extends Point{
    private final Vector vector;

    public Line(Line line){
        super(line.getPoint());
        this.vector=line.getVector();
    }

    public Line(String name, Point point, Vector vector) {
        super(new Point(name, point.getCoordinates()));
        this.vector = vector;
    }

    public Line(String name, Point point1, Point point2) {
        super(point1);
        this.vector = new Vector("",point1.getCoordinates(),point2.getCoordinates());
        this.vector.setTerminalCoordinates(this.vector.getCoordinatesRelativeToOrigin());
        this.vector.setInitialCoordinates(new double[point1.getCoordinates().length]);
    }

    private static double[][] fillMatrix(Line line1, Line line2){
        int length=line1.getCoordinates().length;
        double[][] matrix=new double[length-1][length];

        for (int i = 0; i < length-1; i++) {
            matrix[i][0]=line1.vector.getCoordinatesRelativeToOrigin()[i];
            matrix[i][1]=-line2.vector.getCoordinatesRelativeToOrigin()[i];
            matrix[i][2]-=line1.getCoordinates()[i]-line2.getCoordinates()[i];
        }

        return matrix;
    }
    public static Point commonPointBetweenTwoLines(Line line1,Line line2){

        Matrix m=new Matrix("",fillMatrix(line1, line2));
        m.gausJordan(false);

        Point out=new Point("Common Point Between "+line1.getName()+" and "+line2.getName(),new double[line1.getCoordinates().length]);
        if (m.returnColumn(m.getNumOfColumns()-1)==null){
            return null;
        }else{
            double coord[]=new double[3];
            for (int i = 0; i < 3; i++) {
                coord[i]=line1.getPoint().getCoordinates()[i]+line1.getVector().getCoordinatesRelativeToOrigin()[i]*m.returnColumn(m.getNumOfColumns()-1)[0];
            }
            out.setCoordinates(coord);
        }
        return out;
    }
    public boolean isPointPartOfLine(Point p){
        boolean itIs=true;
        double t=(p.getCoordinates()[0]-getCoordinates()[0])/vector.getCoordinatesRelativeToOrigin()[0];
        if (Double.isNaN(t)){
            t=(p.getCoordinates()[1]-getCoordinates()[1])/vector.getCoordinatesRelativeToOrigin()[1];;
        }
        for (int i = 1; i < p.getCoordinates().length; i++) {
            if (t!=(p.getCoordinates()[i]-getCoordinates()[i])/vector.getCoordinatesRelativeToOrigin()[i]){
                if(Double.isNaN((p.getCoordinates()[i]-getCoordinates()[i])/vector.getCoordinatesRelativeToOrigin()[i])){
                    continue;
                }
                itIs=false;
            }
        }
        return itIs;
    }

    public static double distanceBetweenPointAndLine(Point point, Line line){
        return Math.sin(Vector.angleBetweenTwoVectors(line.vector,new Vector("",line.getCoordinates(), point.getCoordinates()))*0.0174532925)*new Vector("",line.getCoordinates(), point.getCoordinates()).getMagnitude();
    }



    public Vector getVector() {
        return vector;
    }


    public static double distanceBetweenTwoLines(Line line1, Line line2){
        if (line2.vector.getCoordinatesRelativeToOrigin().length==2){
            return Line.distanceBetweenPointAndLine(line2.getPoint(),line1);
        }
        Vector vector1=Vector.vectorProduct(line1.vector,line2.vector);
        if (vector1.getMagnitude()==0){
            return Line.distanceBetweenPointAndLine(line2.getPoint(),line1);
        }
        vector1=Vector.multiplyVectorByScalar("",Vector.vectorProduct(line1.vector,line2.vector),1/vector1.getMagnitude());
        return Math.abs(Vector.dotProduct(vector1,new Vector("",line1.getCoordinates(),line2.getCoordinates())));
    }

    public String toString(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(getName()+":\n");
        for (int i = 0; i < getCoordinates().length; i++) {
            if (vector.getCoordinatesRelativeToOrigin()[i]<0){
                stringBuilder.append(Character.toString(88+i)+" = "+getCoordinates()[i]+" - "+Math.abs(vector.getCoordinatesRelativeToOrigin()[i]) +"t\n");
            }else{
                stringBuilder.append(Character.toString(88+i)+" = "+getCoordinates()[i]+" + "+vector.getCoordinatesRelativeToOrigin()[i]+"t\n");
            }

        }
        return stringBuilder.toString();
    }
}
