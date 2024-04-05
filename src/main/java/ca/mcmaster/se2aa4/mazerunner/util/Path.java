package ca.mcmaster.se2aa4.mazerunner.util;

import java.util.regex.*;

public class Path {
    
    private String path;

    public Path(String input_path) {
        this.path = input_path;
    }

    public void print() {
        System.out.println(path);
    }

    public String getString() {
        return this.path;
    }

    public void factorize() {
        String moves = this.path.replaceAll(" ", "");
        StringBuilder factorized_path = new StringBuilder();

        int count = 1;
        char current_move = ' ';
        for (int i = 0; i < moves.length() - 1; i++) {
            current_move = moves.charAt(i);

            if (current_move == moves.charAt(i + 1) && i < moves.length() - 1) {
                //If the next move is the same (next move cannot be the last move)
                count++;
            } else {
                //If the next move is not the same (or current move is the last move)
                if (count > 1) {
                    factorized_path.append(Integer.toString(count));
                }
                factorized_path.append(current_move);
                factorized_path.append(' ');
                count = 1;
            }
        }
        current_move = moves.charAt(moves.length() - 1);
        if (count > 1) {
            factorized_path.append(Integer.toString(count));
        }
        factorized_path.append(current_move);


        this.path = factorized_path.toString();
    }

    public void unfactorize() {
        StringBuilder unfactorized_path = new StringBuilder();

        String regex = "\\b(\\d*)([LRF])\\b";
        String input = this.path;

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        while (matcher.find()) {

            int number;
            if (matcher.group(1) != "") {
                number = Integer.parseInt(matcher.group(1));
            } else {
                number = 1;
            }
            
            String direction = matcher.group(2);
            for (int i = 0; i < number; i++) {
                unfactorized_path.append(direction);
            }

        }

        this.path = unfactorized_path.toString();
    }
}
