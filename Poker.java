import java.util.Scanner;

public class Poker {
    public static void main(String[] args) {
        System.out.println("Welcome to Poker (with no gambling so it's legal in school)!");
        System.out.println("What is your name?");
        Scanner scan = new Scanner(System.in);
        String name = scan.nextLine();
        Player player = new Player(name);
        Player cpu = new Player("Computer");
        boolean playing = true;
        int w = 0;
        int l = 0;
        int r = 0;

        while(playing) {
            r++;
            System.out.println("Round "+r+": ("+player.getName()+") "+w+" to "+l+" (Computer)");
            player.getHand().clear();
            cpu.getHand().clear();
            Deck deck = new Deck();
            deck.shuffle();
            for(int i=0;i<5;i++) {
                player.getHand().add(deck.dealCard());
                cpu.getHand().add(deck.dealCard());
            }
            player.sortHand();
            cpu.sortHand();
            System.out.println("Your hand: "+player);

            System.out.println("Would you like to replace any cards? (y/n)");
            if(scan.nextLine().equalsIgnoreCase("y")) {
                System.out.println("Enter the indices of the cards you would like to replace without spaces.");
                String indices = scan.nextLine();
                for(int i=0;i<indices.length();i++) {
                    player.getHand().set(Character.getNumericValue(indices.charAt(i)),deck.dealCard());
                }
                player.sortHand();
                System.out.println("Your hand: "+player);
            }
            System.out.println("Computer's hand: "+cpu);

            int playerHand = player.handID();
            int cpuHand = cpu.handID();

            if(playerHand==cpuHand) {
                System.out.println("You and the computer both had a " + player.handName(playerHand)+". ");
                if(player.high(playerHand)>cpu.high(cpuHand)) {
                    System.out.println("You win!");
                    w++;
                }
                else {
                    System.out.println("You lose.");
                    l++;
                }
            }
            else {
                System.out.println("You had a "+player.handName(playerHand)+". The computer had a "+cpu.handName(cpuHand)+". ");
                if(playerHand>cpuHand) {
                    System.out.println("You win!");
                    w++;
                }
                else {
                    System.out.println("You lose.");
                    l++;
                }
            }

            System.out.println("Would you like to play again? (y/n)");
            if(scan.nextLine().equalsIgnoreCase("n")) playing=false;
        }
        System.out.println("Thank you for playing!");
        System.out.println("The final score was ("+player.getName()+") "+w+" to "+l+" (Computer)");
    }
}