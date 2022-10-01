import java.io.*;
public class Driver {
 public static void main(String [] args) {
  Polynomial p = new Polynomial();
  System.out.println(p.evaluate(3));
  double [] c1 = {6,0,0,5};
  int [] d1 = {0,3,1,4};
  Polynomial p1 = new Polynomial(c1, d1);
  double [] c2 = {0,-2,0,0,-9};
  int [] d2 = {5, 2, 0, 3, 6};
  Polynomial p2 = new Polynomial(c2, d2);
  File file = new File("/Users/caspe/OneDrive/Documents/Shit.txt");
  Polynomial p3 = new Polynomial (file);
  for(int i = 0; i < p3.degree.length; i++)
    System.out.println(p3.poly[i] + ", " + p3.degree[i]);
  Polynomial s = p1.add(p2);
  Polynomial s2 = p1.multiply(p2);
  System.out.println("p3(2) = " + p3.evaluate(2));
  System.out.println("s(0.1) = " + s.evaluate(2));
  System.out.println("p1*p2(1) = " + s2.evaluate(1));
  p1.saveToFile("/Users/caspe/OneDrive/Documents/Shit.txt");
  if(s.hasRoot(1))
   System.out.println("1 is a root of s");
  else
   System.out.println("1 is not a root of s");
 }
}
