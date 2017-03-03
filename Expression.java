import java.util.*;

/**
 *
 * @author User001
 */
public class Expression {

    private Node root;
    private Nodes first = null;
    private String newinput;
    private ArrayList<Node> prefix = new ArrayList<Node>();
    private ArrayList<Node> postfix = new ArrayList<Node>();
    private Stack<Node> nodestree = new Stack<Node>();

    // DO NOT MODIFY THIS
    public Expression(){}
   

    public String formulaparser(String infix){
        String pass="";
        String FINAL="";
        
        if(infix ==null){
            throw new java.lang.NullPointerException();
        }
        
        for(int i=0;i<infix.length();i++){
            if(infix.charAt(i)=='(' || infix.charAt(i)==')' ){
                if(!pass.equals("")){
                    FINAL=FINAL+pass+" ";
                    pass="";
                }
                FINAL=FINAL+(String.valueOf(infix.charAt(i)))+" ";
            }
            else if(infix.charAt(i)=='+' || infix.charAt(i)=='-' || infix.charAt(i)=='*' || infix.charAt(i)=='/'){
                if(!pass.equals("")){
                    FINAL=FINAL+pass+" ";
                    pass="";
                }
                FINAL=FINAL+(String.valueOf(infix.charAt(i)))+" ";
            }
            else{
                pass =pass+infix.charAt(i);
            }
        }
        return FINAL;
    }
    
    // Build a Binary and Return the Root
    public Node Infix2BT(String infix){
        if (infix == null) throw new NullPointerException();
        String newinput = formulaparser(infix);
        this.newinput = newinput;
        String[] scan;
        scan=newinput.split(" ");
        int len = scan.length;
        for (int unit = 0 ; unit<len ; unit++) {  
            if  (ignore(scan[unit])) ;
            else if (scan[unit].equals(")")) buildTree();                 
            else   newnode(scan[unit]) ;
        }
        
        return root;
    }
    
 
    //self --> left -->right
    public Node[] PrintPrefix(){
        throwIfNull(root);
        preprocessor(root);
        Node[] prefix = this.prefix.toArray(new Node[this.prefix.size()]);
        return prefix;
    }
    
    public void preprocessor(Node x){
        
        while ( x != null ){
            prefix.add(x);
            preprocessor ( x.getLeft() );
            x= x.getRight();           
        } 

    }
    
    //left -->right -->self
    public Node[] PrintPostfix(){
        throwIfNull(root);
        postprocessor(root);
        Node[] postfix = this.postfix.toArray(new  Node[this.postfix.size()]);
        return postfix;
    }
    
    public void postprocessor(Node y){
        
        if ( y != null ){
            postprocessor( y.getLeft() );
            postprocessor( y.getRight() );
            postfix.add(y);
        }       

    }

    public double Evaluation(){
        throwIfNull(root);
        String[] E;
        E = newinput.split(" ");
        double answer = 0;
        for(int i = 0;i < E.length;i++){
            Nodes oldfirst = first;
                first = new Nodes();
                first.item = E[i];
                first.next = oldfirst;
                if (E[i].equals(")")) {
                    double a = Double.parseDouble(first.next.next.next.item);
                    double b = Double.parseDouble(first.next.item);

                    if (isPlus(first.next.next.item))
                         answer = a + b ;
                    if (isMinus(first.next.next.item))
                         answer = a - b ;
                    if (isTimes(first.next.next.item))
                         answer = a * b ;
                    else if (isDivision(first.next.next.item))
                         answer = a / b ;

                    for(int j = 0;j < 5;j++)   
                        first = first.next;
                    String C =String.valueOf(answer);
                    oldfirst = first;
                    first = new Nodes();
                    first.item = C;
                    first.next = oldfirst;
                }
        }   
        return answer;
    }
    
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
    
    private void newnode(String x){
        Node n = new Node(null,null,x);
        nodestree.push(n);
    }
    
    private void buildTree(){
        if(nodestree.isEmpty()) {
            throw new NullPointerException();
        }
        Node last = nodestree.pop();
        Node second = nodestree.pop();
        Node first = nodestree.pop();
        
        second.setLeft(first);
        second.setRight(last);

        nodestree.push(second);
        root = second;
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Expression test = new Expression();
        String in = args[0];
        test.Infix2BT(in).getValue();
        test.Evaluation();        
        test.PrintPrefix();
        test.PrintPostfix();
        
    }
    
    private void throwIfNull(Node item) {
        if (item.getValue() == null) throw new NullPointerException();
    }
    
    
    private class Nodes {
        String item;
        Nodes next;}
    
}
