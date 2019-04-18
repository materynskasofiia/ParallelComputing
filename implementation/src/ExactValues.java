/**
 * Визначення точного розв'язку
 */
public class ExactValues {
    static double [][] matrix= new double[Constants.numberOfTimeIterations+1][Constants.numberOfXIterations+1];

    static void calculate(){
        View view=new View();
        for(int t=0; t<Constants.numberOfTimeIterations+1; t++){
            for(int i=0; i<Constants.numberOfXIterations+1; i++){
                matrix[t][i]= Math.pow(Constants.c0 * Constants.xStep * i -
                        Constants.a * Constants.c1 * t * Constants.timeStep + Constants.c2, -1);
            }
        }
        //view.printArray(matrix);
        view.printToPlot(matrix,Constants.filePathExact);
    }
}
