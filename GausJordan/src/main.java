public class main {
    public static void main(String[] args) {
        //double[][] mA = {{0, 2, -7, -17},{1, 1, 2, 9} , {0, 3, -11, -27}};
        //double[][] mA = {{0, 0, -2, 0, 7, 12},{0, 0, 0, 0, 0, 0}, {2, 4, -10, 6, 12, 28}, {2, 4, -5, 6, -5, -1}};
        /*    double[][] mA = {{1, 2, 4}, {2, 6, 0}};
         */

        double[][] mA = {{1, 2, 3}, {2, 5, 3}, {1, 0, 8}};
        CMatrix a = new CMatrix(mA, "A");
        CMatrix b= CMatrix.invertedMatrix(a,"b");

        System.out.println(a);
        System.out.println(b);


    }
}
