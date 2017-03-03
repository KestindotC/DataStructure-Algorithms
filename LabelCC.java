import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;


public class LabelCC {
	
	public static int[][] matrix;
	public static int size;
	public QuickUnionUF qf;
	public int id=1;
	

	public LabelCC (int n){
		size=n;
		matrix = new int[size][size];
		for(int row[]: matrix)
			Arrays.fill(row, 1);
		qf = new QuickUnionUF(size*size);                              
		
	}


	public void Blocked(int x,int y) {
		matrix [x-1][y-1]=0;
	}

	public boolean isOpen(int x,int y){
		return matrix[x-1][y-1]!=0;
	}

	private int setID (int i, int j){
		return id;
	}

	public void targetid (int i, int j) {
		int tar =matrix[i-1][j-1];
		if (tar==0){
			System.out.println(0);
		}
		else{
			int ans=matrix[i-1][j-1];
			System.out.println(qf.find(ans));   
			
		}
	}


	public void getIndex(int x, int y){
		if(isOpen(x,y)!=false){
			if ( x==1 && isOpen(x,y-1)!=true || y==1 && isOpen(x-1,y)!=true || x!=1 && y!=1 && isOpen(x-1,y)!=true && isOpen(x,y-1)!=true){
				id++;
				matrix[x-1][y-1]=setID(x,y);
				
			}
			else {
				if (y==1 && isOpen(x-1,y)==true || x!=1 &&y!=1 && isOpen(x-1,y)==true && isOpen(x,y-1)!=true){
					matrix[x-1][y-1]=(matrix[x-2][y-1]);
				}
				else{
					if (x==1 && isOpen(x,y-1)==true || x!=1 && y!=1 && isOpen(x,y-1)==true && isOpen(x-1,y)!=true) {
						matrix[x-1][y-1]=matrix[x-1][y-2];
					}
					else{

						int a =Math.max((matrix[x-2][y-1]),(matrix[x-1][y-2]));
						int b =Math.min((matrix[x-2][y-1]),(matrix[x-1][y-2]));
						matrix[x-1][y-1]=b;
						qf.union(a,b);
					}
				}
			} 
		}
		else
			matrix[x-1][y-1]=0;
	}
	

	public boolean initiation(){
		int ini =matrix[0][0];
		return ini==0;
	}
	
	
	public static void main(String[] args) throws Exception {
		try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
			String[] hintline=br.readLine().split(",");
			int input = Integer.parseInt(hintline[0]);
			int i = Integer.parseInt(hintline[1]);
			int j= Integer.parseInt(hintline[2]);
			LabelCC test = new LabelCC(input);
			

			for(String line;(line = br.readLine())!=(null); ){
				
				String[] coordinate=line.split(",");
				int x = Integer.parseInt(coordinate[0]);        
				int y = Integer.parseInt(coordinate[1]);
				test.Blocked(x, y);
				
			}
			
			for (int m=1;m<=size;m++){
				for (int n=1;n<=size;n++){
					
					if (m==1&& n==1 &&test.initiation()==false){
						matrix[0][0]=1;
						continue;
					}
					else
                              //System.out.println(m+","+n);
						
						test.getIndex(m, n);
					
					
				}
			}
			
			
			test.targetid(i, j);
			
			
		}
	}
	
}