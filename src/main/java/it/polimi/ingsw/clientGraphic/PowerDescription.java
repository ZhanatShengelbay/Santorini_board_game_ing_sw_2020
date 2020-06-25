package it.polimi.ingsw.clientGraphic;

import it.polimi.ingsw.model.EnumDivinity;

public class PowerDescription {

    public static String getDescription(String god){
       EnumDivinity x= EnumDivinity.valueOf(god);
       switch(x){
           case PAN:
               return "<html>"+x.name()+" Win Condition: You also win if " + "<br>"+
                       "your Worker moves down two or " +
                       "more levels.</html> ";
           case ARES:
               return "<html>"+x.name()+" End of Your Turn: You may  " +"<br>"+
                   "remove an unoccupied block " +
                   "(not dome) neighboring your " +"<br>"+
                   "unmoved Worker. You also remove any Tokens " +"<br>"+
                   "on the block.</html> ";
           case ZEUS:
               return"<html>"+ x.name()+" Your Build: Your Worker may build a block under itself</html>";
           case ATLAS:

               return "<html>"+x.name()+" Your Build: Your Worker may " +"<br>"+
                       "build a dome at any level.</html>";
           case LIMUS:
               return "<html>"+x.name()+" Opponent’s Turn: Opponent " +"<br>"+
                       "Workers cannot build on spaces " +
                       "neighboring your Workers, " +"<br>"+
                       "unless building a dome to create a Complete " +
                       "Tower.</html>";
           case APOLLO:
               return"<html>"+ x.name()+" Your Move: Your Worker may " +"<br>"+
                       "move into an opponent Worker’s " +
                       "space by forcing their Worker to " +"<br>"+
                       "the space yours just vacated.</html>";
           case ATHENA:
               return "<html>"+x.name()+" Opponent’s Turn: If one of your " +"<br>"+
                       "Workers moved up on your last " +
                       "turn, opponent Workers cannot " +"<br>"+
                       "move up this turn.</html>";
           case TRITON:
               return "<html>"+x.name()+" Your Move: Each time your " +"<br>"+
                       "Worker moves into a perimeter " +
                       "space, it may immediately move " +"<br>"+
                       "again.</html>";
           case ARTEMIS:
               return "<html>"+x.name()+" Your Move: Your Worker may " +"<br>"+
                       "move one additional time, but not " +
                       "back to its initial space.</html>";
           case DEMETER:
               return"<html>"+ x.name()+" Your Build: Your Worker may " +"<br>"+
                       "build one additional time, but not " +"<br>"+
                       "on the same space.</html>";
           case MINOTAUR:
               return "<html>"+ x.name()+" Your Move: Your Worker may " +
                       "move into an opponent Worker’s " +"<br>"+
                       "space, if their Worker can be\n" +
                       "forced one space straight backwards to an " +"<br>"+
                       "unoccupied space at any level. </html>";
           case APHRODITE:
               return"<html>"+  x.name()+" Any Move: If an opponent Worker" +
                       "starts its turn neighboring one of" +"<br>"+
                       "your Workers, its last move must" +"<br>"+
                       "be to a space neighboring one of your Workers.</html>";
           case HEPHAESTUS:
               return "<html>"+ x.name()+" Your Build: Your Worker may " +"<br>"+
                       "build one additional block (not " +
                       "dome)"+"<br>"+" on top of your first block.</html>";
           case PROMETHEUS:
               return "<html>"+ x.name()+" Your Turn: If your Worker does " +"<br>"+
                       "not move up, it may build both " +"<br>"+
                       "before and after moving.</html>";
           default:
               return "" ;
       }


    }
}
