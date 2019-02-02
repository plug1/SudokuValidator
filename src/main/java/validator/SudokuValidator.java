package validator;

import Exceptions.BadSolutionException;

import java.util.*;

public class SudokuValidator implements Validator {

    @Override
    public int validate(List<Integer> sudokuFields){
        try{
            findAnyWrongNumber(sudokuFields);
            validateRows(sudokuFields);
            validateGrids(sudokuFields);
            validateColumns(sudokuFields);
        }catch (BadSolutionException exception){
            System.out.println(exception.getMessage());
            return 1;
        }
        return 0;
    }

    private void findAnyWrongNumber(List<Integer> fields){
        Optional<Integer> wrongNumber = fields.stream().filter( x -> (x < 1 || x > 9 )).findFirst();
        wrongNumber.ifPresent( x -> { throw new BadSolutionException(">>> There is wrong number: " + x +"<<<"); });
    }

    private void validateRows(List<Integer> fields){
        if (getNumberOfCorrectRows(fields) != 9) {
            throw new BadSolutionException(">>> There is wrong row <<<");
        }
    }

    private int getNumberOfCorrectRows(List<Integer> fields){
        int rowSum = 0;
        int correctRows = 0;
        Set<Integer> uniqueValues = new HashSet<>();

        for (int i = 0; i < fields.size(); i++) {
            if (i % 9 == 0) {
                uniqueValues.clear();
                uniqueValues.add(fields.get(i));
                rowSum = fields.get(i);
            } else {
                rowSum += fields.get(i);
                uniqueValues.add(fields.get(i));
                if (rowSum == 45) {
                    if (uniqueValues.size() == 9)
                        correctRows++;
                }
            }
        }
        return correctRows;
    }

    private void validateColumns(List<Integer> fields){
        if (getNumberOfCorrectColumns(fields) != 9){
            throw new BadSolutionException(">>> There is wrong column <<<");
        }
    }

    private int getNumberOfCorrectColumns(List<Integer> fields){
        int correctCols = 0;
        int colSum;
        Set<Integer> uniqueValues = new HashSet<>();

        for (int i = 0; i < 9; i++) {
            colSum=0;
            uniqueValues.clear();

            for (int j = i; j < fields.size(); j++){
                if (j % 9 == 0 || j == i){
                    if(i == j){
                        colSum += fields.get(j);
                        uniqueValues.add(fields.get(j));
                    }
                    else{
                        colSum += fields.get(j+i);
                        uniqueValues.add(fields.get(j+i));
                    }
                }
                if (colSum == 45){
                    if (uniqueValues.size() == 9){
                        correctCols++;
                        break;
                    }
                }
            }
        }
        return correctCols;
    }

    private void validateGrids(List<Integer> fields){

        if (getNumberOfCorrectGrids(fields) != 9) {
            throw new BadSolutionException(">>> There is wrong grid <<<");
        }
    }

    private int getNumberOfCorrectGrids(List<Integer> fields){
        int gridSum;
        int counter;
        int externalCounter=0;
        int correctGrids=0;
        Set<Integer> uniqueValues = new HashSet<>();

        for (int i = 0; i < fields.size();){
            externalCounter ++;
            gridSum = 0;
            counter = 0;
            uniqueValues.clear();
            for (int j = i; j < fields.size(); j++){
                counter++;
                if (j % 3 == 0 && j != i) {
                    j += 6;
                    gridSum += fields.get(j);
                    uniqueValues.add(fields.get(j));
                }
                else{
                    gridSum += fields.get(j);
                    uniqueValues.add(fields.get(j));
                }
                if (counter == 9){
                    if(externalCounter == 3 || externalCounter == 6){
                        i += 21;
                    }
                    else{
                        i += 3;
                    }
                    if (gridSum == 45){
                        if (uniqueValues.size() == 9){
                            correctGrids++;
                        }
                    }
                    break;
                }
            }
            if (externalCounter == 9){
                break;
            }
        }
        return correctGrids;
    }
}
