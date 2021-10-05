import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
public class Nonogram {

    public int height;   // The number of rows in the puzzle.
    public int width;    // The number of columns in the puzzle.
    public int maxRowGroups;  // An upper bound on the number of groups in any one row.
    public int maxColGroups;  // An upper bound on the number of groups in any one column.
    // Note: the above fields are used by the GUI classes, so don't eliminate them.
    // Do ensure that they are initialized to appropriate values.
    boolean[ ][ ] guess;   // A rectangular array to hold the user's solution to the puzzle.
    // The next two arrays are the heart of the puzzle.
    int[ ][ ] rowGroupLength;   // The group sizes for each row.  rowGroupLength[i][k] is the k'th group in row i.
    int[ ][ ] colGroupLength;   // Same, but for columns.  colGroupLength[j][k] is the k'th group in column j.
    int expectedNumberOfTrues = 0;
    int numberOfTruesInGuess;
    int lastMouseLocX;
    int lastMouseLocY;
    public Nonogram(boolean[ ][ ] targetSolution) {
        // targetSolution must be an mxn rectangular array where m,n >= 1.
        numberOfTruesInGuess = 0;
        height = targetSolution.length;
        width = targetSolution[0].length;
        maxRowGroups = 0;
        maxColGroups = 0;
        guess = new boolean[height][width];
        //Create our 2D array for rows, where there is height number of rows, and an unknown number of groups.
        rowGroupLength = new int[height][];
        //Create our 2D array for columns, where there is width number of rows, and an unknown number of groups.
        colGroupLength = new int[width][];
        rowGroupLength = findRowGroupLengths(targetSolution, false);
        colGroupLength = findColumnGroupLengths(targetSolution, false);

        //System.out.println("Expected "+ expectedNumberOfTrues);

        // TODO: Initialize the fields correctly to make a nonogram puzzle of which
        // targetSolution is a valid solution.
    }


    // The next constructor is provided for you.  It builds off the constructor above.
    public Nonogram(String s) {
        this(stringToBooleanArray(s));
    }


    // The next method is provided for you.  It converts a string to a 2D boolean array.
    static boolean[][] stringToBooleanArray (String s) {

        String[ ] lines = s.split("\n");
        boolean[][] rv = new boolean[lines.length][];
        for (int i=0; i<lines.length; i++) {
            String line = lines[i];
            rv[i] = new boolean[line.length()];
            for (int j=0; j<line.length(); j++) {
                rv[i][j] = (line.charAt(j)!='.');
            }
        }
        return rv;
    }

    int[ ][ ] findColumnGroupLengths (boolean[][] inputPuzzle, boolean guess) {
        int[][] returnedGroupLengths = new int[width][];
        for (int column = 0; column < width; column++) {
            //Create a counter that allows us to count the size of the group
            int counter = 0;
            //Create an arraylist to not have to deal with the hassle of an array's unknown size
            List<Integer> colGroups = new ArrayList<>();
            //In every column, descend the rows and check the size of the groups
            for (int row = 0; row < height; row++) {
                //Technically unnecessary ==, but it helps to visualize the requirement
                if (inputPuzzle[row][column] == true) {
                    counter++;
                } else {
                    //If it's not an X/true, we know we have either gone through a group or just going through blank
                    //space, to check if it's the end of the group we check if the counter was something >0
                    if (counter != 0) {
                        //If we successfully counted a group add it to the arraylist, and reset counter
                        //to 0 to prepare for the next column
                        colGroups.add(counter);
                        counter = 0;
                    }
                }
            }
            //It is possible that the last index/column in a row was an X, therefore
            //We never hit an empty space to allow us to add the counted group to our arraylist
            //To check if this is the case, we see if the counter is not zero, which would,
            //imply that there is was a group size that we didn't add yet
            if (counter != 0) {
                colGroups.add(counter);
            }
            //Now that we know the size of the number of groupings we have, create an array.
            returnedGroupLengths[column] = new int[colGroups.size()];
            //Iterate over all of the indexes for columns (0,1,2,3,4...width-1),
            //and create another array inside that index for a list containing the lengths of groups
            //in order. e.g (2,1,n
            //               2,3,n etc. up to width)
            //System.out.print("Column " + column + " : ");
                for (int i = 0; i < colGroups.size(); i++) {
                    returnedGroupLengths[column][i] = colGroups.get(i);
                    //System.out.print(colGroups.get(i) + " ");
                }
            //System.out.println();
        }
        if(!guess) {
            for(int i = 0; i < returnedGroupLengths.length; i++) {
                if(returnedGroupLengths[i].length > maxColGroups) {
                    maxColGroups = returnedGroupLengths[i].length;
                }
            }
        }
        return returnedGroupLengths;
    }



    int[ ][ ] findRowGroupLengths (boolean[][] inputPuzzle, boolean guess) {
        // figure out the groups in data, and record their
        // lengths in rowGroupLengths and colGroupLengths
        int[][] returnedGroupLengths = new int[height][];
        for(int row = 0; row < height; row++) {
            //Create a counter that allows us to count the size of the group
            int counter = 0;
            //Create an arraylist to not have to deal with the hassle of an array's unknown size
            List<Integer> rowGroups = new ArrayList<>();
            //In a specific row (row) loop over every column and check for what rowGroupLength are present
            for(int column = 0; column < width; column++) {
                //If it is an X, we can count up by 1
                if(inputPuzzle[row][column] == true) {
                    counter++;
                } else {
                    //If it's not an X/true, we know we have either gone through a group or just going through blank
                    //space, to check if it's the end of the group we check if the counter was something >0
                    if(counter != 0) {
                        //If we successfully counted a group add it to the arraylist, and reset counter
                        //to 0 to prepare for the next row
                        rowGroups.add(counter);
                        counter = 0;
                    }
                }
            }
            //It is possible that the last index/column in a row was an X, therefore
            //We never hit an empty space to allow us to add the counted group to our arraylist
            //To check if this is the case, we see if the counter is not zero, which would,
            //imply that there is was a group size that we didn't add yet
            if(counter != 0) {
                rowGroups.add(counter);
            }
            //Now that we know the size of the number of groupings we have, create an array.
            returnedGroupLengths[row] = new int[rowGroups.size()];
            //Iterate over all of the indexes for rows (0,1,2,3,4...height-1),
            //and create another array inside that index for a list containing the lengths of groups
            //in order. e.g (2,
            //               2,2
            //               3,4
            //               etc. up to height)
            //System.out.print("Row " + row + " : ");
                for(int i = 0; i < rowGroups.size(); i++) {
                    returnedGroupLengths[row][i] = rowGroups.get(i);
                    if(!guess) {
                        expectedNumberOfTrues += rowGroups.get(i);
                    }
                    //System.out.print(rowGroups.get(i) + " ");
                }

            //System.out.println();
        }
        if(!guess) {
            for(int i = 0; i < returnedGroupLengths.length; i++) {
                if(returnedGroupLengths[i].length > maxRowGroups) {
                    maxRowGroups = returnedGroupLengths[i].length;
                }
            }
        }
        return returnedGroupLengths;
        // TODO: your code here

    }

    @Override
    public String toString( ) {
        //Note: if you wish to have my toString method actually printing out every time you change a square
        //please uncomment the lines that call this method in the ends of
        //the handleMouseClick and handleMouuseReleaseAt methods

        // print out the nonogram as a string.
        // this should include the group lengths
        // as well as the current guess.

        // this will be mainly used as an debugging tool, as
        // the GUI will be better for interacting with the user.
        String rv = "";
        String blank = " ";
        // For the guess, we will represent the "EMPTY color" by the '.' char,
        // and the "FULL color" (black) by the 'X' char.

        for(int counter = maxColGroups; counter > 0; counter--) {
            rv += blank.repeat(maxRowGroups*2);
            for(int col = 0; col < width; col++) {
                if(colGroupLength[col].length >= counter) {
                    rv += colGroupLength[col][colGroupLength[col].length-counter] + " ";
                } else {
                    rv += "  ";
                }
            }
            rv += "\n";
        }


        for(int row = 0; row < height; row++) {
            rv += blank.repeat((maxRowGroups - rowGroupLength[row].length)*2);
            for(int group = 0; group < rowGroupLength[row].length; group++) {
                rv += rowGroupLength[row][group] + " ";
            }
            for(int col = 0; col <width; col++) {
                if(guess[row][col]) {
                    rv += "X ";
                } else {
                    rv += ". ";
                }
            }
            rv += "\n";
        }

        return rv;
        // TODO: your code here.

    }

    public String contentsOfArray(int[][] arr) {
        String rv = "";

        return rv;
    }

    boolean isGuessCorrect ( ) {
        // return true iff the guess has all the correct row/column
        // group lengths.  It does not have to match the "solution" field.
        // TODO: your code here
        //Guess is coded such that it begins checking once there is at least the amount of
        //Trues needed to match the solution on the guessboard i.e if the minumum amount of trues needed
        //for a correct solution is 20 trues on the board, then once the guess reaches 20 trues
        //then it will begin checking the guess
        boolean flag = true;
        //Find the group lengths of the rows in the user's guess
        int[][] guessRowGroupLengths = findRowGroupLengths(guess, true);
        for(int row = 0; row < height; row++) {
            //First check if the amount of groups is the same as the correct solutions
            //as we do not want to specific indexes if their lengths don't match up
            if(guessRowGroupLengths[row].length != rowGroupLength[row].length) {
                System.out.println("Incorrect number of groups in row : " + row+
                        " Expected : " + rowGroupLength[row].length + " Received: " +
                        guessRowGroupLengths[row].length);
                flag = false;
            } else {
                for(int group = 0; group < rowGroupLength[row].length; group++) {
                    if(guessRowGroupLengths[row][group] != rowGroupLength[row][group]) {
                        System.out.println("Incorrect group length for row : " + row + " and group number : "
                                + group+ " Expected: " + rowGroupLength[row][group] +
                                " Received : " + guessRowGroupLengths[row][group]);
                        flag = false;
                    }
                }
            }
        }
        //Same process for columns.
        int[][] guessColumnGroupLengths = findColumnGroupLengths(guess, true);
        for(int col = 0; col < width; col++) {
            //If our guesses # of column groups in a specific column aren't the same, don't bother checking them
            //say that we had an incorrect number of groups in that column.
            if(guessColumnGroupLengths[col].length != colGroupLength[col].length) {
                System.out.println("Incorrect number of groups in column : " + col +
                        " Expected : " + colGroupLength[col].length + " Received: " +
                        guessColumnGroupLengths[col].length);
                flag = false;
            } else {
                //Otherwise, let's start checking the groups compared to the right solution's
                for(int group = 0; group < colGroupLength[col].length; group++) {
                        if(guessColumnGroupLengths[col][group] != colGroupLength[col][group]) {
                            System.out.println("Incorrect group length for column : " + col + " and group number : " +
                                    group + " Expected: " + colGroupLength[col][group] +
                                    " Received : " + guessColumnGroupLengths[col][group]);
                            flag = false;
                        }
                }
            }
        }
        if(flag) {
            System.out.println("Congratulations, you solved the puzzle!");
        } else {
            System.out.println("That puzzle solution is incorrect.");
        }
        return flag;
    }

    // The next 4 methods are callback methods that will be invoked by the GUI when appropriate.
    // You need to fill them in to provide the correct functionality.
    public void handleMouseClickAt(int i, int j) {
        // Add code here to handle a "mouseClick" event at row i, column j.
        // You should toggle the guessed color for the cell at the clicked location.
        // You will need to handle the possibility that (i,j) is out of bounds (negative or too big).
        // TODO: your code here
        try {
            if(guess[i][j]) {
                guess[i][j] = false;
                numberOfTruesInGuess--;
            } else {
                guess[i][j] = true;
                numberOfTruesInGuess++;
            }
        } catch (Exception e) {

        }
        if(numberOfTruesInGuess >= expectedNumberOfTrues) {
            isGuessCorrect();
        }
        //Uncomment this if you wish for the string to be printed out any time a
        //box is clicked
        //System.out.println(this.toString());
    }

    public void handleMousePressAt(int yLoc, int xLoc) {
        // Add code here to handle a "mousePress" event at row i, column j.
        // You should record the location of the press in an instance variable.
        // Don't display anything until the mouse is released.
        // You will need to handle the possibility that (i,j) is out of bounds (negative or too big).
        // TODO: your code here

        //The row number indicates the Y location of the click
        //The column number indicates the X location of the click
        try {
            lastMouseLocX = xLoc;
            lastMouseLocY = yLoc;
        } catch (Exception e) {

        }

    }

    public void handleMouseReleaseAt(int currentLocY, int currentLocX) {
        // Add code here to handle a "mouseRelease" event at row i, column j.
        // If there was a previous mousePress event, you should now check whether the "release" cell is
        // in the same row or column as the "press" cell.  If it is, you should flip the color of the
        // "press" cell, and color all the other cells between the "press" and "release" cells to match.
        // You will need to handle the possibility that (i,j) is out of bounds (negative or too big).
        // TODO: your code here

        //See what the difference between the x and y values are.
        int differenceInY = currentLocY - lastMouseLocY;
        int differenceInX = currentLocX- lastMouseLocX;
        int startingPoint;
        int endingPoint;
        //Check what kind of movement the mouse did, in the X or Y direction.
        if(lastMouseLocY != currentLocY) {
            try {
                //Depending on whether the difference was negative or positive
                //Change at what points we start and end the loop at
                if(differenceInY < 0) {
                    startingPoint = currentLocY;
                    endingPoint = lastMouseLocY;
                } else {
                    startingPoint = lastMouseLocY;
                    endingPoint = currentLocY;
                }
                //Depending on what the value was at the initial position clicked, change all
                //of the boxes in between the starting point and end point to the opposite of that value
                if(guess[lastMouseLocY][lastMouseLocX]) {
                    for(int row = startingPoint; row <= endingPoint; row++) {
                        if(guess[row][lastMouseLocX] != false) {
                            guess[row][lastMouseLocX] = false;
                            numberOfTruesInGuess--;
                        }

                    }
                } else {
                    for(int row = startingPoint; row <= endingPoint; row++) {
                        if(guess[row][lastMouseLocX] != true) {
                            guess[row][lastMouseLocX] = true;
                            numberOfTruesInGuess++;
                        }

                    }
                }
            } catch (Exception e) {
                System.out.println("Illegal x, y");
            }
        } else if(lastMouseLocX != currentLocX) {
            //Executes if y did not change, and if x did change
            //Same process with negative differences
            try {
                if(differenceInX < 0) {
                    startingPoint = currentLocX;
                    endingPoint = lastMouseLocX;
                } else {
                    startingPoint = lastMouseLocX;
                    endingPoint = currentLocX;
                }
                //Depending on what the value was at the initial position clicked, change all
                //of the boxes in between the starting point and end point to the opposite of that value
                if(guess[lastMouseLocY][lastMouseLocX]) {
                    for(int col = startingPoint; col <= endingPoint; col++) {
                        if(guess[lastMouseLocY][col] != false) {
                            guess[lastMouseLocY][col] = false;
                            numberOfTruesInGuess--;
                        }

                    }
                } else {
                    for(int col = startingPoint; col <= endingPoint; col++) {
                        if(guess[lastMouseLocY][col] != true) {
                            guess[lastMouseLocY][col] = true;
                            numberOfTruesInGuess++;
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Illegal x, y");
            }
        }
        if(numberOfTruesInGuess >= expectedNumberOfTrues) {
            isGuessCorrect();
        }
        //Uncomment this if you wish for the guess to be printed out every time a selection of boxes
        //is changed
        //System.out.println(this.toString());
    }

    public void handleResetButtonClick( ) {
        // Add code to handle a click of the "reset" button.
        // You should reset all cells to be empty (white) again.
        // TODO: your code here
        for(int row = 0; row < height; row++) {
            for(int column = 0; column < width; column++) {
                if(guess[row][column]) {
                    guess[row][column] = false;
                }
            }
        }
        numberOfTruesInGuess = 0;
    }

    // main method: you can put your own test code in here.
    public static void main(String[] args) {

        //Default beginner String:
        String pic = "..XXX..\n.XX.XX.\nXX...XX\nX.....X\nXX...XX\n.XX.XX.\n..XXX..";
        //Example 1 on learn:
        /*String pic = "......X.XX\n" +
                     "........XX\n" +
                     ".......X..\n" +
                     ".........X\n" +
                     ".....XX...\n" +
                     "XX..XXXX..\n" +
                     "XX.XXXXXX.\n" +
                     ".XXXXXXXX.\n" +
                     "....X..X..\n" +
                     "...XX.XX..";
*/
        //Example 2 on learn:
        /*String pic =8
                        "XX...X\n" +
                        ".X.XXX\n" +
                        ".X.XX.\n" +
                        ".XXX..\n" +
                        ".XXXX.\n" +
                        "...X..";

         */
        Nonogram nono = new Nonogram(pic);

        System.out.println(pic);
        System.out.println();
        System.out.println(nono);


        NonogramGUI gui = new NonogramGUI(nono);   // starts up a GUI interface for the puzzle passed to it.
    }

}
