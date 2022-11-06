public class main {
    public static void main(String[] args) {
        //double[][] mA = {{0, 2, -7, -17},{1, 1, 2, 9} , {0, 3, -11, -27}};
        //double[][] mA = {{0, 0, -2, 0, 7, 12},{0, 0, 0, 0, 0, 0}, {2, 4, -10, 6, 12, 28}, {2, 4, -5, 6, -5, -1}};
        /*    double[][] mA = {{1, 2, 4}, {2, 6, 0}};
         */
        double[][] mB = {{11, -8}, {-4, 3}};
        CMatrix b = new CMatrix(mB, "B");
        double[][] mA = {{3, -2}, {-1, 1}};
        CMatrix a = new CMatrix(mA, "A");
        CMatrix c = CMatrix.powerOfMatrix(a,"C",3);
        System.out.println(a);
        System.out.println(c);
    }
}
