/**
 * Послідовна реалізація явного методу
 */
public class SequentialSolving {
    static double[][] matrix = new double[Constants.numberOfTimeIterations+1][Constants.numberOfXIterations+1];
    static  int finalT;
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
        long beginTime=System.currentTimeMillis(); //System.currentTimeMillis() or System.nanoTime()
        //всі інші, крім крайових значень розв'язку за виведеною вручну формулою
        for (int t = 0; t < Constants.numberOfTimeIterations ; t++) {
            for (int i = 1; i < Constants.numberOfXIterations ; i++) {
                matrix[t + 1][i] = Constants.a * Constants.timeStep * (2 * matrix[t][i]
                        * (matrix[t][i - 1] - 2 * matrix[t][i] + matrix[t][i + 1])
                        - Constants.xStep * Math.pow(matrix[t][i + 1] - matrix[t][i - 1], 2))
                        / (2 * Math.pow(Constants.xStep * matrix[t][i], 2)) + matrix[t][i];
            }
        }
        long endTime=System.currentTimeMillis();
        view.print("Sequential: "+(endTime-beginTime)+" ms\n");
        //вивід результату на екран
        //view.printArray(matrix);
        //вивід результуту в файл для побудови графіку
        view.printToPlot(matrix, Constants.filePathSequential);
    }
}
