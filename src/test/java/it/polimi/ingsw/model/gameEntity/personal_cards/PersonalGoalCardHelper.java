package it.polimi.ingsw.model.gameEntity.personal_cards;



/*public class PersonalGoalCardHelper {

    public static PersonalGoalCard newPersonalGoalCardFromString(String str){
        String newstr;
        newstr = str.replaceAll("\n","");
        newstr = newstr.replaceAll(" ","");
        newstr = newstr.replaceAll("\"","");

        String[] card = newstr.split("\\|");

        int numOfCard = Integer.parseInt(card[0]);

        ArrayList<Goal> goals = new ArrayList<>();

       for(int i =1; i<=6; i++){
           String[] singleGoal = card[i].split(",");
           int row = Integer.parseInt(singleGoal[0]);
           int col = Integer.parseInt(singleGoal[1]);
           String itemTileType = singleGoal[2];
           Goal goal = new Goal(row,col,itemTileType);
           goals.add(goal);
       }
        return new PersonalGoalCard(numOfCard,goals);
    }
}*/



