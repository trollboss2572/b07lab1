public class Polynomial
{
  double [] poly;
  int [] degree;
  public Polynomial()
  {
    poly = new double[100];
    degree = new double[100];
    poly[0] = 0;
    degree[0] = 0;
  }
  public Polynomial(double [] poly_arr; double [] degree_arr)
  {
    int size = poly_arr.length;
    poly = new double[size];
    int size2 = degree_arr.length;
    for(int i=0; i<size;i++)
    {
      if (i>=size2)

    }
      poly[i] = poly_arr[i];

  }
  public Polynomial add(Polynomial second_poly)
  {
    int size = Math.max(second_poly.poly.length, poly.length);
    double [] updated_size = new double[size];
    if (poly.length <= size)
    {
      int UwU = poly.length;
      for(int i = 0; i < size; i++)
      {
        if (i < UwU)
          updated_size[i] = poly[i];
        else
          updated_size[i] = 0;
      }
    }
    else
    {
      int UwU = second_poly.poly.length;
      for(int i = 0; i < size; i++)
      {
        if (i < UwU)
          updated_size[i] = second_poly.poly[i];
        else
          updated_size[i] = 0;
      }
    }
    double [] new_poly = new double[size];
    for(int i=0; i<size; i++)
      new_poly[i] = updated_size[i] + second_poly.poly[i];
    Polynomial return_poly = new Polynomial(new_poly);
    return (return_poly);
  }
  public double evaluate(double val_for_x)
  {
    int size = poly.length;
    double sum = 0;
    for (int i=0; i<size; i++)
      sum += (poly[i]*Math.pow(val_for_x, i));
      return (sum);
  }
  public boolean hasRoot(double x)
  {
    if (evaluate(x) == 0)
      return (true);
    else
      return (false);
  }
}
