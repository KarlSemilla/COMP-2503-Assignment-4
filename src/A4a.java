import java.util.Collections;
import java.util.Scanner;
public class A4a {
	private Scanner in = new Scanner(System.in);
	private int totalwordcount;
	private int stopwordcount;
	
	private String[] stopwords = { 
		"a", "about",  "all", "am", "an", "and",  "any", "are", "as", "at", 
		"be", "been", "but", "by", "can", "cannot", "could", "did", "do", "does", 
		"else", "for", "from", "get", "got", "had", "has", "have", "he", "her", 	
		"hers", "him", "his", "how", "i", "if", "in", "into", "is", "it", 
		"its", "like", "more", "me", "my", "no", "now", "not", "of", "on", 
		"one", "or", "our", "out", "said", "say", "says", "she", "so", "some",
		"than", "that", "thats", "the", "their", "them", "then", "there", "these", "they", "this", 
		"to", "too", "us", "upon", "was", "we", "were", "what", "with", "when", 
		"where", "which", "while", "who", "whom", "why", "will", "you", "your", "up", "down", "left", "right",
		"man", "woman", "would", "should", "dont", "after", "before", "im", "men"
   };
	
	private BST<Word> words = new BST<Word>();
	
	public static void main(String args[]) 
	{
		A4a a4 = new A4a();
		a4.run();
	}
	
	public void run() 
	{
		read();
	}
	
	public void read() 
	{
		for(int i = 0; i < stopwords.length; i++) {
			Word s = new Word(stopwords[i]);
			words.add(s);
		}
		
		System.out.println("Hit");
		while(in.hasNext()) {
	        String word = in.next().toLowerCase().trim().replaceAll( "[^a-z]","");
	        if(word.length() > 0) {
		        totalwordcount++;
		        Word w = new Word(word);
			    words.add(w);
			    System.out.println(words.size());
	        }
	    }
		
		for(int i = 0; i < stopwords.length; i++) {
			Word s = new Word(stopwords[i]);
			words.deleteData(s);
		}
		
		in.close();
		
		for(int i = 0; i < stopwords.length; i++) {
			Word s = new Word(stopwords[i]);
			words.deleteData(s);
		}
	}
	
	   /** 
	   List the first n words in the list of words.
	   @param n the number of words to list
	   */
	   private void printWords( int n) 
	   {
	      int i = 0;
	      while ( i < words.size() && i < n) 
	      {
	         System.out.println(words.getData());
	         i++;
	      }
	   }
	
	  private void printResults() 
	   {
	       System.out.println( "Total Words: " + totalwordcount);
	       System.out.println( "Unique Words: " + words.size()); 
	       System.out.println( "Stop Words: " + stopwordcount);
	       System.out.println();
	       System.out.println( "10 Most Frequent");
	       Collections.sort( words, Word.CompFreqDesc); 
	       printWords(10); 
	       System.out.println();
	       System.out.println( "10 Least Frequent");
	       Collections.sort( words, Word.CompFreqAsc); 
	       printWords(10);
	       System.out.println();
	       System.out.println( "All");
	       Collections.sort( words); 
	       printWords( words.size());
	   }
}
