import java.io.*;
import java.util.*;

public class Main {

	public static int solution(String[] words, int[] farr, int[] score, int idx) {
		//write your code here
		
		if(idx==words.length){
		    return 0;
		}
		
		int sno = 0 + solution(words,farr,score,idx+1); // not include
		// word to include 
		int sword = 0; // score word 
		String word = words[idx];
		boolean flag = true;
		for(int i=0;i<word.length();i++){
		    char ch = word.charAt(i);
		    if(farr[ch-'a']==0){
		        flag = false;
		        //can not put break here, otherwise some freq will be changed
		        // ans we even don't know whose
		    }
		    farr[ch-'a']--;
		    sword+=score[ch-'a'];
		}
		int syes = 0;
		if(flag){
		    syes = sword + solution(words,farr,score,idx+1);
		}
		for(int i=0;i<word.length();i++){
		    char ch = word.charAt(i);
		    farr[ch-'a']++;
		}
		
		return Math.max(sno,syes);
	}


	/********************************************************************************** */

	// method 2 -> find combinations using for loop.

	public static int solution2(String[] words, int[] farr, int[] score, int idx) {
		//write your code here
		int tscore = 0;
		for(int i=idx;i<words.length;i++){
		    String word = words[i];
		    boolean flag = true;
		    int myScore = 0;
		    for(int j=0;j<word.length();j++){
		        char ch = word.charAt(j);
		        if(farr[ch-'a']==0){
		            flag = false;
		        }
		        farr[ch-'a']--;
		        myScore+=score[ch-'a'];
		    }
		    
		    if(flag){
		        tscore = Math.max(tscore, myScore+solution(words,farr,score,idx+1));
		    }
		    for(int j=0;j<word.length();j++){
		        char ch = word.charAt(j);
		        farr[ch-'a']++;
		    }
		}
		
		return tscore;
	}

	/**************************************************************************************** */

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		int nofWords = scn.nextInt();
		String[] words = new String[nofWords];
		for(int i = 0 ; i < words.length; i++) {
			words[i] = scn.next();
		}
		int nofLetters = scn.nextInt();
		char[] letters = new char[nofLetters];
		for (int i = 0; i < letters.length; i++) {
			letters[i] = scn.next().charAt(0);
		}
		int[] score = new int[26];
		for (int i = 0; i < score.length; i++) {
			score[i] = scn.nextInt();
		}
		if (words == null || words.length == 0 || letters == null || letters.length == 0 || score == null
				|| score.length == 0) {
			System.out.println(0);
			return;
		}
		int[] farr = new int[score.length];
		for (char ch : letters) {
			farr[ch - 'a']++;
		}
		System.out.println(solution(words, farr, score, 0));

	}
}