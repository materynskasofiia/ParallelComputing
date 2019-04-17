public class SequentialSolving {
    public static void main(String[] args) {
        double[][] matrix = new double[Constants.numberOfTimeIterations+1][Constants.numberOfXIterations+1];
        View view=new View();
        //
        for (int i = 0; i < Constants.numberOfXIterations+1; i++) {
            matrix[0][i] = Math.pow(Constants.c0 * Constants.xStep * i + Constants.c2, -1);
        }
        for (int t = 1; t < Constants.numberOfTimeIterations+1; t++) {
            matrix[t][0] = Math.pow(-Constants.a * Constants.c1 * t * Constants.timeStep + Constants.c2, -1);
            matrix[t][Constants.numberOfXIterations] = Math.pow(Constants.c0 -
                    Constants.a * Constants.c1 * t * Constants.timeStep + Constants.c2, -1);
        }
        for (int t = 0; t < Constants.numberOfTimeIterations ; t++) {
            for (int i = 1; i < Constants.numberOfXIterations ; i++) {
                matrix[t + 1][i] = Constants.a * Constants.timeStep * (2 * matrix[t][i] * (matrix[t][i - 1] - 2 * matrix[t][i] + matrix[t][i + 1])
                        - Constants.xStep * Math.pow(matrix[t][i + 1] - matrix[t][i - 1], 2)) / (2 * Math.pow(Constants.xStep * matrix[t][i], 2)) + matrix[t][i];
            }
        }
        for (int t = 0; t < Constants.numberOfTimeIterations+1 ; t++) {
            for (int i = 0; i < Constants.numberOfXIterations+1 ; i++) {
                view.printFormatted("%.2f",matrix[t][i]);
                view.print(" ");
            }
            view.print("\n");
        }
    }
}