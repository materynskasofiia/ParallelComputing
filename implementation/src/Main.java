public class Main {
    public static void  main(String [] args) {
        ExactValues.calculate();
        SequentialSolving.calculate();
        ComputationalError computationalError=new ComputationalError();
        System.out.println("Computational error: "+computationalError.computeError()+"%");
    }
}
