import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Bezier {
	private ArrayList<Punkt> punkt = new ArrayList<Punkt>();
	public int ile;
	public void wczytaj()
	{
		try 
		{
			Scanner read = new Scanner(new File("punkty.txt"));
			double x, y;
			ile = Integer.parseInt(read.nextLine());
			for(int i=0; i<ile*4; i++)
			{
				x = Double.parseDouble(read.next());
				y = Double.parseDouble(read.next());
				punkt.add(new Punkt(x, y));
			}
			read.close();
		} catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
	}
	public void wyswietl() {
		for(int i=0; i<11; i++) {
			System.out.println(punkt.get(i).getX());
		}
	}
	public int silnia(int i){
		if (i < 1)
			return 1;
		else
			return i * silnia(i - 1);
	}
	public double dwumian(int n, int k)
	{
		if(k==0 || n==k) {
			return 1;
		}
		else {
			double licznik = silnia(n);
			double mianownik = silnia(k)*silnia(n-k);		
			return licznik/mianownik;
		}
	}
	public void licz() {

		double wx = 0;
		double wy = 0;
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("zapis.txt"));
			for(int krzywa=0; krzywa<ile; krzywa++) {
				for(double t=0; t<=1; t+=0.001) {
					for(int i = 0; i<=3; i++) {
						wx += dwumian(3, i)*Math.pow((1-t), 3 - i)*Math.pow(t, i)*punkt.get(krzywa*4+i).getX();
						wy += dwumian(3, i)*Math.pow((1-t), 3 - i)*Math.pow(t, i)*punkt.get(krzywa*4+i).getY();

					}
					writer.write(wx+" "+wy+System.lineSeparator());
					wx=0;
					wy=0;
				}
			}
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void test() {
		double wx = dwumian(11, 3)*Math.pow((1-0.03),11-3)*Math.pow(0.03, 3)*punkt.get(3).getX();
		System.out.println(wx);
	}
	
}
