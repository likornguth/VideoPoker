# VideoPoker
Coding project P7.10 in the 7th Edition of Big Java, with modifications

The Game class controls all of the functions of the game by prompting methods of the other classes through its play method
First, it calls cards.shuffle() - cards is an object belonging to the Deck class. The shuffle method generates a new array list of 
integers between 0-51, then using the Random class, fills the 52 spots of the integer array list with random numbers. In order to
avoid repeats, I had to create a conditional that would only add items to the list if they weren't already already added.
Once this new Array List was created, I assigned each card in the deck to one of these values.  Then, to create new card objects
from these values, I needed to create 4 distinct suites each with 13 unique ranks.  The integer divison of value/13 would result in
a int between 0-3, which I then incremented to change the range to 1-4.  These correspond to the suits. Value%13 results in a 
value 0-12, which is also incremented by one to create a range 1-13 to correspond to the ranks (ace thru king).

The game class then prompts the user to make a bet of (1-5) tokens and stores the result to make the winnings calculation later.
Then, the game calls the deal method 5 times, each time returning the top card of the now shuffled deck.  The index of the top card 
is stored as an instance variable and each time deal is called the index decrements by one.

The player can now see their hand as a list of 5 unique cards, and can choose to swap out as many of their cards as they like
After the user rejects a card, the remove card method removes a card from the arraylist, causing the arraylist size to decrease 
as well as the indexes of each card.  That is why a new list of updated options appears before each prompt to remove cards. 
The method also keeps track of how many new cards need to be dealed out. Since the amount of times that the removal method is 
called is variable (depends on what user wants) , I decided to use a while loop that terminates only when the user enters the 
non-integer value X.  When this happens, the game deals enough cards to fill out the player's hand again.

Then, the checkHand method evaluates the players hand and returns a string result ("No Pair", "One Pair", ... , "Royal Flush").
the winnings method then takes the bet stored from the beginning and multiplies it by the payout corresponding to the result string.
