import javax.swing.text.Highlighter;
import java.util.ArrayList;
import java.util.HashMap;

public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private ArrayList<Card> hand = new ArrayList<Card>();

    private ArrayList<String> highlighted = new ArrayList<String>();

    public void buildDeck() {
        deck.clear();
        String[] suits = {"clubs", "diamonds", "hearts", "spades"};
        String[] values = {"02", "09", "A", "10"};
        // String[] values = {"02", "03", "04", "05", "06", "07", "08", "09", "10", "A", "J", "K", "Q"};
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
            if (!deck.isEmpty()) {
                int r = (int) (Math.random() * deck.size());
                Card c = deck.remove(r);
                if (!hand.contains(c)) {
                    hand.remove(cardToChangeIndex);
                    hand.add(cardToChangeIndex, c);
                    newCardIsValid = true;
                }
            }
            else {
                hand.remove(cardToChangeIndex);
                Card c = new Card("Empty", "00");
                hand.add(cardToChangeIndex, c);
                newCardIsValid = true;

            }
        }
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public boolean isGamePossible() {
        HashMap<String, Card> currentHand = new HashMap<String, Card>();
        int amountOfFives = 0;
        int amountOfFours = 0;
        int amountOfThrees = 0;
        int amountOfTwos = 0;
        int amountOfAs = 0;
        for (Card c : hand) {
            if (c.getValue().equals("05")) {
                amountOfFives++;
            }
            if (c.getValue().equals("04")) {
                amountOfFours++;
            }
            if (c.getValue().equals("03")) {
                amountOfThrees++;
            }
            if (c.getValue().equals("02")) {
                amountOfTwos++;
            }
            if (c.getValue().equals("A")) {
                amountOfAs++;
            }
            currentHand.put(c.getValue(), c);
        }
        if (currentHand.containsKey("J") && currentHand.containsKey("Q") && currentHand.containsKey("K")) {
            return true;
        }
        if ((currentHand.containsKey("A") && currentHand.containsKey("10")) || (currentHand.containsKey("02") && currentHand.containsKey("09")) || (currentHand.containsKey("03") && currentHand.containsKey("08")) || (currentHand.containsKey("04") && currentHand.containsKey("07")) || (currentHand.containsKey("05") && currentHand.containsKey("06"))) {
            return true;
        }
        if ((currentHand.containsKey("A") && currentHand.containsKey("02") && currentHand.containsKey("08")) || (currentHand.containsKey("A") && currentHand.containsKey("03") && currentHand.containsKey("07")) || (currentHand.containsKey("A") && currentHand.containsKey("04") && currentHand.containsKey("06")) || (currentHand.containsKey("02") && currentHand.containsKey("03") && currentHand.containsKey("06")) || (currentHand.containsKey("02") && currentHand.containsKey("04") && currentHand.containsKey("05")))  {
            return true;
        }
        if (currentHand.containsKey("A") && amountOfFives >= 2) {
            return true;
        }
        if (currentHand.containsKey("03") && amountOfFours >= 2) {
            return true;
        }
        if (currentHand.containsKey("05") && amountOfThrees >= 2) {
            return true;
        }
        if (currentHand.containsKey("07") && amountOfTwos >= 2) {
            return true;
        }
        if (currentHand.containsKey("09") && amountOfAs >= 2) {
            return true;
        }
        return false;
    }

    public void clearHand() {
        hand.clear();
    }

    public void addToHighlight(String c) {
        highlighted.add(c);
    }

    public void removeFromHighlight(String c) {
        highlighted.remove(c);
    }

    public boolean isHighlightValid() {
        if (highlighted.size() == 3 && highlighted.contains("J") && highlighted.contains("Q") && highlighted.contains("K")) {
            highlighted.clear();
            return true;
        }
        if (highlighted.size() == 2 && (highlighted.contains("A") && highlighted.contains("10")) || (highlighted.contains("02") && highlighted.contains("09")) || (highlighted.contains("03") && highlighted.contains("08")) || (highlighted.contains("04") && highlighted.contains("07")) || (highlighted.contains("05") && highlighted.contains("06"))) {
            highlighted.clear();
            return true;
        }
        if (highlighted.size() == 3 && (highlighted.contains("A") && highlighted.contains("02") && highlighted.contains("08")) || (highlighted.contains("A") && highlighted.contains("03") && highlighted.contains("07")) || (highlighted.contains("A") && highlighted.contains("04") && highlighted.contains("06")) || (highlighted.contains("02") && highlighted.contains("03") && highlighted.contains("06")) || (highlighted.contains("02") && highlighted.contains("04") && highlighted.contains("05"))) {
            highlighted.clear();
            return true;
        }
        if (highlighted.size() == 3) {
            int num5 = 0;
            int numA = 0;
            int num3 = 0;
            int num4 = 0;
            int num7 = 0;
            int num2 = 0;
            int num9 = 0;
            for (String s : highlighted) {
                if (s.equals("05")) {
                    num5++;
                }
                if (s.equals("A")) {
                    numA++;
                }
                if (s.equals("03")) {
                    num3++;
                }
                if (s.equals("04")) {
                    num4++;
                }
                if (s.equals("07")) {
                    num7++;
                }
                if (s.equals("02")) {
                    num2++;
                }
                if (s.equals("09")) {
                    num9++;
                }
            }
            if (numA == 1 && num5 == 2) {
                highlighted.clear();
                return true;
            }
            if (num4 == 2 && num3 == 1) {
                highlighted.clear();
                return true;
            }
            if (num3 == 2 && num5 == 1) {
                highlighted.clear();
                return true;
            }
            if (num7 == 1 && num2 == 2) {
                highlighted.clear();
                return true;
            }
            if (numA == 2 && num9 == 1) {
                highlighted.clear();
                return true;
            }
        }
        return false;
    }

    public boolean handIsEmpty() {
        for (Card c : hand) {
            if (!c.getValue().equals("00")) {
                return false;
            }
        }
        return true;
    }
}
