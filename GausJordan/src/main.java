public class main {
    public static void main(String[] args) {
        double[][] mA = {{1, 1, 2, 9}, {0, 2, -7, -17}, {0, 3, -11, -27}};
        CMatrix a = new CMatrix(mA, "A");
        a.gaus();
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
