/**
 * Created by senni on 18/08/2017.
 */
public class Options {
    private int targetSum = 100000;
    private boolean printSumsEnabled = false;

    public Options(String[] args) {
        parseArgs(args);
    }

    private void parseArgs(String[] args) {
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "--target":
                    targetSum = Integer.parseInt(args[++i]);
                    break;
                case "--print":
                    printSumsEnabled = true;
                    break;
                default:
                    break;
            }
        }
    }

    public int getTargetSum() {
        return targetSum;
    }

    public boolean isPrintSumsEnabled() {
        return printSumsEnabled;
    }
}
