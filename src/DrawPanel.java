import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.util.ArrayList;

class DrawPanel extends JPanel implements MouseListener {

    private Rectangle button;
    private Deck deck;

    public DrawPanel() {
        button = new Rectangle(140, 300, 210, 26);
        this.addMouseListener(this);
        deck = new Deck();
        deck.buildDeck();
        deck.buildHand();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 120;
        int y = 10;
        for (int i = 0; i < deck.getHand().size(); i++) {
            if (i != 0 && i % 3 == 0) {
                y += 74;
                x = 120;
            }
            Card c = deck.getHand().get(i);
            if (c.getHighlight()) {
                g.drawRect(x, y, c.getImage().getWidth(), c.getImage().getHeight());
            }
            c.setRectangleLocation(x, y);
            g.drawImage(c.getImage(), x, y, null);
            x = x + c.getImage().getWidth() + 20;
        }
        g.setFont(new Font("Courier New", Font.BOLD, 20));
        g.drawString("Play Another Game", 143, 320);
        g.drawString("Cards left in Deck: " + deck.getDeck().size(), 120, 380);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
        if (!deck.isGamePossible() && !deck.getDeck().isEmpty() && !deck.getHand().isEmpty()) {
            g.setColor(Color.RED);
            g.drawString("No Numbers Add Up To 11", 0, 450);
            g.setColor(Color.BLACK);
        } else if (!deck.isGamePossible() && deck.getDeck().isEmpty() && deck.handIsEmpty()) {
            g.setColor(Color.BLUE);
            g.drawString("All Cards Are Eliminated. You Win!", 0, 450);
            g.setColor(Color.BLACK);
        }
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
            if (button.contains(clicked)) {
                deck.buildDeck();
                deck.clearHand();
                deck.buildHand();
            }
        }
        if (e.getButton() == 3) {
            if (deck.isGamePossible()) {
                for (int i = 0; i < deck.getHand().size(); i++) {
                    Rectangle box = deck.getHand().get(i).getCardBox();
                    if (box.contains(clicked) && !deck.getHand().get(i).getHighlight()) {
                        deck.getHand().get(i).flipHighlight();
                        deck.addToHighlight(deck.getHand().get(i).getValue());
                    }
                     else if (box.contains(clicked) && deck.getHand().get(i).getHighlight()) {
                        deck.getHand().get(i).flipHighlight();
                        deck.removeFromHighlight(deck.getHand().get(i).getValue());
                    }
                }
                if (deck.isHighlightValid()) {
                    for (int i = 0; i < deck.getHand().size(); i++) {
                        if(deck.getHand().get(i).getHighlight()) {
                            deck.getNewCard(i);
                        }
                    }
                }
            }
        }
    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}