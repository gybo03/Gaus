import Vector.Vector;

public class Main {
    public static void main(String[] args) {
        int[] iC = {0, 0, 0};
        int[] tC1 = {1, 1, 1};
        int[] tC2 = {0, 0, 1};

        int[] tC3 = {1, 0};
        int[] tC4 = {0, -1};

        Vector v = new Vector("v", iC, tC1);
        System.out.println(v);
        Vector w = new Vector("w", iC, tC2);
        System.out.println(w);
        System.out.println(Vector.dotProduct(v, w));
        System.out.println(v.getMagnitude() * w.getMagnitude());
        System.out.println(Vector.angleBetweenTwoVectors(v, w));
        System.out.println(Math.acos(Vector.dotProduct(v, w) / (v.getMagnitude() * w.getMagnitude())) * 57.2957795131);

        /*
        System.out.println(Math.cos(Math.PI));Vector w = Vector.multiplyVectorByScalar("-v",v,-1);
        System.out.println(w);
        Vector add = Vector.addVectors("add", v, w);
        System.out.println(add);
        Vector sub = Vector.subtractVectors("sub", v, w);
        System.out.println(sub);*/
    }
}
