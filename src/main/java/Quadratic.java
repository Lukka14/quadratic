import java.util.ArrayList;
import java.util.List;

public class Quadratic {
    private double a;
    private double b;
    private double c;
    private Root x1;
    private Root x2;

    public Quadratic(double a, double b, double c) {
        setA(a);
        this.b = b;
        this.c = c;
    }
    public Quadratic(){}

    // get the discriminant
    public double getD(){
        return b*b-4*a*c;
    }

    // we have 2 roots, we need list to return both of them
    public List<Root> getRoots(){
        List<Root> roots=new ArrayList<>(2);
        double D=getD();
        if(x1==null) x1=new Root();
        if(x2==null) x2=new Root();
        if(D<0){
            x1.setRe(-b/2);
            x1.setIm(Math.sqrt( Math.abs(D) )/2);

            x2.setRe(-b/2);
            x2.setIm(-Math.sqrt( Math.abs(D) )/2);
        }
        else {
            x1.setRe((-b+Math.sqrt(D))/(2*a));
            x2.setRe((-b-Math.sqrt(D))/(2*a));
        }
        roots.add(x1);
        roots.add(x2);
        return roots;
    }

    // 'a' must not be 0
    public void setA(double a){
        if(a==0) throw new IllegalArgumentException("'a' must not be 0.");
        this.a=a;
    }

    public double getA(){
        return a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    // overriding default methods

    @Override
    public String toString(){
        if(x1==null || x2==null) getRoots();
        String withIm="a="+a+"\tb="+b+"\tc="+c+"\nRoots:  x1="+x1.getRe()+"+"+x1.getIm()+"i  x2="+x2.getRe()+""+x2.getIm()+"i";
        String withoutIm="a="+a+"\tb="+b+"\tc="+c+"\nRoots:  x1="+x1.getRe()+"  x2="+x2.getRe();
        return getD()<0 ? withIm : withoutIm;
    }

    @Override
    public boolean equals(Object o) {
        if(x1==null || x2==null) getRoots();
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quadratic quadratic = (Quadratic) o;

        if (Double.compare(quadratic.a, a) != 0) return false;
        if (Double.compare(quadratic.b, b) != 0) return false;
        if (Double.compare(quadratic.c, c) != 0) return false;
        if (!x1.equals(quadratic.x1)) return false;
        return x2.equals(quadratic.x2);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        if(x1==null || x2==null) getRoots();
        temp = Double.doubleToLongBits(a);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(b);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(c);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + x1.hashCode();
        result = 31 * result + x2.hashCode();
        return result;
    }
}
