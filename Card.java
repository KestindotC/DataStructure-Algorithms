import java.util.Comparator;
public class Card implements Comparable<Card> {

	private String face; // should be one of [A, 2, 3, 4, 5, 6, 7, 8, 9, 10, J, Q, K]
	private String suit; // should be one of [Spades, Hearts, Diamonds, Clubs]
	
    public static final Comparator<Card> SUIT_ORDER = new SuitOrder();

    // DO NOT MODIFY THIS
    public Card(String face, String suit){
        this.face = face;
        this.suit = suit;
    }
     
    // DO NOT MODIFY THIS   
    public String getFace(){
        return this.face;
    }
    
    // DO NOT MODIFY THIS    
    public String getSuit(){
        return this.suit;
    }   
    
    // TODO
    public int compareTo(Card that) {      
        int ans = facecmp(that);
        if (ans==0) return suitcmp(that);
        else return ans;
        // complete this function so the Card can be sorted
        // (you must consider both face and suit)
    }  

    // TODO
    private static class SuitOrder implements Comparator<Card> {
        public int compare(Card c1, Card c2) {
            return c1.suitcmp(c2);           
            
        }
    }
    private int suitcmp(Card tae){      
        if (this.suitind()> tae.suitind()) return +1;
        if (this.suitind()< tae.suitind()) return -1;       
        return 0;
    }
    
    private int facecmp(Card card){
        if (this.faceind()> card.faceind()) return +1;
        if (this.faceind()< card.faceind()) return -1;
        return 0;
    }
    
    public int faceind(){
        if (face.equals("A")) return 14;
        if (face.equals("2")) return 2;
        if (face.equals("3")) return 3;
        if (face.equals("4")) return 4;
        if (face.equals("5")) return 5;
        if (face.equals("6")) return 6;
        if (face.equals("7")) return 7;
        if (face.equals("8")) return 8;
        if (face.equals("9")) return 9;
        if (face.equals("10")) return 10;
        if (face.equals("J")) return 11;
        if (face.equals("Q")) return 12;
        if (face.equals("K")) return 13;
        return 0;
    }
    
    public int suitind(){
        if (this.suit.equals("Spades")) return 4;
        if (this.suit.equals("Hearts")) return 3;
        if (this.suit.equals("Diamonds")) return 2;
        if (this.suit.equals("Clubs")) return 1;
        return 0;
        
    }
    
    
}

