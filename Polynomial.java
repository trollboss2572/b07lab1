import java.util.Scanner;
import java.io.*;

public class Polynomial
{
  double [] poly;
  int [] degree;
  public Polynomial()
  {
    poly = new double[100];
    degree = new int[100];
    poly[0] = 0;
    degree[0] = 0;
  }
  public Polynomial(double [] poly_arr, int [] degree_arr)
  {
    int size = poly_arr.length;
    poly = new double[size];
    degree = new int[size];
    for(int i=0; i<size;i++)
    {
      poly[i] = poly_arr[i];
      degree[i] = degree_arr[i];
    }
  }
  public Polynomial(File file)
  {
    try
    {
      Scanner reader = new Scanner(file);
      String polynomial = reader.nextLine();
      String[] array_of_polys = polynomial.split("\\+");
      poly = new double[100];
      degree = new int[100];
      int index_place = 1;
      String double_str = "";
      int x_checker_index = 1;
      if (polynomial.charAt(0) == '-')
      {
        while(x_checker_index < array_of_polys.length && (Character.isDigit(polynomial.charAt(x_checker_index)) || polynomial.charAt(x_checker_index) == '.'))
        {
          double_str = double_str.concat(Character.toString(polynomial.charAt(x_checker_index)));
          x_checker_index++;
        }
        poly[0] = -Double.parseDouble(double_str);
        if ((x_checker_index < polynomial.length() && polynomial.charAt(x_checker_index) == 'x'))
          degree[0] = Integer.parseInt(Character.toString(polynomial.charAt(x_checker_index + 1)));
        else
          degree[0] = 0;
      }
      else
      {
        x_checker_index = 0;
        while(x_checker_index < polynomial.length() && (Character.isDigit(polynomial.charAt(x_checker_index)) || polynomial.charAt(x_checker_index) == '.'))
        {
          double_str = double_str.concat(Character.toString(polynomial.charAt(x_checker_index)));
          x_checker_index++;
        }
        poly[0] = Double.parseDouble(double_str);
        if ((x_checker_index < polynomial.length() && polynomial.charAt(x_checker_index) == 'x'))
          degree[0] = Integer.parseInt(Character.toString(polynomial.charAt(x_checker_index + 1)));
        else
          degree[0] = 0;
      }
      for(int i = 1; i < array_of_polys.length; i++)
      {
        double_str = "";
        x_checker_index = 0;
        while(x_checker_index < array_of_polys[i].length() && (Character.isDigit(array_of_polys[i].charAt(x_checker_index)) || array_of_polys[i].charAt(x_checker_index) == '.'))
        {
          double_str = double_str.concat(Character.toString(array_of_polys[i].charAt(x_checker_index)));
          x_checker_index++;
        }
        poly[index_place] = Double.parseDouble(double_str);
        if ((x_checker_index < array_of_polys[i].length() && array_of_polys[i].charAt(x_checker_index) == 'x'))
          degree[index_place] = Integer.parseInt(Character.toString(array_of_polys[i].charAt(x_checker_index + 1)));
        else
          degree[index_place] = 0;
        index_place++;
      }
      array_of_polys = polynomial.split("\\-");
      for(int i = 1; i < array_of_polys.length; i++)
      {
        x_checker_index = 0;
        double_str = "";
        while((x_checker_index < array_of_polys[i].length()) && (Character.isDigit(array_of_polys[i].charAt(x_checker_index)) || array_of_polys[i].charAt(x_checker_index) == '.'))
        {
          double_str = double_str.concat(Character.toString(array_of_polys[i].charAt(x_checker_index)));
          x_checker_index++;
        }
        poly[index_place] = -Double.parseDouble(double_str);
        if ((x_checker_index < array_of_polys[i].length() && array_of_polys[i].charAt(x_checker_index) == 'x'))
          degree[index_place] = Integer.parseInt(Character.toString(array_of_polys[i].charAt(x_checker_index + 1)));
        else
          degree[index_place] = 0;
        index_place++;
      }
      for(int i = 0; i < poly.length; i++)
      {
        if (poly[i] == 0)
        {
          degree[i] = 0;
        }
      }
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
    }
  }
  public Polynomial add(Polynomial second_poly)
  {
    int size = second_poly.poly.length + poly.length;
    double [] updated_size = new double[size];
    int [] updated_exp = new int[size];
    int index_of_0 = 0;
    if (poly.length <= size)
    {
      for(int i = 0; i < poly.length; i++)
      {
        for(int x = 0; x < second_poly.poly.length; x++)
        {
          if(degree[i] == second_poly.degree[x])
          {
            updated_size[index_of_0] = poly[i] + second_poly.poly[x];
            updated_exp[index_of_0] = degree[i];
            index_of_0++;
            break;
          }
        }
      }
    }
    for(int i = 0; i < second_poly.degree.length; i++)
    {
      boolean already_summed = false;
      for(int x: updated_exp)
        if (second_poly.degree[i] == x)
        {
            already_summed = true;
            break;
        }
      if (!already_summed)
      {
        updated_size[index_of_0] = second_poly.poly[i];
        updated_exp[index_of_0] = second_poly.degree[i];
        index_of_0++;
      }
    }
    for(int i = 0; i < degree.length; i++)
    {
      boolean already_summed = false;
      for(int x: updated_exp)
        if (degree[i] == x)
        {
            already_summed = true;
            break;
        }
      if (!already_summed)
      {
        updated_size[index_of_0] = poly[i];
        updated_exp[index_of_0] = degree[i];
        index_of_0++;
      }
    }
    Polynomial return_poly = new Polynomial(updated_size, updated_exp);
    return return_poly;
  }
  public double evaluate(double val_for_x)
  {
    int size = poly.length;
    double sum = 0;
    for (int i=0; i<size; i++)
      sum += (poly[i]*Math.pow(val_for_x, degree[i]));
      return (sum);
  }
  public boolean hasRoot(double x)
  {
    if (evaluate(x) == 0)
      return (true);
    else
      return (false);
  }
  public Polynomial multiply(Polynomial p2)
  {
    int size = p2.poly.length * poly.length;
    double [] result_poly = new double[size];
    int [] result_degree = new int[size];
    int index_of_0 = 0;
    for(int i = 0; i < poly.length; i++)
    {
      for(int x = 0; x < p2.poly.length; x++)
      {
        result_poly[index_of_0] = poly[i] * p2.poly[x];
        result_degree[index_of_0] = degree[i] + p2.degree[x];
        index_of_0++;
      }
    }
    for(int i = 0; i < result_degree.length; i++)
    {
      for(int x = 0; x < result_degree.length; x++)
      {
        if (result_degree[i] == result_degree[x] && x != i)
        {
          result_poly[i] = result_poly[i] + result_poly[x];
          result_poly[x] = 0;
        }
      }
    }
    for (int i = 0; i < result_poly.length; i++)
    {
      if(result_poly[i] == 0)
        result_degree[i] = 0;
    }
    Polynomial return_poly = new Polynomial(result_poly, result_degree);
    return (return_poly);
  }
  public void saveToFile(String filepath)
  {
    try
    {
      String poly_as_string = "";
      if (poly[0] != 0)
        poly_as_string = poly_as_string.concat(Double.toString(poly[0]));
      for(int i = 1; i < poly.length; i++)
      {
        if (poly[i] > 0)
          poly_as_string = poly_as_string.concat("+");
        else if (poly[i] == 0)
          continue;
        poly_as_string = poly_as_string.concat(Double.toString(poly[i]));
        if (degree[i] != 0)
        {
          poly_as_string = poly_as_string.concat("x");
          poly_as_string = poly_as_string.concat(Integer.toString(degree[i]));
        }
      }
      FileWriter file = new FileWriter(filepath, false);
      file.write(poly_as_string);
      file.close();
    }
    catch (IOException e)
    {
      System.out.println("fuckey wuckey");
    }
  }
}
