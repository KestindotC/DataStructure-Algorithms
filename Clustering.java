import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Clustering {

    private static int Pointnum;
    private static Point2D[] pointsarray;
    private MinPQ<Pair> pq = new MinPQ<Pair>();
    public static ArrayList<Cluster> clustercollector;
    public static Set<Point2D> clustercenter;
    
    public static class Pair implements Comparable<Pair>{
        Double dis;
        Point2D A,B;
        Cluster x,y;
        
        public Pair(Cluster a,Cluster b){
            this.A=a.getCenter();
            this.B=b.getCenter();
            this.dis=a.distanceTo(b);
            this.x=a;
            this.y=b;
        }
        
        private Double Distance(){
            return dis;
        }
        
        private Point2D[] getPair(){
            Point2D[] ans = new Point2D[2];
            ans[0]=this.A;
            ans[1]=this.B;
            return ans;
        }
        
        private Cluster[] getCluster(){
            Cluster[] ans = new Cluster[2];
            ans[0]=this.x;
            ans[1]=this.y;
            return ans;
        }
        
        private boolean needtocloned(Pair x){
            
            if(!clustercenter.contains(x.A) || !clustercenter.contains(x.B)) return false;
            return true;
           
        }
        
        public int compareTo(Pair that){
            Double ans = that.Distance();
            if(this.dis>ans) return +1;
            if(this.dis<ans) return -1;
            return 0;
        }
        
    }

    public static class Cluster implements Comparable<Cluster>{
        private ArrayList<Point2D> cluster;
        private Point2D CenterG;
        private int howmanypoints;

        public Cluster(){}
        
        private Cluster(Point2D a){
            cluster= new ArrayList<Point2D>();
            this.CenterG=a;
            this.howmanypoints=1;
        }

        public Point2D getCenter(){
            return this.CenterG;
        }

        public int getsize(){
            return this.howmanypoints;
        }
        
        public double distanceTo(Cluster that){
            return this.getCenter().distanceTo(that.getCenter());           
        }


        private static Cluster UNION(Cluster[] arr) {
            Cluster a =arr[0];
            Cluster b =arr[1];
            //System.out.println("union!");
            double x1,x2,y1,y2;
            int SIZE = a.getsize()+b.getsize();

            x1=a.getCenter().x();
            y1=a.getCenter().y();
            x2=b.getCenter().x();
            y2=b.getCenter().y();
            Point2D newp = new Point2D((x1*a.getsize()+x2*b.getsize())/SIZE,(y1*a.getsize()+y2*b.getsize())/SIZE);
            
            Cluster n = new Cluster(newp);
            n.howmanypoints=SIZE;
            for(int i = 0;i < a.getsize();i++){            
                n.cluster.add(a.cluster.get(i));
            }
            clustercollector.remove(a);
            
            for(int i = 0;i < b.getsize();i++){
                n.cluster.add(b.cluster.get(i));
            }
            clustercollector.remove(b);
            
            clustercollector.add(n);
            clustercenter.remove(a.getCenter());
            clustercenter.remove(b.getCenter());
            clustercenter.add(newp);

            return n;
        }
        
        
        public int compareTo(Cluster that){
            int ans = that.getsize();
            if(this.howmanypoints >ans) return +1;
            if(this.howmanypoints<ans) return -1;
            return 0;
        }
        
    
    }
    
    public void ScanDistance(Cluster x){       
        for(int t=0;t<clustercollector.size()-1;t++){
            Cluster s =clustercollector.get(t);
            Pair ans = new Pair(s, x);
            pq.insert(ans);
        }
       
    }

    private void pointOUT(){
        MaxPQ<Cluster> mine = new MaxPQ<>();
        
        for (Cluster clustercollector1 : clustercollector) {
            mine.insert(clustercollector1);
        }
       
        
        for(int outprint=0;outprint < mine.size()+2;outprint++){
            Cluster ans =mine.delMax();
            System.out.println(ans.getsize()+" "+String.format("%.2f",ans.CenterG.x())+" "+String.format("%.2f",ans.CenterG.y()));
        }
        
        Lastout();       
    }
    
     private void Lastout(){
         MinPQ<Double> m = new MinPQ<Double>();
         
         Point2D[] a = clustercollector.get(0).cluster.toArray(new Point2D[clustercollector.get(0).getsize()]);
         Point2D[] b = clustercollector.get(1).cluster.toArray(new Point2D[clustercollector.get(1).getsize()]);
         Point2D[] c = clustercollector.get(2).cluster.toArray(new Point2D[clustercollector.get(2).getsize()]);
        
         
         for (int i = 0 ; i< a.length ; i++){
             for(Point2D those :b){
                 m.insert(a[i].distanceTo(those));
             }
         }
         
         for (int i = 0 ; i< a.length ; i++){
             for(Point2D those :c){
                 m.insert(a[i].distanceTo(those));
             }
         }
         
         for (int i = 0 ; i< b.length ; i++){
             for(Point2D those :c){
                 m.insert(b[i].distanceTo(those));
             }
         }
        
         Double ans =m.delMin();
         System.out.println(String.format("%.2f",ans));
     }
    
    private void PriorQprocessor(){
        
        int count=0;
        
            while (clustercollector.size()>3){
                Pair needclone = pq.delMin();  
                count++;
                //System.out.println(clustercollector.size());
                if (needclone.needtocloned(needclone)){               
                   ScanDistance(Cluster.UNION(needclone.getCluster()));                  
                }
//                if (count==1) {
//                    System.out.println("stop");
//                }
            }
     }

    private void start(){
        clustercollector = new ArrayList<Cluster>();
        clustercenter = new HashSet<Point2D>();
        int count=0;
        for(int i =0;i<pointsarray.length; i++) {
            Point2D points = pointsarray[i];
            Cluster nc = new Cluster(points);
            nc.cluster.add(points);
            
            clustercollector.add(nc);
            clustercenter.add(points);
            count++;
        }          
        
        for(int ind =0 ;ind<clustercollector.size()-1;ind++){
            for(int nextind = ind+1; nextind<clustercollector.size();nextind++){
                Pair trypair = new Pair(clustercollector.get(ind),clustercollector.get(nextind));
                pq.insert(trypair);
                
            }   
        }
        PriorQprocessor();
        
    }

    public static void main(String[] args) throws Exception {
            // TODO code application logic here
               
        try(BufferedReader br = new BufferedReader(new FileReader(args[0]))){

            int Num_point = Integer.parseInt(br.readLine());
            Clustering program = new Clustering();

            program.Pointnum=Num_point;       
            program.pointsarray = new Point2D[Pointnum];
            int time = 0;

            for(String points;(points = br.readLine()) != null; ){
                String[] cor=points.split(" ");
                Double x = Double.parseDouble(cor[0]);
                Double y = Double.parseDouble(cor[1]);
                Point2D w = new Point2D(x, y);               
                pointsarray[time]=w;
                time++;
            }

            if (program.Pointnum<=3){
                program.Justout(pointsarray);
                program.Justdis();
            }
            else{
                program.start();
                program.pointOUT();
            }
        }
    }
    public void Justout(Point2D[] s){
        Insertion.sort(s);
        for(int x = 0 ; x < s.length ; x++){
            double ax = s[x].x();
            double ay = s[x].y();
        System.out.println("1 "+" "+String.format("%.2f",ax)+" "+String.format("%.2f",ay));
        
        }
    }
    
    public void Justdis(){
        MinPQ<Double> haha = new MinPQ<Double>();
        for(int x = 0 ;x <pointsarray.length-1; x++){
            for(int y =x +1;y<pointsarray.length;y++){
                haha.insert(pointsarray[x].distanceTo(pointsarray[y]));
            }     
        }
        System.out.println(String.format("%.2f",haha.delMin())); 
    }
    
    
}




