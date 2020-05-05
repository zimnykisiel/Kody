
import java.io.*;
import java.util.Scanner;

public class Bezier
{
    private Scanner scan;
    private FileWriter write;
    public Bezier() throws IOException {
        scan = new Scanner(new File("dane.txt"));
        write = new FileWriter("pkt.txt");
    }

    public int Dwumian( int n, int k )
    {
        int  wynik = 1;
        int i;
        for(i = 1; i <= k; i++) {
            wynik = wynik * ( n - i + 1 ) / i;
        }
        return wynik;
    }

    public void licz() throws IOException {
        int platy = scan.nextInt();
        Punkt[][] punkty = new Punkt[4][4];
        int[] dwumian = new int[4];
        double pktx = 0.0;
        double pkty = 0.0;
        double pktz = 0.0;
        double x=0;
        double y=0;
        double z=0;
        int w1 = 0;
        int w2 = 0;
        String wynik = "";
        write.flush();
        write.write("x y z" + System.lineSeparator());
        for(int i=0; i<=3; i++)
        {
            dwumian[i] = Dwumian(3, i);
        }
        for(int k=0; k<platy; k++)
        {
            w1 = Integer.parseInt(scan.next());
            w2 = Integer.parseInt(scan.next());
            for(int p=0; p<=w1; p++) {
                for(int o=0; o<=w2; o++) {
                    x = Double.parseDouble(scan.next());
                    y = Double.parseDouble(scan.next());
                    z = Double.parseDouble(scan.next());
                    punkty[p][o] = new Punkt(x, y, z);
                }
            }
            for (double u = 0.0; u <= 1; u += 0.01) {
                for(double v=0.0; v<=1; v+=0.01) {
                    for (int p = 0; p <= 3; p++) {
                        for(int q=0; q<=3; q++) {
                            pktx += dwumian[p] * Math.pow(u, p) * Math.pow(1 - u, 3 - p) * (dwumian[q] * Math.pow(v, q) * Math.pow(1 - v, 3 - q) * punkty[p][q].x);
                            pkty += dwumian[p] * Math.pow(u, p) * Math.pow(1 - u, 3 - p) * (dwumian[q] * Math.pow(v, q) * Math.pow(1 - v, 3 - q) * punkty[p][q].y);
                            pktz += dwumian[p] * Math.pow(u, p) * Math.pow(1 - u, 3 - p) * (dwumian[q] * Math.pow(v, q) * Math.pow(1 - v, 3 - q) * punkty[p][q].z);
                        }
                    }
                    wynik = pktx + " " + pkty + " " + pktz + System.lineSeparator();
                    pktx=0;
                    pkty=0;
                    pktz=0;
                    write.write(wynik);
                }
            }
        }
        write.close();
    }

    public static void main(String[] args) throws IOException {
        Bezier b = new Bezier();
        b.licz();
    }
}