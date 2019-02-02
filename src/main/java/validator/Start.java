package validator;

import Utils.Loader;

import java.util.List;
import java.util.Scanner;

public class Start {

    public static void main(String[] args) {
        start();
    }

    private static void start(){
        validate();
    }

    private static void validate(){
        Validator sudokuValidator = new SudokuValidator();
        System.out.println(sudokuValidator.validate(readData()));
    }

    private static List<Integer> readData() {
        Loader loader = new Loader();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Type file name or full path");
        String fileName = scanner.nextLine();
        return loader.getSudokuFields(fileName);

    }


}
