import java.util.Random;
import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;
// add your own banner here

public class Game {
	
	private Player p;
	private Deck cards;
    private ArrayList<Card> playerHand;
    private int numReplaced;
    private double winnings;
    private int numPairs = 0;
    private int numTrios = 0;
    private int numQuads = 0;
    private String result = "none";
    private boolean flush = false;
    private boolean straight = false;
    private boolean matchFound = false;
    private boolean royal = false;
	
	
	public Game(String[] testHand){
        
        // turn user input array into program readable arraylist
        // call checkHand(ArrayList<Card> () ) with new array list as parameter
        // Pray to god it does its job.
        String cardString = "";
        String suitString = "";
        int suitCode = 0;
        String rankString = "";
        int rankCode = 0;
        testHand = new String[5];
        Scanner a = new Scanner(System.in);
        System.out.print("Enter card 1: ");
        int w = 0;
        while(w < testHand.length){
            
            testHand[w] = a.nextLine();
            w += 1;
            if(w < testHand.length){
                System.out.print("Enter card " + (w+1) + ": ");
            }
            
            
        }
        
        System.out.println("hand: ");
        System.out.print("{" + testHand[0] + "," +  testHand[1] + "," +  testHand[2] + "," +  testHand[3] + "," +  testHand[4] + "}");
        System.out.println("");
        ArrayList<Card> x = new ArrayList<Card>();
        for(int i = 0; i<testHand.length; i++){
            cardString = testHand[i];
            char suitChar = cardString.charAt(0);
            suitCode = suitConvert(suitChar); 
            
            if(cardString.length() < 3){
                rankString = String.valueOf(cardString.charAt(1));
                rankCode = Integer.parseInt(rankString);
            }
            
            else{
                rankString = String.valueOf(cardString.charAt(1)) + String.valueOf(cardString.charAt(2));
                rankCode = Integer.parseInt(rankString);
                //System.out.println("rank: " + rankCode);
            }
            Card c = new Card(suitCode, rankCode);
            //System.out.println("card: " + c.toString());
            x.add(c);
        }
        playerHand = x;
        System.out.println(playerHand);
        checkHand(playerHand);
        //TESTINGOUT
        System.out.println("two of a kind: " + numPairs);
        System.out.println("three of a kind: " + numTrios);
        System.out.println("four of a kind: " + numQuads);
        System.out.println("Flush: " + flush);
        System.out.println("Straight: " + straight);
        System.out.println("Royal Flush: " + royal);
        System.out.println("result: " + result);
        System.exit(0);
	
		
	}
	
	public Game(){
		p = new Player();
        cards = new Deck();
	}
	
	public void play(){
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Welcome to Poker!");
        cards.shuffle();
        System.out.println("How much would you like to bet? (1-5 tokens)");
        Scanner a = new Scanner(System.in);
        int num = a.nextInt();
        if(num > 0 && num <= 5){
                p.bets(num);
            }
        System.out.println("Your bet: " + num + " tokens");
        System.out.println("");
        System.out.println("Take a look at your hand:");
        for(int i=0; i < 5; i++){
            Card newCard = cards.deal();
            System.out.print((i+1) + ".");
            p.addCard(newCard);
        }
        // ask player which cards they want to return (none, some or all), and then removes them from player hand
        // then replaces them with new cards from the top of the deck
        System.out.println("");
        System.out.println("Which card would you like to replace(1-5)? (press X when done)");
        numReplaced = 0;
        Scanner b = new Scanner(System.in);
        playerHand = p.getHand();
        while(b.hasNextInt()){
            // while user is entering input, create an array list of players current hand, reject chosen cards, keep track of how many
            // cards the program should deal out after
             // maybe keep thi global
            Card rejected = playerHand.get(b.nextInt() - 1); // HELP: can't figure out how to get an item from player hand to remove it
            p.removeCard(rejected);
            System.out.println(playerHand);
            System.out.println("Which card would you like to replace(1-" + playerHand.size() + ")?  (press X when done)");
            numReplaced += 1;
        }
        // Deal out the same amount of cards that the player rejected.  Hand should be back at 5 cards
        for(int k = 0; k < numReplaced; k++){
            System.out.println("Dealed: ");
            p.addCard(cards.deal());
        }
        ArrayList<Card> finalHand = p.getHand();
        System.out.println("Your final hand: " + finalHand);
        System.out.println("--------------------------------------------------------------------------------------------");
        System.out.println("Result: " + checkHand(finalHand));
        
        double payout = 0;
            if(result == "No Pair") {
                payout = 0;               
            }
            else if(result == "One Pair"){
                payout = 1;
            }
            else if(result == "Two Pairs"){
                payout = 2;
            }
            else if(result == "Three of a kind"){
                payout = 3;
            }
            else if(result == "Straight"){
                payout= 4;
            }
            else if(result == "Flush"){
                payout = 5;
            }
            else if(result == "Four of a kind"){
                payout = 6;
            }
            else if(result == "Straight Flush"){
                payout = 25;
            }
            else if(result == "Full House"){
                payout = 50;
            }
            else if(result == "Royal Flush"){
                payout = 250;
            }
        p.winnings(payout);
        winnings = p.getBankroll();
        if(winnings != 0){
            System.out.println("Congratulations!");
        }
        else{
            System.out.println("Luck of the draw... Better luck next time!");
        }
        System.out.println("Your Winnings: " + winnings);
                 
	}
	
	public String checkHand(ArrayList<Card> hand){
        //first, sort smallest, to largest by rank value
        //then, see if there are any suit pairs
      
        Collections.sort(hand);
        
        // outer for loop tests to see if element i matches the element immediately following ( a pair)
        // inner while loop tests to see if there are 3 or four of a kind
        for(int i = 0; i< hand.size() - 1; i++){
            int j = i + 1;
            Card a = hand.get(i);
            Card b = hand.get(j);
            if(checkPair(a, b) == true){
                matchFound = true;
                //System.out.println("pair found: " + a.getRank());
                numPairs += 1;
            }
            
            while(matchFound == true && j < hand.size()){
                j += 1;
                b = hand.get(j);
                if(checkPair(a,b) == true){
                    
                    if(j - i == 2) {
                        numTrios += 1;
                        //System.out.println("three of a Kind: " + b.getRank());
                        numPairs -= 1;
                        if(j == hand.size() - 1){
                            matchFound = false;
                        }
                    }
                    else if(j - i == 3){
                        numQuads += 1;
                        //System.out.print("four of a kind: " + b.getRank());
                        numPairs = 0;
                        numTrios = 0;
                        matchFound = false;
                    }
                }
                else{
                    matchFound = false;
                }
                
            }
            // if we find a pair or trio, we don't need to check the next one/two bc they will be identical
            // skip forward in index appropriate amount of times

            if(numTrios == 1){
                i+= 1;
            }
            
        }
        if(checkFlush(hand) == true){
            flush = true;
        }
        if(checkStraight(hand) == true){
            straight = true;
        }
        if(checkRoyal(hand) == true){
            royal = true;
            straight = true;
        }
        if(numQuads == 1){
            numTrios = 0;
        }
        
        
         
        if(numPairs == 0 && numTrios == 0 && numQuads == 0){
            if(straight == true && royal == false){
                if(flush == true){
                    result = "Straight Flush";
                    return result;
                    
                }
                else{
                    result = "Straight";
                    return result;
                }
            }
            else if(flush == true){
                if(royal == true){
                    result = "Royal Flush";
                    return result;
                }
                else{
                    result = "Flush";
                    return result;
                }
                
            }
            else{
                result = "No Pair";
                return result;
            }
        }
        else if(numPairs == 1 && numTrios == 0){
            result = "One Pair";
            return "One Pair";
        }
        else if(numPairs == 2){
            result = "Two Pairs";
            return "Two Pairs";
        }
        else if(numTrios == 1){
            if(numPairs == 1){
                result = "Full House";
                return result;
            }
            else{
                result = "Three of a kind";
                return result;     
            }
        }
        else if(numQuads == 1){
            result = "Four of a kind";
            return result;
        }
        
            
    return result;    
    }       
                

	public boolean checkPair(Card a, Card b){
        //System.out.println("Checking pair: " + a.toString() + "," + b.toString());
        if(a.compareTo(b) == 0){
            return true;   
            }
        else{
            return false;
        }
    }

    public boolean checkFlush(ArrayList<Card> hand){
        int suitMatches = 0;
        for(int i = 0; i < hand.size() - 1; i++){
            Card a = hand.get(i);
            Card b = hand.get(i+1);
            if(a.getSuit() != b.getSuit()){
                return false;
            }
            else{
                suitMatches += 1;
            }
            
        }
        return true;
    }

    public boolean checkStraight(ArrayList<Card> hand){
        Card first = hand.get(0);
        Card second = hand.get(1);
        Card third = hand.get(2);
        Card fourth = hand.get(3);
        Card fifth = hand.get(4);
        if((fifth.getRank() - 1 == fourth.getRank()) && (fourth.getRank() - 1 == third.getRank()) 
           && (third.getRank() - 1 == second.getRank()) && (second.getRank() -1 == first.getRank())){
            return true;
        }
        else{
            return false;
        }
        

    }
    public boolean checkRoyal(ArrayList<Card> hand){
        Card first = hand.get(0);
        Card second = hand.get(1);
        Card third = hand.get(2);
        Card fourth = hand.get(3);
        Card fifth = hand.get(4);
        if(first.getRank() == 1 && second.getRank() == 10 && third.getRank() == 11 && 
           fourth.getRank() == 12 && fifth.getRank() == 13){
            return true;
        }
        else{
            return false;
        }
    }
    public int suitConvert(char suit){
        int suitDigit = 0;
        int[] suitDigits = {1,2,3,4};
        char[] suitChars = {'c','d','h','s'};
        if(suit == suitChars[0]){
            suitDigit = 1;
            return suitDigit;
            
        }
        else if(suit == suitChars[1]){
            suitDigit = 2;
            return suitDigit;
            
        }
        else if(suit == suitChars[2]){
            suitDigit = 3;
            return suitDigit;

        }
        else if(suit == suitChars[3]){
            suitDigit = 4;
            return suitDigit;
        }
        return suitDigit;
    }
    

}
