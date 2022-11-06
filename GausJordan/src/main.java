public class main {
    public static void main(String[] args) {
        //double[][] mA = {{0, 2, -7, -17},{1, 1, 2, 9} , {0, 3, -11, -27}};
        double[][] mA = {{0, 0, -2, 0, 7, 12}, {2, 4, -10, 6, 12, 28}, {2, 4, -5, 6, -5, -1}};
        CMatrix a = new CMatrix(mA, "A");

        a.gausJordan();

        /*a.makeNonZerosAboveLeadingOneZero(2);
        System.out.println(a);
        System.out.println(a.findNonZerosAboveLeadingOne(2,2));
        a.makeNonZerosAboveLeadingOneZero(2);
        System.out.println(a);*/

        /*a.makeLeadingOneOnTop(0);
        System.out.println(a);
        a.makeLeadingOnesOne(0);
        System.out.println(a);
        a.makeNonZerosBeneathLeadingOneZeros(0);
        System.out.println(a);
        System.out.println(a.findNonZerosBeneathLeadingOne(0,0));
        System.out.println(a.findLeftMostLeadingOne(0).getX()+" "+a.findLeftMostLeadingOne(0).getY());*/

    }
}
