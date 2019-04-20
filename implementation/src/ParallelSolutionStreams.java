import java.util.stream.IntStream;

public class ParallelSolutionStreams {
    static double[][] matrix = new double[Constants.numberOfTimeIterations+1][Constants.numberOfXIterations+1];
    private static int finalT;
    void calculate(){
        View view=new View();
        //початкові умови t=0
        for (int i = 0; i < Constants.numberOfXIterations+1; i++) {
            matrix[0][i] = Math.pow(Constants.c0 * Constants.xStep * i + Constants.c2, -1);
        }
        //граничні умови x=0, x=1
        for (int t = 1; t < Constants.numberOfTimeIterations+1; t++) {
            matrix[t][0] = Math.pow(-Constants.a * Constants.c1 * t * Constants.timeStep + Constants.c2, -1);
            matrix[t][Constants.numberOfXIterations] = Math.pow(Constants.c0 -
                    Constants.a * Constants.c1 * t * Constants.timeStep + Constants.c2, -1);
        }
        long beginTime=System.currentTimeMillis(); //System.currentTimeMillis() System.nanoTime()
        for (int t = 0; t < Constants.numberOfTimeIterations ; t++) {
            finalT = t;
            IntStream.range(1,Constants.numberOfXIterations).parallel().forEach(a->
                    matrix[finalT +1][a]=Constants.a * Constants.timeStep * (2 * matrix[finalT][a]
                    * (matrix[finalT][a - 1] - 2 * matrix[finalT][a] + matrix[finalT][a + 1])
                    - Constants.xStep * Math.pow(matrix[finalT][a + 1] - matrix[finalT][a - 1], 2))
                    / (2 * Math.pow(Constants.xStep * matrix[finalT][a], 2)) + matrix[finalT][a]);
        }
        long endTime=System.currentTimeMillis();
        view.print("Parallel: "+(endTime-beginTime)+" ms\n");
        ExactValues.calculate();
        //view.printArray(matrix);
        view.printToPlot(matrix, Constants.filePathParallel);
    }
}
