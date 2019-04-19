public class ComputationalError {

    double computeError(double [][] matrix){
        double maxError=0;
        for(int t=0; t<Constants.numberOfTimeIterations+1; t++) {
            for (int i = 0; i < Constants.numberOfXIterations + 1; i++) {
                double temp=Math.abs(ExactValues.matrix[t][i]-matrix[t][i])/ExactValues.matrix[t][i];
                if(temp>maxError){
                    maxError=temp;
                }
            }
        }
        return maxError*100;
    }
}
