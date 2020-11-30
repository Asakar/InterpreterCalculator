import java.util.ArrayList;
import java.util.HashMap;

/**
 * In this class  I create a program which is a mini-interpreter, it consists of a variables keys which is
 * of type HashMap and program of type string. The keys is used to store my variables and their values. The program is used to store the input ie. what I
 * will be interpreting.
 * @author Asakar Hussain
 * @version
 */

public class Interpreter {

    static HashMap<String, Integer> keys = new HashMap<String, Integer>();

    static String program = "A = 2\nB = 8\nC = A + B\nC"; // I have hard coded the String input as specified
//    static String program = "A = 2\nB = 8\nC = A  + -B\nC"; // I have hard coded the String input as specified


    /**
     * My input method will split the program onn new line and return it as a string array
     * @return String array which holds the values of the input
     */
    public static String[] input(){
        return program.split("\n"); // Here I first split the statements on each new line
    }

    /**
     * My splittingStatements method further splits the input on the equal sign such that I end up with an array list which contains arrays.
     * @return variablesList which is a Array list which contains arrays
     */
    public static ArrayList<String[]> splittingStatements(){
        ArrayList<String[]> variableList = new ArrayList<>();

        for(int i = 0; i< input().length; i++){
            variableList.add(input()[i].split("=")); // Here I split the statements further on the equal sign and store it in variableList
        }

        return variableList;
    }

    /**
     * This evaluate method evaluates the string to get the integer value to be stored in the HashMap. it takes as a parameter a string.
     * @param statement of type string to inspect
     * @return total of type integer which will be stored in the HashMap
     */
    public static int evaluate(String statement){

        int total = 0; //Holds the final value of the variable which will be stored in HashMap
        ArrayList<Integer> numberValue = new ArrayList<Integer>(); // An array list of my values
        String[] symbols = statement.split("\\s+"); //This splits the statement on the spaces and stores the array in symbols

        for(int i=0; i < symbols.length;i++){

            String symbol = symbols[i];

            // I will first check if the symbol at the element i is equal to any negative variable as asked for by Harish so that I can handle the
            // operation C = A + -B. In each of my if statements I will get the value and add it to my numberValue array list.
            if(symbol.matches("-[A-Za-z]+")){
                numberValue.add(-keys.get(symbol.replace("-", "")));
            }

            // Then I will check if it is equal to any positive variable.
            if(symbol.matches("[A-Za-z]+")){
                numberValue.add(keys.get(symbol));
            }

            // Then I will check if the symbol is a integer
            if (symbol.matches("[0-9]+")) {
                numberValue.add(Integer.parseInt(symbol));
            }
            // Then I will check if I am dealing with a negative integer
            if(symbol.matches("-[0-9]+")){
                numberValue.add(Integer.parseInt(symbol));
            }
        }

        // This following for loop is used to add upp the values in numberValue array and store it to total.
        for(int i=0; i<numberValue.size(); i++){
            total += numberValue.get(i);
        }

        return total;
    }

    /**
     * My addingVariables method adds the variables and their corresponding values to HashMap. It will also print the value when needed to.
     */
    public static void addingVariables() {

        // I will loop through the split statement
        for (int j = 0; j < splittingStatements().size(); j++) {
            String[] currentStatement = splittingStatements().get(j); // This will store the statement I am currently inspecting in currentStatement

            // My first if statement here adds the variables and adds is corresponding  evaluated integer to my HashMap
            // So this means i have successfully stored all the variables and their integer values.
            if (currentStatement.length > 1) {
                keys.put(currentStatement[0].replace(" ",""), evaluate(currentStatement[1]));
            }

            // This if statement will check if the length of the current statement is equal to 1 and will then print the value
            // as Harish asked for.
            if(currentStatement.length==1){
                System.out.println(keys.get(splittingStatements().get(j)[0]));
            }
        }
    }

    /**
     * In my method I create the object test and execute the program.
     */
    public static void main(String[] args) {
        addingVariables();
    }
}
