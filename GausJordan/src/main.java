public class main {
    public static void main(String[] args) {
        double[][] mA = {{0, 3, -2, 0, 2, 0, 0}, {5, 6, -5, -2, 4, -3, 0}, {0, 0, 5, 10, 0, 15, 0}, {0, 6, 0, 8, 4, 18, 0}};
        CMatrix a = new CMatrix(mA, "A");
        System.out.println(a);
        a.makeLeadingOneOnTop(0);
        System.out.println(a);
        a.makeLeadingOnesOne(0);
        System.out.println(a);
        //System.out.println(a.findLeftMostLeadingOne(0).getX()+" "+a.findLeftMostLeadingOne(0).getY());

    }
}
