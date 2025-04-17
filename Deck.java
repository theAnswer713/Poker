import java.util.Random;
import java.util.Stack;

public class Deck {
    private Stack<Card> deck = new Stack<>();

    public Deck() {
        for(int s = 0; s < 4; ++s) {
            String suit = "";
            if (s == 0) {
                suit = "♥";
            }

            if (s == 1) {
                suit = "♦";
            }

            if (s == 2) {
                suit = "♣";
            }

            if (s == 3) {
                suit = "♠";
            }

            for(int v = 1; v <= 13; ++v) {
                deck.push(new Card(v, suit));
            }
        }

    }

    public Card dealCard() {
        return deck.pop();
    }

    public void shuffle() {
        Random rand = new Random();

        for(int count=0;count<1000;count++) {
            int split = deck.size() / 2 + rand.nextInt(9)-4;
            Stack<Card> l = new Stack<>();
            Stack<Card> r = new Stack<>();
            for(int i=0;i<split;i++) {
                l.push(deck.pop());
            }
            while(!deck.isEmpty()) {
                r.push(deck.pop());
            }
            while(!l.isEmpty()||!r.isEmpty()) {
                int n = Math.min(l.size(),rand.nextInt(3)+1);
                for(int i=0;i<n;i++) {
                    deck.push(l.pop());
                }

                n = Math.min(r.size(),rand.nextInt(3)+1);
                for(int i=0;i<n;i++) {
                    deck.push(r.pop());
                }
            }
        }
    }
}
