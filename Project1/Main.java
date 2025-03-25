//import classes and packages
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.PrintStream;


// create AVLTree class to construct AVL Tree
public class Main
{
    //main() method starts
    public static void main(String[] args)throws FileNotFoundException
    {
        // create tree object of ConstructAVLTree class object for costructing AVL Tree
        ConstructAVLTree treeObject = new ConstructAVLTree();



        String inputFileName = args[0]; // take the input file name fromn the command line
        String outputFileName = args[1]; // take the output file name from the commmand line

        //////////////////////////Write File///////////////////////////////////////////////////////////////
        try {
            //Reflecting standard output to a .txt file
            PrintStream fileOut = new PrintStream(outputFileName);
            System.setOut(fileOut);
        } catch (FileNotFoundException e){
            System.err.println("Output file cant be opened." + e.getMessage());
        }

        /////////////////////////////////////////////////////////////////////////////////////////////////////






        //////////////////////////Read File/////////////////////////////////////////////////////////////////

        String fileName = inputFileName;
        File file = new File(fileName);
        Scanner inputFile = new Scanner(file);

        boolean rootMustStart=true;
        while (inputFile.hasNextLine()){


            String readLine = inputFile.nextLine(); //read text line by line
            String[] lineSplit = readLine.split(" ");
            //System.out.println(lineSplit[0]);

            if (rootMustStart){
                treeObject.insertElement( lineSplit[0], Float. parseFloat(lineSplit[1]) );
                rootMustStart=false;
                continue;
            }

            if(lineSplit[0].equals("MEMBER_IN")) {
                treeObject.insertElement( lineSplit[1], Float. parseFloat(lineSplit[2]) );
            }
            else if(lineSplit[0].equals("MEMBER_OUT")) {
                treeObject.remove( Float. parseFloat(lineSplit[2]) );
            }
            else if(lineSplit[0].equals("INTEL_TARGET")) {
                treeObject.searchTarget( Float. parseFloat(lineSplit[2]), Float. parseFloat(lineSplit[4]) );
            }
            else if(lineSplit[0].equals("INTEL_RANK")) {

                treeObject.outputNodesSameDepth( Float. parseFloat(lineSplit[2]) );
                System.out.println("");
            }
            else if(lineSplit[0].equals("INTEL_DIVIDE")) {
                int targetcount = treeObject.maxNumIndependentNodes();
                System.out.println("Division Analysis Result: " + targetcount);
            }
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////



        //System.out.println("");

        //treeObject.preorderTraversal();









    }
}