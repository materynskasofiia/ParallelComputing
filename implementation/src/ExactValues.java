public class ExactValues {
    public static void  main(String [] args){
        View view=new View();
        for(int t=0; t<Constants.numberOfTimeIterations+1; t++){
            for(int i=0; i<Constants.numberOfXIterations+1; i++){
                view.printFormatted("%.2f",Math.pow(Constants.c0*Constants.xStep*i-
                        Constants.a*Constants.c1*t*Constants.timeStep+Constants.c2, -1));
                view.print(" ");
            }
            view.print("\n");
        }
//        for(int t=0; t<Constants.numberOfTimeIterations; t++) {
//            for (int i = 0; i < Constants.numberOfXIterations; i++) {
//                view.print(System.out.print("{" + Constants.xStep * i + "," + Constants.timeStep * t + ","
//                        + Math.pow(Constants.c0 * Constants.xStep * i -
//                        Constants.a * Constants.c1 * t * Constants.timeStep + Constants.c2, -1) + "},");
//            }
//            view.print("\n");
//        }
    }
}
