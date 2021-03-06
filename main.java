import java.io.*;
import java.util.*;

class Item {
  String name;
  int price;

  public Item(String name, int price) {
    this.name = name;
    this.price = price;
  }

  public String toString() { 
      return this.name + ": " + this.price;
  }
}

public class main {
  public static void main(String[] args) throws Exception {
    FileInputStream file=new FileInputStream("sample_input.txt");       
    Scanner sc=new Scanner(file);
    int number_of_employees = Integer.parseInt(sc.nextLine().split(": ")[1]);
    sc.nextLine(); sc.nextLine(); sc.nextLine();

    ArrayList<Item> goodiesdata = new ArrayList<Item>();

    while(sc.hasNextLine())  
    {
      String now[] = sc.nextLine().split(": ");
      goodiesdata.add(new Item(now[0], Integer.parseInt(now[1])));
    }
    sc.close();

    Collections.sort(goodiesdata, new Comparator<Item>(){
      public int compare(Item a, Item b) { 
        return a.price - b.price; 
      } 
    });
    int min_diff = goodiesdata.get(goodiesdata.size()-1).price;
    int min_index = 0;
    for(int i=0;i<goodiesdata.size()-number_of_employees+1;i++) {
      int diff = goodiesdata.get(number_of_employees+i-1).price-goodiesdata.get(i).price;
      if(diff<=min_diff) {
        min_diff = diff;
        min_index = i;
      }
    }
    FileWriter output = new FileWriter("sample_output.txt");
    output.write("The goodies selected for distribution are:\n\n");
    for(int i=min_index;i<min_index + number_of_employees; i++) {
      output.write(goodiesdata.get(i).toString() + "\n");
    }
    output.write("\nAnd the difference between the chosen goodie with highest price and the lowest price is " + min_diff);
	  output.close();
  }
}