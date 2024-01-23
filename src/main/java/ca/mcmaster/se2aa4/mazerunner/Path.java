package ca.mcmaster.se2aa4.mazerunner;

public class Path {
    
    String path;

    Path(String input_path) {
        //Implement Path Factorizer
        this.path = input_path;
    }

    public void print() {
        System.out.println(path);
    }
}
