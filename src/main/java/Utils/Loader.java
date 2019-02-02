package Utils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Loader {

    public List<Integer> getSudokuFields(String path){
        return getDataFromFile(path);
    }

    private List<Integer> getDataFromFile(String path){
        List<Integer> fields = new ArrayList<>();
        File f = new File(path);

        try (BufferedReader in = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = in.readLine()) != null) {
                String[] tokens = line.split(",");
                for (String token : tokens) {
                    fields.add(Integer.parseInt(token));
                }
            }
        } catch (NumberFormatException exception) {
            System.out.println("Invalid file data");
            System.exit(1);
        } catch (FileNotFoundException exception){
            System.out.println("File not found");
            System.exit(1);
        } catch (IOException exception){
            System.out.println("Read data exception");
            System.exit(1);
        }
        return fields;
    }
}
