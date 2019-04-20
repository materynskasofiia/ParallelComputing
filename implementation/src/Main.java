public class Main {
    public static void  main(String [] args) {
        ExactValues.calculate();
        new SequentialSolving().calculate();
        ComputationalError computationalError=new ComputationalError();
        System.out.println("Computational error: "+computationalError.computeError(SequentialSolving.matrix)+"%");
        new ParallelSolutionStreams().calculate();
        System.out.println("Computational error: "+computationalError.computeError(ParallelSolutionStreams.matrix)+"%");
    }
}
