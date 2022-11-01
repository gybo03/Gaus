public class main {
    public static void main(String[] args) {
        //double[][] mA = {{0, 2, -7, -17},{1, 1, 2, 9} , {0, 3, -11, -27}};
        //double[][] mA = {{0, 0, -2, 0, 7, 12},{0, 0, 0, 0, 0, 0}, {2, 4, -10, 6, 12, 28}, {2, 4, -5, 6, -5, -1}};
    /*    double[][] mA = {{1, 2, 4}, {2, 6, 0}};
        */
        double[][] mB = {{3, -2}, {-1, 1}};
        double[][] mA = {{3, -2}, {-1, 1}};
        CMatrix a = new CMatrix(mA, "A");
        double[][] mC = a.multiplyMatrix(mB);
        mC = a.multiplyMatrix(a.multiplyMatrix(mA));

        CMatrix c = new CMatrix(mC,"C");
        System.out.println(a);
        System.out.println(c);
    }
}
