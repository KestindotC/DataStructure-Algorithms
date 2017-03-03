import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
        
public class MyConvexHull {
    private static int readtimes;
    private static Double distance;
    private static WeightedQuickUnionUF uf;
    private static Point2D[] PointsArray;
    private static HashMap<Point2D,Integer> vertex;
    private static int[] idmap; 
    private static int CC =1;
    private static int CCnum;
    public static int sum=0;
    private static int result;
    
    
    private class CC {
    private ArrayList<Integer> cc= new ArrayList<Integer>();
    
    }
  
    public static int[] convertIntegers(List<Integer> integers){
    int[] ret = new int[integers.size()];
    Iterator<Integer> iterator = integers.iterator();
    for (int i = 0; i < ret.length; i++)
    {
        ret[i] = iterator.next().intValue();
    }
    return ret;
}
    
    public static int[] ConvexHullVertex(Point2D[] a) {
        //setID(a);
        ArrayList<Integer> ans = new ArrayList<>();       
        GrahamScan graham = new GrahamScan(a);
       
        for (Point2D p : graham.hull()){
           ans.add(vertex.get(p));}
        
        int [] A = new int[ans.size()];
        
        A=convertIntegers(ans);
        Arrays.sort(A);
        
        
        return A;
    }   
    
    private static void setID (Point2D[] c){
        int index = 0;
        int len = c.length;
        vertex= new HashMap<Point2D, Integer>();
        for(int times=0;times<len;times++){
        vertex.put(c[times], index);
        index++;
        }
    }
    
    private static int getID (Point2D x){
        int id = vertex.get(x);
        return id;
    }

    private static void UnionFilter(Point2D[] m){   
    uf = new WeightedQuickUnionUF(readtimes);   
    for (int i=0;i<readtimes-1;i++) {
        for(int j=i+1;j<readtimes;j++){
            //System.out.println(m[i].distanceTo(m[j]));
            if(m[i].distanceTo(m[j])<=distance) {
                //System.out.println(m[i].distanceTo(m[j]));
                
                int a=vertex.get(m[i]);
                int b=vertex.get(m[j]);
                //System.out.println(a+","+b);
                uf.union(a, b);
                                
            }          
        }        
    }    
}
    
    public static void CalaculateCC(/*Point2D[] a*/) {
       
        for (int u=0;u<readtimes;u++){
            idmap[u]=uf.find(u);
        }
//        HashMap<Integer,Integer> ccid = new  HashMap<Integer,Integer>();
//        
//        for(Point2D a: points){
//            int ida = uf.find(getID(a));
//            if (!ccid.containsKey(ida)) { cc=new CC(); }
//            else { ;
//                    
//                    }
//            
//        }
        
    }
    
    private static boolean alreadyCC(int x){
       return idmap[x]!=0;      
}

    private static void PointArrayProcessor(Point2D[] a){
        setID(a);
        idmap= new int[readtimes];       
        UnionFilter(a);
        
        
    }
    
    public static Point2D[] reverseHash2Point2Darray(ArrayList<Integer> n){
        int size = n.size();
        Point2D[] ConIn = new Point2D[size];
        for (int q=0;q<size;q++){
            Point2D p = PointsArray[n.get(q)];
            ConIn[q]=p;
        }
        return ConIn;
    }

    private static int SumConvexPoint (int[] R){
        
        sum=sum+R.length;
        return sum;
    }
   

    public static void main(String[] args) throws Exception {
        
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            
            // 3. find connected components (CCs) with a size >= 3
            // 4. for each CC, find its convex hull vertices by calling ConvexHullVertex(a[])
            // 5. count the number of points in N serving as a convex hull vertex, print it
            
            
            distance = Double.parseDouble(br.readLine());
            readtimes = Integer.parseInt(br.readLine());
            PointsArray= new Point2D[readtimes];
            int pos=0;
            
            for(String coordinate;( coordinate = br.readLine()) != null; ){
                String[] cor=coordinate.split(" ");
                Double x = Double.parseDouble(cor[0]);
                Double y = Double.parseDouble(cor[1]);
                Point2D p = new Point2D(x, y);
                PointsArray[pos]=p;
                pos++;
            }
            PointArrayProcessor(PointsArray);
            CalaculateCC();
            
            for (int itr=0;itr<readtimes;itr++){
                ArrayList<Integer> iArr = new ArrayList<Integer>();   
               // System.out.println(itr);
                for (int x=0;x<idmap.length;x++){
                    int y = idmap[x];
                    if (y==itr) {
                        iArr.add(x);
                   // System.out.println("This CC contains rootID : " + x);
                    }
                }
                
                if (iArr.size()<3) continue;
                //System.out.println("Change CC");
                int result;
                result=SumConvexPoint(ConvexHullVertex(reverseHash2Point2Darray(iArr)));
                MyConvexHull.result=result;
            }
        
                System.out.println(result);
            
        }
      
        
    }
}

    
    

