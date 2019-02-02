package validator;

import org.junit.Test;


import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class Tests {


    Validator sudokuValidator = new SudokuValidator();

    @Test
    public void validSolutionShouldReturnZero(){
        List<Integer> sudokuFields = Arrays.asList(7,3,5,6,1,4,8,9,2,
                                                   8,4,2,9,7,3,5,6,1,
                                                   9,6,1,2,8,5,3,7,4,
                                                   2,8,6,3,4,9,1,5,7,
                                                   4,1,3,8,5,7,9,2,6,
                                                   5,7,9,1,2,6,4,3,8,
                                                   1,5,7,4,9,2,6,8,3,
                                                   6,9,4,7,3,8,2,1,5,
                                                   3,2,8,5,6,1,7,4,9
        );
        assertEquals(0,sudokuValidator.validate(sudokuFields));
    }

    @Test
    public void invalidSsolutionShouldReturnOne(){
        List<Integer> sudokuFields = Arrays.asList(1,2,3,1,2,3,1,2,3,
                                                   4,5,6,4,5,6,4,5,6,
                                                   7,8,9,7,8,9,7,8,9,
                                                   1,2,3,1,2,3,1,2,3,
                                                   4,5,6,4,5,6,4,5,6,
                                                   7,8,9,7,8,9,7,8,9,
                                                   1,2,3,1,2,3,1,2,3,
                                                   4,5,6,4,5,6,4,5,6,
                                                   7,8,9,7,8,9,7,8,9
        );
        assertEquals(1,sudokuValidator.validate(sudokuFields));
    }

}
