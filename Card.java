import java.util.ArrayList;
// add your own banner here

public class Card implements Comparable<Card>{
	
	private int suit; // use integers 1-4 to encode the suit
	private int rank; // use integers 1-13 to encode the rank
	private String[] suitArray = {"Clubs", "Diamonds", "Hearts", "Spades"};
    private String[] rankArray = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    
	public Card(int s, int r){
		//make a card with suit s and value v
        this.suit = s;
        this.rank = r;
	}
	
	public int compareTo(Card c){
		// use this method to compare cards so they may be easily sorted
        
        // same card = 0
        // card1>card2 = positive(1)
        // card1<card2 = negative(2)
        
        // get the implicit rank 
        if(this.rank == c.rank){
            return 0;
        }
        else if(this.rank > c.rank){
            return 1;
        }
        else if(this.rank < c.rank){
            return -1;
        }
        else{
            return 42;
        }
        

	}
	
	public String toString(){
		// use this method to easily print a Card object
        return rankArray[rank-1] + " of " + suitArray[suit - 1];
	}
    public int getSuit(){
        return suit; // int 1-4
    }
    public int getRank(){
        return rank; // int 1-13
    }

}
