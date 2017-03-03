/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kestin
 */
public class Node {
    private Node left;
    private Node right;
    private String value;

    public Node(Node left, Node right, String value){
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public Node getLeft(){
        return(this.left);
    }

    public Node getRight(){
        return(this.right);
    }

    public String getValue(){
        return(this.value);
    }

    public void setLeft(Node left){
        this.left = left;
    }

    public void setRight(Node right){
        this.right = right;
    }

    public void setValue(String value){
        this.value = value;
    }
    
}
