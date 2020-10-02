import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
         Scanner scn=new Scanner(System.in);
        String str=scn.nextLine();
        Stack<String> molecules=new Stack<>();
        int i=0;
          while(i<str.length()){
            char ch=str.charAt(i);
            if(ch=='(' || ch=='O' || ch=='C' || ch=='H'){
                molecules.push(ch+"");
                i++;
            }
            else if(ch>='1' && ch<='9'){
                if(molecules.peek().equals("O")){
                    molecules.pop();
                    int ans=16*(ch-'0');
                    molecules.push(ans+"");
                }
                if(molecules.peek().equals("H")){
                    molecules.pop();
                    int ans=1*(ch-'0');
                    molecules.push(ans+"");
                }
                if(molecules.peek().equals("C")){
                    molecules.pop();
                    int ans=12*(ch-'0');
                    molecules.push(ans+"");
                }
                i++;
            }
            else if(ch==')'){
                int ans=0;
                while(molecules.size()>0 && !molecules.peek().equals("(")){
                    //ans=ans+(molecules.pop()-'0');
                    String ch1=molecules.pop();
                    if(ch1.equals("O"))ans+=16;
                    else if(ch1.equals("C"))ans+=12;
                    else if(ch1.equals("H")) ans+=1;
                    else ans=ans+Integer.parseInt(ch1);
                }
                molecules.pop();
                if(i<str.length() && str.charAt(i+1)>='1' && str.charAt(i+1)<='9'){
                    ans=ans*(str.charAt(i+1)-'0');
                    molecules.push(ans+"");
                    i=i+2;
                }
                else{
                    molecules.push(ans+"");
                    i++;
                }
            }
        }
        
         int ans=0;
        while(molecules.size()>0){
                  String ch1=molecules.pop();
                    if(ch1.equals("O"))ans+=16;
                    else if(ch1.equals("C"))ans+=12;
                    else if(ch1.equals("H")) ans+=1;
                    else ans=ans+Integer.parseInt(ch1);
        }
        System.out.println(ans);
         
    }
}