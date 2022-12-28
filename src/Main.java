import AnaliticGeometry.*;
import Matrix.Matrix;
import Vector.Vector;



public class Main {
    public static void main(String[] args) {

        //<editor-fold desc="commonLineBetweenTwoPlanes">
        /*double[] zero = {0, 0, 0};
        double[] a = {0, -9, 0};
        double[] b = {0, 0, -4};
        double[] c = {2, -1, 0};
        double[] d = {1, -1, -1};



        Point point0 = new Point("0", zero);
        Point point1 = new Point("a", a);
        Point point2 = new Point("b", b);
        Point point3 = new Point("c", c);
        Point point4 = new Point("d", d);

        Vector vector1 = new Vector("vector1", point0, point3);
        Vector vector2 = new Vector("vector2", point0, point4);

        Plane plane1 = new Plane("plane1", point1, vector1);
        Plane plane2 = new Plane("plane2", point2, vector2);

        System.out.println(plane1);
        System.out.println(plane2);

        System.out.println(Plane.commonLineBetweenTwoPlanes(plane1,plane2));
        *///</editor-fold>
        //<editor-fold desc="distanceBetweenTwoPlanes">
        /*double[] zero = {0, 0, 0};
        double[] a = {0, 0, -1.5};
        double[] b = {1, 2, -2};
        double[] c = {0, 0, -1.75};
        double[] d = {2, 4, -4};



        Point point0 = new Point("0", zero);
        Point point1 = new Point("a", a);
        Point point2 = new Point("b", b);
        Point point3 = new Point("c", c);
        Point point4 = new Point("d", d);

        Vector vector1 = new Vector("vector1", point0, point2);
        Vector vector2 = new Vector("vector2", point0, point4);

        Plane plane1 = new Plane("plane1", point1, vector1);
        Plane plane2 = new Plane("plane2", point3, vector2);

        System.out.println(Plane.distanceBetweenTwoPlanes(plane2, plane1));
        *///</editor-fold>
        //<editor-fold desc="commonPointBetweenLineAndPlane">
        /*double[] zero = {0, 0, 0};
        double[] a = {7, 7, 1};
        double[] b = {2, -3, 6};
        double[] c={2,6,-9};
        double[] d={3,4,-4};

        Point point0=new Point("0",zero);
        Point point1 = new Point("a", a);
        Point point2=new Point("b",b);
        Point point3=new Point("c",c);
        Point point4=new Point("d",d);

        Vector vector1 = new Vector("vector1", point0, point2);
        Vector vector2 = new Vector("vector2", point0, point4);

        Line line1=new Line("line1",point3,vector2);

        Plane plane1= new Plane("plane1",point1,vector1);

        System.out.println(plane1);

        System.out.println(line1);

        System.out.println(Plane.commonPointBetweenLineAndPlane(line1,plane1));
        *///</editor-fold>
        //<editor-fold desc="distanceBetweenPointAndPlane">
        /*double[] zero = {0, 0, 0};
        double[] a = {7, 7, 1};
        double[] b = {2, -3, 6};
        double[] c={1,-4,-3};

        Point point0=new Point("0",zero);
        Point point1 = new Point("a", a);
        Point point2=new Point("b",b);
        Point point3=new Point("c",c);

        Vector vector1 = new Vector("vector1", point0, point2);

        Plane plane1= new Plane("plane1",point1,vector1);

        System.out.println(plane1);

        System.out.println(Plane.distanceBetweenPointAndPlane(point3,plane1));
        *///</editor-fold>
        //<editor-fold desc="plane.toSting">
        /*double[] zero = {0, 0, 0};
        double[] a = {2, 6, -9};
        double[] b = {3, 4, -4};

        Point point1 = new Point("a", a);
        Point point2=new Point("b",b);

        Vector vector1 = new Vector("vector1", zero, b);

        Plane plane1= new Plane("plane1",point1,vector1);

        System.out.println(plane1);
        *///</editor-fold>
        //<editor-fold desc="commonPointBetweenTwoLines">
        /*double[] p0 = {0, 0, 0};
        double[] p1 = {1, -4, 8};
        double[] p2 = {5, 1, 8};
        double[] v1 = {2, 1, -2};
        double[] v2 = {1, -1, -3};

        Point point1=new Point("point1",p1);
        Point point2=new Point("point2",p2);
        Vector vector1=new Vector("vector1",p0,v1);
        Vector vector2=new Vector("vector2",p0,v2);
        System.out.println(vector1);
        System.out.println(vector2);
        Line line1=new Line("line1",point1,vector1);
        Line line2=new Line("line2",point2,vector2);
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(Line.commonPointBetweenTwoLines(line1,line2));*/
        //</editor-fold>
        //<editor-fold desc="distanceBetweenTwoLines">
        /*double[] zero = {0, 0, 0};
        double[] a = {2, 6, -9};
        double[] b = {3, 4, -4};
        double[] c = {-1, -2, 3};
        double[] d = {2, -6, 1};

        Point point1 = new Point("a", a);
        Point point2 = new Point("c", c);

        Vector vector1 = new Vector("vector1", zero, b);
        Vector vector2 = new Vector("vector2", zero, d);

        Line line1 = new Line("line1", point1, vector1);
        Line line2 = new Line("line2", point2, vector2);

        System.out.println(Line.distanceBetweenTwoLines(line1, line2));*/
        //</editor-fold>

        double[][] mA = {{1, -2,0,0,3}, {2, -5 ,-3 ,-2 ,6}, {0, 5, 15, 10, 0}, {2, 6, 18, 8, 6}};
        Matrix m=new Matrix("m",mA);
        //Matrix m1=Matrix.makeTransposeMatrix("m1",m);
        //System.out.println(m);
        m.gaus(true);


        /*System.out.println(m1);
        m1.gaus(false);
        System.out.println(m1);*/
        /*


        Point p=new Point("p",tC1);
        System.out.println(p);
        Point q=new Point("q",tC2);
        System.out.println(q);
        System.out.println(Point.distanceFromTwoPoints(p,q));

        System.out.println(line.isPointPartOfLine(point2));


        //double[][] mA = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        //double[][] mA = {{1, 0, 0 , -1}, {3, 1, 2, 2}, {1, 0, -2, 1}, {2, 0, 0, 1}};
        //double[][] mA = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        //double[][] mA = {{1, 2, 3}, {-4, 5, 6}, {7, -8, 9}};
        //double[][] mA = {{1, 2}, {3, 4}};

        Vector v = new Vector("v", tC2, tC1);
        System.out.println(v);

        Matrix a = new Matrix("A", mA);
        System.out.println(a);

        System.out.println(a.findTheVectorWithMostZeros());
        System.out.println(a.getDeterminant(mA));


        Vector a= Vector.projectionOnOtherVector("a",v,w);
        System.out.println(a);

        System.out.println(Math.cos(Math.PI));Vector w = Vector.multiplyVectorByScalar("-v",v,-1);
        System.out.println(w);
        Vector add = Vector.addVectors("add", v, w);
        System.out.println(add);
        Vector sub = Vector.subtractVectors("sub", v, w);
        System.out.println(sub);
        */
    }
}
