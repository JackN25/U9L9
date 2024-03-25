import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.util.ArrayList;
import java.awt.Font;

class DrawPanel extends JPanel implements MouseListener {

    private Rectangle button;
    private Deck deck;

    public DrawPanel() {
        button = new Rectangle(155, 300, 160, 26);
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
        g.drawString("Play Another Game", 157, 320);
        g.drawRect((int)button.getX(), (int)button.getY(), (int)button.getWidth(), (int)button.getHeight());
    }

    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if (e.getButton() == 1) {
            if (button.contains(clicked)) {
                deck.buildHand();
            }

            for (int i = 0; i < deck.getHand().size(); i++) {
                Rectangle box = deck.getHand().get(i).getCardBox();
                if (box.contains(clicked)) {
                    deck.getHand().get(i).flipCard();
                }
            }
        }
        if (e.getButton() == 3) {
            for (int i = 0; i < deck.getHand().size(); i++) {
                Rectangle box = deck.getHand().get(i).getCardBox();
                if (box.contains(clicked) && !deck.getHand().get(i).getHighlight()) {
                    deck.getHand().get(i).flipHighlight();
                }
                else if (box.contains(clicked) && deck.getHand().get(i).getHighlight()) {
                    deck.getNewCard(i);
                }
            }
        }


    }

    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }
}