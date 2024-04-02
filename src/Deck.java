import java.util.ArrayList;
import java.util.HashMap;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private ArrayList<Card> hand = new ArrayList<Card>();


    public void buildDeck() {
        deck.clear();
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        String[] values = {"02", "03", "04", "05", "06", "07", "08", "09", "10", "A", "J", "K", "Q"};
        for (String s : suits) {
            for (String v : values) {
                Card c = new Card(s, v);
                deck.add(c);
            }
        }
    }

    public void removeCard(Card c) {
        for (int i = 0; i < deck.size(); i ++) {
            if (deck.get(i).getSuit().equalsIgnoreCase(c.getSuit()) && deck.get(i).getValue().equals(c.getValue())) {
                deck.remove(i);
                return;
            }
        }
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void buildHand() {
        deck.addAll(hand);
        hand.clear();
        for (int i = 0; i < 9; i++) {
            int r = (int)(Math.random()*deck.size());
            Card c = deck.remove(r);
            hand.add(c);
        }
    }


    public void getNewCard(int cardToChangeIndex) {
        boolean newCardIsValid = false;
        while (!newCardIsValid) {
            int r = (int) (Math.random() * deck.size());
            Card c = deck.remove(r);
            if (!hand.contains(c)) {
                hand.remove(cardToChangeIndex);
                hand.add(cardToChangeIndex, c);
                newCardIsValid = true;
            }
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public boolean isGamePossible() {
        HashMap<Card, String> currentHand = new HashMap<Card, String>();
        for (Card c : hand) {
            currentHand.put(c, c.getSuit());
        }
        if (currentHand.containsKey("J") && currentHand.containsKey("Q") && currentHand.containsKey("K")) {
            return true;
        }
        if (currentHand.containsKey("A") && currentHand.containsKey("10") )
        return false;
    }
}
