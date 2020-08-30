import java.io.*;
import java.util.*;

public class Main {

	public static int solution(String[] words, int[] farr, int[] score, int idx) {
		//write your code here
		// no base case needed.
		
		int ans = Integer.MIN_VALUE;
		for(int i=0;i<words.length;i++){
		    String word = words[i];
		    int myScore = 0;
		    boolean res = true;
		    for(int j=0;j<word.length();j++){
		        char ch = word.charAt(j);
		        if(farr[ch-'0']>0){
		            farr[ch-'0']--;
		            myScore+=score[ch-'0'];
		        } else
		            res = false;
		    }
		    if(res){
		        ans = Math.max(ans,solution(words,farr,score,idx+1));
		    }
		   
		}
		return ans;
	}

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