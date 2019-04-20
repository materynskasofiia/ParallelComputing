class ParallelSolution {

    static int finalT;
    static final double a=1.;
    static final double c0=.5;
    static final double c1=.5;
    static final double c2=1.;
    static final int numberOfXIterations=30;
    static final int numberOfTimeIterations=2500;
    static final double xStep=1./numberOfXIterations;
    static final double timeStep=1./numberOfTimeIterations;
    static double[][] matrix = new double[numberOfTimeIterations+1][numberOfXIterations+1];
    static double[][] matrix1 = new double[numberOfTimeIterations+1][numberOfXIterations+1];
    public static void main(String [] args){
        for (int i = 0; i < numberOfXIterations+1; i++) {
            matrix[0][i] = Math.pow(c0 * xStep * i + c2, -1);
        }
        for (int t = 1; t < numberOfTimeIterations+1; t++) {
            matrix[t][0] = Math.pow(-a * c1 * t * timeStep + c2, -1);
            matrix[t][numberOfXIterations] = Math.pow(c0 -
                    a * c1 * t * timeStep + c2, -1);
        }
        long beginTime=System.currentTimeMillis();
        for (int t = 0; t < numberOfTimeIterations ; t++) {
            // omp parallel for
            for (int i = 1; i < numberOfXIterations ; i++) {
                matrix[t + 1][i] = a * timeStep * (2 * matrix[t][i]
                        * (matrix[t][i - 1] - 2 * matrix[t][i] + matrix[t][i + 1])
                        - xStep * Math.pow(matrix[t][i + 1] - matrix[t][i - 1], 2))
                        / (2 * Math.pow(xStep * matrix[t][i], 2)) + matrix[t][i];
            }
        }
        long endTime=System.currentTimeMillis();
        for(int t=0; t<numberOfTimeIterations+1; t++){
            for(int i=0; i<numberOfXIterations+1; i++){
                matrix1[t][i]= Math.pow(c0 * xStep * i -
                        a * c1 * t * timeStep + c2, -1);
            }
        }
        double maxError=0;
        for(int t=0; t<numberOfTimeIterations+1; t++) {
            for (int i = 0; i < numberOfXIterations + 1; i++) {
                double temp=Math.abs(matrix[t][i]-matrix1[t][i])/matrix1[t][i];
                if(temp>maxError){
                    maxError=temp;
                }
            }
        }
        System.out.println("Computational error: "+maxError*100+"%\n" +
                "Computed in "+(endTime-beginTime)+" ms");
    }
}

