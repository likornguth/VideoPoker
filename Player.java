import java.util.Scanner;
import java.util.ArrayList;
// add your own banner here

public class Player {
	
		
	private ArrayList<Card> hand; // the player's cards
	private double bankroll;
    private double bet;

		
	public Player(){		
	    // create a player here
        hand = new ArrayList<Card>();
        this.bankroll = 0;
        this.bet = 0;
	}

	public void addCard(Card c){
	    // add the card c to the player's hand
        hand.add(c);
        System.out.println( c.toString());
	}

	public void removeCard(Card c){
	    // remove the card c from the player's hand
        hand.remove(c);
        System.out.println("removed: " + c.toString());
        }
		
        public void bets(double amt){
            // player makes a bet
            bet = amt;
            
        }

        public void winnings(double odds){
            //	adjust bankroll if player wins

            bankroll = odds * bet;
        }

        public double getBankroll(){
            // return current balance of bankroll
            return bankroll;
        }
    
    public ArrayList<Card> getHand(){
        //. returns an ArrayList of the player's current hand of 5 cards
        ArrayList<Card> handList = hand;
        return handList;
        
    }

        // you may wish to use more methods here
}


