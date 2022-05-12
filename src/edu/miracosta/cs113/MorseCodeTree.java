package edu.miracosta.cs113;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * MorseCodeTree : A BinaryTree, with Nodes of type Character to represent each letter of the English alphabet,
 * and a means of traversal to be used to decipher Morse code.
 *
 * @version 1.0
 */
public class MorseCodeTree extends BinaryTree<Character> {

    public static final File INPUT_FILE = new File("/Users/arturo/Desktop/CodeRepository/Trees_HW7/src/edu/miracosta/cs113/Letters.txt") ;

    public MorseCodeTree() {
        readMorseCodeTree() ;
    }

    // TODO:
    // Build this class, which includes the parent BinaryTree implementation in addition to
    // the `translateFromMorseCode` and `readMorseCodeTree` methods. Documentation has been suggested for the former,
    // where said exceptional cases are to be handled according to the corresponding unit tests.

    /**
     * Non-recursive method for translating a String comprised of morse code values through traversals
     * in the MorseCodeTree.
     *
     * The given input is expected to contain morse code values, with '*' for dots and '-' for dashes, representing
     * only letters in the English alphabet.
     *
     * This method will also handle exceptional cases, namely if a given token's length exceeds that of the tree's
     * number of possible traversals, or if the given token contains a character that is neither '*' nor '-'.
     *
     * @param morseCode The given input representing letters in Morse code
     * @return a String representing the decoded values from morseCode
     */
    public String translateFromMorseCode(String morseCode) {

        if (morseCode.isEmpty()) {
            throw new IllegalArgumentException("Illegal argument (null) provided to translateFromMorseCode method");
        }

        String[] splitString = morseCode.split(" ") ;

        Node<Character> currentNode ;

        StringBuilder decodedString = new StringBuilder() ;

        for (String letter : splitString) {

            currentNode = root;

            for (int i = 0 ; i < letter.length() ; i++) {
                if (letter.charAt(i) == '*') {
                    currentNode = currentNode.left ;
                } else if (letter.charAt(i) == '-' ) {
                    currentNode = currentNode.right ;
                } else {
                    throw new IllegalArgumentException("String may only contain * or -") ;
                }
            }

            decodedString.append(currentNode.data) ;
        }
        return decodedString.toString() ;
    }

    private void readMorseCodeTree() {

        //initialize root to null
        root = new Node<>(null) ;

        //create an arraylist of all the data in the text file
        ArrayList<String> unparsedCode = new ArrayList<String>() ;

        //create a Node to contain the character data form the Strings in text file
        Node<Character> currentNode ;

        try {
            Scanner input = new Scanner(INPUT_FILE) ;

            while(input.hasNext()) {
                unparsedCode.add(input.nextLine()) ;
            }
            input.close();
        } catch(FileNotFoundException fnfe) {
            fnfe.printStackTrace() ;
            System.err.println("ERROR: Cannot open file: " + INPUT_FILE + " for input.") ;
        }

        for(String l : unparsedCode) {
            currentNode = root ;

            char letter = l.charAt(0) ;
            String morseCode = l.substring(2) ;

            for(int i = 0 ; i < morseCode.length() ; i++) {

                //left
                if(morseCode.charAt(i) == '*') {

                    if(currentNode.left == null) {
                        currentNode.left = new Node<>(letter);
                    } else {
                        currentNode = currentNode.left ;
                    }

                //right
                } else if(morseCode.charAt(i) == '-') {

                    if(currentNode.right == null) {
                        currentNode.right = new Node<>(letter);
                    } else {
                        currentNode = currentNode.right;
                    }

                }
            }
        }
    }

} // End of class MorseCodeTree