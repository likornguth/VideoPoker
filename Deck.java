import java.util.ArrayList;
import java.util.Random;
// add your own banner here

public class Deck {
	
	private Card[] cards;
        
	private int top; // the index of the top of the deck
    
    private Card topCard;

	// add more instance variables if needed
	
	public Deck(){
		// make a 52 card deck here
        cards = new Card[52]; 
        //top = cards[51];
        top = cards.length - 1;
        topCard = cards[top];
	}
	
	public void shuffle(){
		// shuffle the deck here
        Random rand = new Random();
        ArrayList<Integer> shuffledDeck = new ArrayList<Integer>();
        // While loop will iterate through, filling a new ArrayList of the same size as cards
        // It stops only when the 51st spot has been assigned a value
        while(shuffledDeck.size() < 52){
            int r = rand.nextInt(52);
            // conditional tests to see if we have already added card with value r to the deck
            // card will only be added to the deck if this condition is FALSE
            if(!shuffledDeck.contains(r)){
                shuffledDeck.add(r);
            }

            
        }
    // Now, we fill the cards array with each element of the shuffledDeck arrayList we just created
    // (value/13) + 1 will return an int 1-4 which corresponds to the suit
    // (value%13) + 1 will return an int 1-13 which corresponds to rank   
        for(int i = 0; i < 52; i++){
            int value = shuffledDeck.get(i);
            cards[i] = new Card((value/13) + 1, (value%13)+1);
        }
        

	}
	
	public Card deal(){
		// deal the top card in the deck
        
        topCard = cards[top];
        Card dealed = topCard;
        top--;
        return dealed;
        
	}
	
	// add more methods here if needed

}
