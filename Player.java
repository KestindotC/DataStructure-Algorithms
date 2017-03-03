import java.util.Arrays;
import java.util.*;

public class Player implements Comparable<Player>{ 

    private Card[] cards = new Card[5];
    private String name;
     
    // DO NOT MODIFY THIS
    public Player(String name) {
        this.name = name;
    }
     
    // DO NOT MODIFY THIS
    public String getName() {
        return this.name;
     }
     
    // DO NOT MODIFY THIS
    public void setCards(Card[] cards) {
        this.cards = cards;
    }
     
    // TODO 
    public int compareTo(Player that) {
        // complete this function so the Player can be sorted according to the cards he/she has.
        Arrays.sort(this.cards);
        Arrays.sort(that.cards);
        if(pokertype(this.cards)>pokertype(that.cards)) return +1;
        if(pokertype(this.cards)<pokertype(that.cards)) return -1;
        else special(this.cards,that.cards);
        return 0;
    }
    
    private int special(Card[] a, Card[] b){
        int which = pokertype(a);

        int[] facea = new int[5];
        for(int p=0;p<5;p++) facea[p]=faceind(a[p].getFace());
        int[] faceb = new int[5];
        for(int p=0;p<5;p++) faceb[p]=faceind(b[p].getFace());
        int[] ae = recordface(a);
        int[] be = recordface(b);
        int randoma=0;
        int randomb=0;
        
        if(which==6){
            MaxPQ<Card> pka = new MaxPQ<>();
            MaxPQ<Card> pkb = new MaxPQ<>();
            
            for (int each = 0 ;each< ae.length;each++){
                if (ae[each]==3) randoma=each;
            }
            for (int each = 0 ;each< be.length;each++){
                if (be[each]==3) randomb=each;
            }
            
            randoma=randoma+1;
            randomb=randomb+1;
            
            if (randoma>randomb) return +1;
            if (randoma<randomb) return -1;
            else {
                for(int p=0;p<5;p++){
                    int e=facea[p];
                    int w=facea[p];
                    if (e==randoma) pka.insert(a[p]);
                    if (w==randomb) pkb.insert(b[p]);
                }

                pka.delMax().compareTo(pkb.delMax());
            }
        }
        
        if (which==5){
            a[4].compareTo(b[4]);
        }
        
        
        if (which==4){
            for(Card x:a){
                    if (x.getFace().equals("A")) randoma=randoma+1;
                    if (x.getFace().equals("2")) randoma=randoma+1;
                    if (x.getFace().equals("3")) randoma=randoma+1;
                    if (x.getFace().equals("4")) randoma=randoma+1;
                    if (x.getFace().equals("5")) randoma=randoma+1;
                }
                if (randoma==5) return -1;
            for(Card x:b){
                    if (x.getFace().equals("A")) randomb=randomb+1;
                    if (x.getFace().equals("2")) randomb=randomb+1;
                    if (x.getFace().equals("3")) randomb=randomb+1;
                    if (x.getFace().equals("4")) randomb=randomb+1;
                    if (x.getFace().equals("5")) randomb=randomb+1;
            }
                if (randomb==5) return -1;
            
                else a[4].compareTo(b[4]);   
        }
            
        if (which==3){
            MaxPQ<Card> pka = new MaxPQ<>();
            MaxPQ<Card> pkb = new MaxPQ<>();
            MaxPQ<Integer> Aa = new MaxPQ<>();
            MaxPQ<Integer> Bb = new MaxPQ<>();
            for (int each = 0 ;each< ae.length;each++){
                if (ae[each]==2) Aa.insert(each);
            }
            for (int each = 0 ;each< be.length;each++){
                if (be[each]==2) Bb.insert(each);
            }
            randoma=Aa.max()+1;
            randomb=Bb.max()+1;
            int ans = Aa.delMax().compareTo(Bb.delMax());
            if (ans==0){ 
                for (int p= 0 ;p<5;p++){
                    if (facea[p]==randoma) pka.insert(a[p]);
                    if (faceb[p]==randomb) pkb.insert(b[p]);
                }
                pka.delMax().compareTo(pkb.delMax());
            }
            else return ans;
             
        }
        
        if (which==2){
            MaxPQ<Card> pka = new MaxPQ<>();
            MaxPQ<Card> pkb = new MaxPQ<>();
            for (int each = 0 ;each< ae.length;each++){
                if (ae[each]==2) randoma=each;
            }
            for (int each = 0 ;each< be.length;each++){
                if (be[each]==2) randomb=each;
            }
            randoma=randoma+1;
            randomb=randomb+1;
            if (randoma>randomb) return +1;
            if (randoma<randomb) return -1;
            if (randoma-randomb==0){ 
                for (int p= 0 ;p<5;p++){
                    if (facea[p]==randoma) pka.insert(a[p]);
                    if (faceb[p]==randomb) pkb.insert(b[p]);
                }
                pka.delMax().compareTo(pkb.delMax());
            }
            return -100;
        }
        
        if (which==1){
            a[4].compareTo(b[4]);
        }
        
        
        
        return 0;
    }
    // to read the pokertype in each player
    private int pokertype(Card[] a) {
        int flag=0;
        if(twopair(a)!=-1) flag=twopair(a);
        if(onepair(a)!=-1) flag=onepair(a);
        if(straight(a)!=-1) flag=straight(a);
        if(flush(a)!=-1) flag=flush(a);
        if(fullhouse(a)!=-1) flag=fullhouse(a);
        if(highcard(a)!=-1) flag=highcard(a);
        return flag;
    }
    
    private int highcard(Card[] c){
        int no=0;
        if(fullhouse(c)==-1) no=no+1;
        if(flush(c)==-1) no=no+1;
        if(straight(c)==-1) no=no+1;
        if(twopair(c)==-1) no=no+1;
        if(onepair(c)==-1) no=no+1;
        if(no==5) return 1;
        return -1;
    }
    
    private int onepair(Card[] c){
        int[] temp = recordface(c);
        int pair=0;
        int single=0;
        for (int each : temp){
            if (each==2) pair=pair+1;
            if (each==1) single=single+1;
        }
        if (pair==1 && single==3) return 2;
        return -1;
    }
    
    private int twopair(Card[] c){
        int[] temp = recordface(c);
        int sum=0;
        for (int each : temp){
            if (each==2) sum=sum+1;
        }
        if (sum==2) return 3;
        return -1;
    }
    
    private int straight(Card[] c){
        int[] face = new int[5];
        for(int scan=0;scan<c.length;scan++){
            face[scan]=faceind(c[scan].getFace());
        }
        int sum=0;
        for (int r=0;r<4;r++){
            if (face[r+1]-face[r]==1) sum=sum+1;
        }
        if (sum>=4) return 4;
        if (sum==3){
            if(face[4]==14) return 4;
        }
        return -1;
    }
    
    private int flush(Card[] c){
        int[] temp = recordsuit(c);
        for (int each : temp){
            if (each==5) return 5;
        }
        return -1;
    }
    
    private int fullhouse(Card[] c){
        int[] temp = recordface(c);
        for (int each : temp){
            if (each==3) return 6;
        }
        return -1;
    }
    
    private int[] recordface(Card[] a){
        int[] recording= new int[14];
        
        for(int i=0;i<5;i++){
            recording[faceind(a[i].getFace())-1]=recording[faceind(a[i].getFace())-1]+1;      
        }
        return recording;
    }
    
    private int[] recordsuit(Card[] a){       
        int[] suitrecord=new int[4];
                
        for(int i=0;i<5;i++){
            suitrecord[suitind(a[i].getSuit())-1]=suitrecord[suitind(a[i].getSuit())-1]+1;
        }
        return suitrecord;
    }
    
    
    
    
    private int suitind(String a){
        if (a.equals("Spades")) return 4;
        if (a.equals("Hearts")) return 3;
        if (a.equals("Diamonds")) return 2;
        if (a.equals("Clubs")) return 1;
        return 0;
        
    }
    
    public int faceind(String a){
        if (a.equals("A")) return 14;
        if (a.equals("2")) return 2;
        if (a.equals("3")) return 3;
        if (a.equals("4")) return 4;
        if (a.equals("5")) return 5;
        if (a.equals("6")) return 6;
        if (a.equals("7")) return 7;
        if (a.equals("8")) return 8;
        if (a.equals("9")) return 9;
        if (a.equals("10")) return 10;
        if (a.equals("J")) return 11;
        if (a.equals("Q")) return 12;
        if (a.equals("K")) return 13;
        return 0;
    }



}



