import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public void printToPlot(double array[][], String path){
        DecimalFormat df=new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        StringBuilder writeToFile=new StringBuilder("");
        for(int t=0; t<array.length; t++) {
            for (int i = 0; i < array[t].length; i++) {
                writeToFile.append(df.format(Constants.xStep * i))
                        .append(" ").append(df.format(Constants.timeStep * t)).append(" ")
                        .append(df.format(array[t][i])).append(" ");
            }
            writeToFile.append("\n");
        }
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(path));
            bufferedWriter.write(writeToFile.toString());
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
