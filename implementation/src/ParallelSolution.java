import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class ParallelSolution {
    static double[][] matrix = new double[Constants.numberOfTimeIterations+1][Constants.numberOfXIterations+1];
    static int finalT;
    public static void main(String [] args){
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
//        for (int t = 0; t < Constants.numberOfTimeIterations ; t++) {
//            finalT = t;
//            IntStream.range(1,Constants.numberOfXIterations).parallel().forEach(a->
//                    matrix[finalT +1][a]=Constants.a * Constants.timeStep * (2 * matrix[finalT][a]
//                    * (matrix[finalT][a - 1] - 2 * matrix[finalT][a] + matrix[finalT][a + 1])
//                    - Constants.xStep * Math.pow(matrix[finalT][a + 1] - matrix[finalT][a - 1], 2))
//                    / (2 * Math.pow(Constants.xStep * matrix[finalT][a], 2)) + matrix[finalT][a]);
//        }
        for (int t = 0; t < Constants.numberOfTimeIterations ; t++) {
            // omp parallel for
            for (int i = 1; i < Constants.numberOfXIterations ; i++) {
                matrix[t + 1][i] = Constants.a * Constants.timeStep * (2 * matrix[t][i]
                        * (matrix[t][i - 1] - 2 * matrix[t][i] + matrix[t][i + 1])
                        - Constants.xStep * Math.pow(matrix[t][i + 1] - matrix[t][i - 1], 2))
                        / (2 * Math.pow(Constants.xStep * matrix[t][i], 2)) + matrix[t][i];
            }
        }
        long endTime=System.currentTimeMillis();
        new View().print("Parallel: "+(endTime-beginTime)+" ms\n");
        ExactValues.calculate();
        System.out.println("Computational error: "+new ComputationalError().computeError(ParallelSolution.matrix)+"%\n" +
                "Computed in "+(endTime-beginTime)+" ms");
        //new View().printArray(matrix);
        //new View().printToPlot(matrix, Constants.filePathParallel);
    }
}

