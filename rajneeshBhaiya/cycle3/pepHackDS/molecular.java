import java.util.Scanner; 
import java.util.Stack; 

public class molecular {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in); 
        String str = scn.nextLine();
        Stack<Character> operands = new Stack<>(); 
        Stack<Character> operator = new Stack<>(); 
        int n = str.length(); 

        long total = 0; 
        // System.out.println("i am here");

        long weight = 0; 
        for(int i =0; i<n; i++){
            char ch = str.charAt(i); 
            if(ch == '('){
                operator.push(ch); 
            } else if(isDigit(ch)){
                operands.push(ch); 
            } else if(ch == ')'){
                while(operator.peek()!='('){
                    char top = operands.pop();
                    operator.pop(); 
                    if(isDigit(top)){
                        char top2 = operands.pop(); 
                        weight+= calcWeight(top2)* (top-'0');
                    } else
                         weight+= calcWeight(top);
                }
                operator.pop();
                if(i+1<n && (isDigit(str.charAt(i+1)) )){
                    int val = str.charAt(i+1) - '0'; 
                    total += val*weight;
                    i++;
                } else{
                    total+=weight;
                }
                weight = 0; 
            } else {
                operands.push(ch); 
                if(operator.size()>0) operator.push(ch); 
            }
        }

        while(operands.size()>0){
            // System.out.println("i am here");
            char top = operands.pop();
            if(isDigit(top)){
                char top2 = operands.pop(); 
                total+= calcWeight(top2)* (top-'0');
            } else
                 total+= calcWeight(top);
        }

        System.out.println(total);

    }

    public static boolean isDigit(char ch ){
        if(ch >='2' && ch<='9' ) return true; 
        return false; 
    }

    public static int calcWeight(char ch){
        if(ch == 'H') return 1; 
        else if(ch == 'C') return 12; 
        else if(ch == 'O') return 16; 
        else return 0; 
    }
}
