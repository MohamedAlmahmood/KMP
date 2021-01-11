/* 
   (originally from R.I. Nishat - 08/02/2014)
   (revised by N. Mehta - 11/7/2018)
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;


public class KMP{
    private String pattern;
    int[][] array;
   
    
    public KMP(String pattern){  
        this.pattern = pattern;
    	array = new int [256][pattern.length()];
    	array[pattern.charAt(0)][0] = 1;
    	int i = 1;
    	int state = 0; //longest prefix suffix.
    	while(i<pattern.length())  //loop for the length of the pattern
    	{
    		int j = 0;
    		while(j<256) //for all possible characters
    		{
    			array[j][i] = array[j][state];
    			j++;
    		}
    		state = array[pattern.charAt(i)][state]; //update longest prefix suffix.
    		array[pattern.charAt(i)][i] = i + 1;
    		i++;
    	}
    	
    }
    
    public int search(String txt){  
    	
    	int i = 0; 
    	int counter = 0;
    	while (i < pattern.length()  &&  counter < txt.length()) 
    	{
    		i = array[txt.charAt(counter)][i];
    		counter++;
    	} 
    	
    	if (i>pattern.length() || i<pattern.length()) 
    	{
    		return txt.length();
    	}
    	else 
    	{
    		return counter - pattern.length();
    	}
    	
    }
    
    
    public static void main(String[] args) throws FileNotFoundException{
	Scanner s;
	if (args.length > 0){
	    try{
		s = new Scanner(new File(args[0]));
	    }
	    catch(java.io.FileNotFoundException e){
		System.out.println("Unable to open "+args[0]+ ".");
		return;
	    }
	    System.out.println("Opened file "+args[0] + ".");
	    String text = "";
	    while(s.hasNext()){
		text += s.next() + " ";
	    }
	    
	    for(int i = 1; i < args.length; i++){
		KMP k = new KMP(args[i]);
		int index = k.search(text);
		if(index >= text.length()){
		    System.out.println(args[i] + " was not found.");
		}
		else System.out.println("The string \"" + args[i] + "\" was found at index " + index + ".");
	    }
	}
	else{
	    System.out.println("usage: java SubstringSearch <filename> <pattern_1> <pattern_2> ... <pattern_n>.");
	}
    }
}
