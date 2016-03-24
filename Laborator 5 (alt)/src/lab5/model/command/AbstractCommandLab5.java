package lab5.model.command;

import java.nio.file.Path;
import java.nio.file.Paths;
import lab5.controller.MyTree;
import lab5.controller.content.DetailsPanel;

/**
 *
 * @author Anca
 */
abstract public class AbstractCommandLab5 implements CommandLab5 {
    private MyTree myTree;
    private DetailsPanel dp;
    protected String[] args;
    protected String currentPath;
    /**
     * @return the arguments
     */
    @Override
    public String[] getArgs() {
        return this.args;
    }

    /**
     * @param args the arguments to set
     */
    @Override
    public void setArgs(String[] args) {
        this.args = args;
    }

    /**
     * Creates a Path object from the arguments
     * @return Path
     */
    public Path parseArgs() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length - 1; i++) {
            sb.append(args[i]).append(" ");
        }
        sb.append(args[args.length - 1]);
        //Checks if the link is absolute, if not it appends the current dir
        String initString;
        if (Paths.get(sb.toString()).isAbsolute()) {
            initString = "";
        }
        else {
            initString = getCurrentPath().toString()+"\\";
        }
        //Replace the / with double / in the link
        String[] list = sb.toString().split("\\\\");
        StringBuilder sb2 = new StringBuilder(initString);
        for (int i = 0; i < list.length - 1; i++) {
            sb2.append(list[i]).append("\\\\");
        }
        sb2.append(list[list.length - 1]);
        Path p = Paths.get(sb2.toString());
        return p;
    }

    /**
     * @return the currentPath
     */
    public String getCurrentPath() {
        return currentPath;
    }

    /**
     * @param currentPath the currentPath to set
     */
    public void setCurrentPath(String currentPath) {
        this.currentPath = currentPath;
    }

    /**
     * @return the myTree
     */
    public MyTree getMyTree() {
        return myTree;
    }

    /**
     * @param myTree the myTree to set
     */
    public void setMyTree(MyTree myTree) {
        this.myTree = myTree;
    }

    /**
     * @return the dp
     */
    public DetailsPanel getDp() {
        return dp;
    }

    /**
     * @param dp the dp to set
     */
    public void setDp(DetailsPanel dp) {
        this.dp = dp;
    }
}

