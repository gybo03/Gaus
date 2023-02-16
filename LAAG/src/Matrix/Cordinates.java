package Matrix;

public class Cordinates {
    private int x,y;

    public Cordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String toString(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("(x,y)=("+x+","+y+")");
        return stringBuilder.toString();
    }
}
