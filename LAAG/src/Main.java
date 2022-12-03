import Vector.Vector;

public class Main {
    public static void main(String[] args) {
        double[] iC = {0, 0, 0};
        double[] tC1 = {2, -1, 3};
        double[] tC2 = {4, -1, 2};

        double[] tC3 = {1, 0};
        double[] tC4 = {0, -1};

        Vector v = new Vector("v", iC, tC1);
        System.out.println(v);
        Vector w = new Vector("w", iC, tC2);
        System.out.println(w);
        Vector a= Vector.projectionOnOtherVector("a",v,w);
        System.out.println(a);

        /*
        System.out.println(Math.cos(Math.PI));Vector w = Vector.multiplyVectorByScalar("-v",v,-1);
        System.out.println(w);
        Vector add = Vector.addVectors("add", v, w);
        System.out.println(add);
        Vector sub = Vector.subtractVectors("sub", v, w);
        System.out.println(sub);*/
    }
}
