import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void sortHand() {
        Card temp;
        for(int i=0;i<hand.size()-1;i++) {
            for(int j=i+1;j<hand.size();j++) {
                if(hand.get(i).getValue()>hand.get(j).getValue()) {
                    temp = hand.get(i);
                    hand.set(i,hand.get(j));
                    hand.set(j,temp);
                }
            }
        }
    }

    public int handID() {
        if(royalFlush()!=-1) return 10;
        if(straightFlush()!=-1) return 9;
        if(fourKind()!=-1) return 8;
        if(fullHouse()!=-1) return 7;
        if(flush()!=-1) return 6;
        if(straight()!=-1) return 5;
        if(threeKind()!=-1) return 4;
        if(twoPair()!=-1) return 3;
        if(pair()!=-1) return 2;
        return 1;
    }

    public String handName(int id) {
        switch(id) {
            case 10 -> {return "Royal Flush";}
            case 9 -> {return "Straight Flush";}
            case 8 -> {return "Four of a Kind";}
            case 7 -> {return "Full House";}
            case 6 -> {return "Flush";}
            case 5 -> {return "Straight";}
            case 4 -> {return "Three of a Kind";}
            case 3 -> {return "Two Pair";}
            case 2 -> {return "Pair";}
            default -> {return "High Card";}
        }
    }

    public int high(int id) {
        switch(id) {
            case 10 -> {return royalFlush();}
            case 9 -> {return straightFlush();}
            case 8 -> {return fourKind();}
            case 7 -> {return fullHouse();}
            case 6 -> {return flush();}
            case 5 -> {return straight();}
            case 4 -> {return threeKind();}
            case 3 -> {return twoPair();}
            case 2 -> {return pair();}
            case 1 -> {return highCard();}
        }
        return highCard();
    }

    public int royalFlush() {
        if(straightFlush()!=-1) {
            if(hand.get(4).getValue()==14) {
                return 14;
            }
        }
        return -1;
    }

    public int straightFlush() {
        if(straight() != -1 && flush() != -1) return hand.get(4).getValue();
        else return -1;
    }

    public int fullHouse() {
        if(threeKind()!=-1) {
            if(hand.get(0).getValue()==hand.get(1).getValue() && hand.get(3).getValue()==hand.get(4).getValue()) {
                return hand.get(4).getValue();
            }
        }
        return -1;
    }

    public int flush() {
        String s = hand.get(0).getSuit();
        for(int i=1;i<hand.size();i++) {
            if(!hand.get(i).getSuit().equals(s)) {
                return -1;
            }
        }
        return hand.get(4).getValue();
    }

    public int straight() {
        for(int i=0;i<hand.size()-1;i++) {
            if(hand.get(i).getValue() != hand.get(i+1).getValue()-1) {
                return -1;
            }
        }
        return hand.get(4).getValue();
    }

    public int fourKind() {
        for(int i=0;i<hand.size()-3;i++) {
            if(hand.get(i).getValue()==hand.get(i+1).getValue() && hand.get(i+1).getValue()==hand.get(i+2).getValue() && hand.get(i+2).getValue()==hand.get(i+3).getValue()) {
                return hand.get(i).getValue();
            }
        }
        return -1;
    }

    public int threeKind() {
        for(int i=0;i<hand.size()-2;i++) {
            if(hand.get(i).getValue()==hand.get(i+1).getValue() && hand.get(i+1).getValue()==hand.get(i+2).getValue()) {
                return hand.get(i).getValue();
            }
        }
        return -1;
    }

    public int twoPair() {
        int count=0;
        int val=-1;
        for(int i=0;i<hand.size()-1;i++) {
            if(hand.get(i).getValue()==hand.get(i+1).getValue()) {
                count++;
                val=hand.get(i).getValue();
            }
        }
        if(count==2) {
            return val;
        }
        else return -1;
    }

    public int pair() {
        for(int i=hand.size()-2;i>=0;i--) {
            if(hand.get(i).getValue()==hand.get(i+1).getValue()) {
                return hand.get(i).getValue();
            }
        }
        return -1;
    }

    public int highCard() {
        return hand.get(4).getValue();
    }


    @Override
    public String toString() {
        String output = "";
        for(Card x: hand) {
            output += x+" ";
        }
        return output;
    }
}