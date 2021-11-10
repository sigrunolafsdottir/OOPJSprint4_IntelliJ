package ÖvnUppg9_Riddles;

public class RiddleProtocol {
    private static final int WAITING = 0;
    private static final int SENTRIDDLE = 1;
    private static final int SENTANSWER = 2;

    private static final int NUMRIDDLES = 3;

    private int state = WAITING;
    private int currentRiddle = 0;

    private String[] clues = { "Vad är det som går och går men aldrig kommer till dörren?", 
        "Vilken sten är alltid ihålig?", 
        "Vilket öga kan inte se?"};
    private String[] answers = { "Klockan",
                                 "Skorstenen",
                                 "Nålsögat" };

    public String processInput(String theInput) {
        String theOutput = null;

        if (state == WAITING ) {
            theOutput = clues[currentRiddle];
            state = SENTRIDDLE;
        }
        else if (state == SENTANSWER) {
            if (theInput.equalsIgnoreCase("nej")){
                theOutput = "bye";
            }
            else {
                theOutput = clues[currentRiddle];
                state = SENTRIDDLE;
            }
        } else if (state == SENTRIDDLE) {
            if (theInput.equalsIgnoreCase(answers[currentRiddle])) {
                theOutput = "Rätt, vill du ha en till? (svara ja eller nej)";
            } else {
                theOutput = "Fel, vill du ha en till? (svara ja eller nej)";
            }
            state = SENTANSWER;
            currentRiddle++;
        }
        return theOutput;
    }
}