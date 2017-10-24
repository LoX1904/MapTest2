package com.gym.leon.maptest;


public class QuestionList {
    //TODO Sinvolle Fragen raussuchen
    private String mQuestions [] = {
            "Vor der Marienkirche befindet sich die Lutherlinde. Wieso trägt sie diesen Namen?",
            "Um welches Jahrhundert wurde das Heimatmuseum erbaut?",
            "Als was diente der Pulverturm früher?",
            "Opfer welcher Kriege symbolisiert der Ehrenhain?",
            "Rathaus "

    };


    private String mChoices [][] = {
            {"Luther prädigte dort", "Luther starb dort", "Luther versteckte sich dort"},
            {"1200", "1300", "1400"},
            {"Munitionslagerstätte", "Nahrungsmittellagestätte", "Wachturm"},
            {"Deutsch/Franz. Krieg , 1 WK, 2 WK", "Napoleon. Kriege, 1 WK, 2 WK", "Deutscher Krieg, 1 WK , 2 WK"},
            {"a", "b", "c"}
    };



    private String mCorrectAnswers[] = {"Luther prädigte dort", "1300", "Munitionslagerstätte", "Deutsch/Franz. Krieg , 1 WK, 2 WK","c"};




    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}
