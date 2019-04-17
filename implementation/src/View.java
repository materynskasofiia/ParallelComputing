import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class View {
    public void print(String string){
        System.out.print(string);
    }

    public void printFormatted(String format, double value){
        DecimalFormat df=new DecimalFormat(format,new DecimalFormatSymbols(Locale.US));
        System.out.print(df.format(value));
    }

    public void printArray(double [][] array){
        for (int t = 0; t < array.length ; t++) {
            for (int i = 0; i < array[t].length ; i++) {
                printFormatted("#.##",array[t][i]);
                print(" ");
            }
            print("\n");
        }
    }

    public void printToPlot(double array[][]){
        DecimalFormat df=new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        for(int t=0; t<array.length; t++) {
            for (int i = 0; i < array[t].length; i++) {
                print("{" + df.format(Constants.xStep * i) + "," + df.format(Constants.timeStep * t) + ","
                        +df.format(array[t][i]) + "},");
            }
            print("\n");
        }
    }
}
