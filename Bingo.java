import java.io.FileReader;
import java.io.BufferedReader;

public class Bingo {
    
    
    public static void main(String[] args) throws Exception {

        // read file from args[0] in Java 7 style
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // read a line and split by ','
            String[] data = br.readLine().split(",");
            // store the first integer in variable stringCount (number of announced strings)
            int stringCount = Integer.parseInt(data[0]);

            // store the second integer in variable num (dimension of matrix: num * num)            
            int num = Integer.parseInt(data[1]);

            // initilization of a String array in Java
            String[] announce = new String[stringCount];
            String[][] matrix = new String[num][num];
            int[][] checkmatrix = new int[num][num];
            // printf in Java (you should comment out or delete this in your final submission)
            // System.out.printf("number of announced strings: %d\ndimension of matrix: %d x %d\n", stringCount, num, num);
            

            announce = br.readLine().split(",");
            
            for (int layer=0;layer<=num-1;layer++){
                String[] temp=(br.readLine().split(","));
                for (int co=0;co<=num-1;co++){
                    
            matrix[layer][co]=temp[co];
            }
            }
          
            for (int f1 = 0;f1<num;f1++){
                for (int f2=0;f2<num;f2++){
                    for (int condition=0;condition<stringCount;condition++){
                        if ((announce[condition]).equals(matrix[f1][f2])) {
                           checkmatrix[f1][f2]=1;
                        } 
                        else 
                        if (checkmatrix[f1][f2]!=0){
                        checkmatrix[f1][f2]=1;}                    
                }              
            }             
            }
            int line=0;           
            //rowlines
            for(int linecheck=0;linecheck<num;linecheck++){
                int summary=0;
                for(int columncheck=0;columncheck<num;columncheck++){
                        if( checkmatrix[linecheck][columncheck]!=0){
                            summary+=1;
                        } 
                }
                if (summary==num){
                    line=line+1;
                }                             
                
            }
            
            //columnlines
             for(int linecheck=0;linecheck<num;linecheck++){
                int summary=0;
                for(int columncheck=0;columncheck<num;columncheck++){
                        if( checkmatrix[columncheck][linecheck]!=0){
                            summary+=1;
                        } 
                }
                if (summary==num){
                    line=line+1;
                }                             
                
            }
             
             
             //Cross
             int summaryc1 =0;
             for(int linecheck=0;linecheck<num;linecheck++){
                    if(checkmatrix[linecheck][linecheck]!=0){
                            summaryc1+=1;
                        }             
             }
              if (summaryc1==num){
                    line=line+1;
                }           
             
             int summaryc2=0;
             for(int linecheck=0;linecheck<num;linecheck++){          
                    if(checkmatrix[linecheck][num-1-linecheck] !=0){
                            summaryc2+=1;
                        } 
             }
              if (summaryc2==num){
                    line=line+1;}                                  
            
                            System.out.println(line);
           
           
       
        }
            
    
        }
        
        }
           
