import java.io.BufferedReader;
import java.io.FileReader;

public class Percolation {
 
    private boolean[][] matrix;
    private int size;
    private int pseu1id=0;
    private int pseu2id;
    private WeightedQuickUnionUF qf;

    //Creates N-by-N grid, with all sites blocked.
    public Percolation (int N) {
        size = N;
        pseu2id = size*size+1;
        qf = new WeightedQuickUnionUF(size*size+2);
        matrix = new boolean[size][size];
    }
    
    public void open (int i, int j){
        matrix[i-1][j-1] =true;
        if (i==1){
            qf.union(getQFIndex(i, j), pseu1id);
        }
        
        if (i==size) {
            qf.union(getQFIndex(i, j), pseu2id);
        }
        
        if (j>1 && isOpen(i, j-1)){
            qf.union(getQFIndex(i, j), getQFIndex(i, j - 1));
        }
        if (j < size && isOpen(i, j + 1)) {
            qf.union(getQFIndex(i, j), getQFIndex(i, j + 1));
        }
        if (i > 1 && isOpen(i - 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i - 1, j));
        }
        if (i < size && isOpen(i + 1, j)) {
            qf.union(getQFIndex(i, j), getQFIndex(i + 1, j));
        }
        
    }


    public static void main(String[] args) throws Exception{
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
        String matrixsize= br.readLine();
        int input = Integer.parseInt(matrixsize);
        Percolation perc =new Percolation(input);
        
        
        for(String[] line;( line = br.readLine().split(",")) != null; ) {
            int i = Integer.parseInt(line[0]);
            int j = Integer.parseInt(line[1]);
            perc.open(i,j);
            if (perc.percolates()==true){
                System.out.println(i+","+j);
                break;
            }      
        }
        
        
        } 
        catch (Exception ex) {
            System.out.println("-1");    
            } 
     }


    public boolean isOpen(int i, int j) {   
        return matrix[i-1][j-1];
    }
    
    public boolean percolates() {
        return qf.connected(pseu1id,pseu2id);
    }  
    
    
    private int getQFIndex (int i, int j){
        return (i-1)*size+j;
    }
    

}
    

