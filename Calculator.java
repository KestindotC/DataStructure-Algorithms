/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kestin
 */

import java.util.Scanner;
public class Calculator {
    Double answer;
    private static Stack<String> ops  = new Stack<String>();
    private static Stack<Double> vals = new Stack<Double>();

    private boolean ignore(String x){
        return x.equals("(");
    }
    
    private boolean isPlus(String x){
        return x.equals("+");
    }

    private boolean isMinus (String x){
        return x.equals("-"); 
    }

    private boolean isTimes (String x){
        return x.equals("*"); 
    }

    private boolean isDivision (String x){
        return x.equals("/"); 
    }
    
    private void startCal(){
        String op = ops.pop();
        
        if (ignore(op))  ;
        else if (isPlus(op))    vals.push(vals.pop()+vals.pop());
        else if (isTimes(op)) vals.push(vals.pop() * vals.pop());
        else if (isMinus(op)) {
            Double nex = vals.pop();
            Double first = vals.pop();
            
            vals.push(first-nex);
        }
        else if (isDivision(op)) {
            Double nex = vals.pop();
            Double first = vals.pop();
            
            vals.push(first/nex);
        }
        
    }

    public Double ans (String e) {
        
        Scanner scanner = new Scanner(e).useDelimiter(" ");
        //System.out.println(scanner.next());
        while (scanner.hasNext()==true) {
            String s = scanner.next();
            //System.out.println(s);
            if (ignore(s)) ;
            else if (isPlus(s))    ops.push(s);          
            else if (isTimes(s))    ops.push(s);
            else if (isMinus(s))    ops.push(s);
            else if (isDivision(s))    ops.push(s);
            else if (s.equals(")"))    { startCal();          
            }
            else vals.push(Double.parseDouble(s));
           
        }  
       return vals.pop();
        //return vals.pop(); 
    }




    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* TODO code application logic here
        Calculator cct = new Calculator();
        String question = "( ( ( 1 + ( 12 * 5 ) ) - ( 3 * 4 ) ) + ( 4 / 5 ) )";
        cct.ans(question);
        */
    }


}
