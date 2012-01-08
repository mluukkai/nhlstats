
package nhlapp.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player implements Comparable<Player> {
    @Id
    @GeneratedValue
    private Long id;

    public Player() {
    }

    public Player(String name, String team, int games, int goals, int assists, int penalties, int playerId) {
        this.name = name;
        this.team = team;
        this.games = games;
        this.goals = goals;
        this.assists = assists;
        this.penalties = penalties;
        this.playerId = playerId;
    }    
        
    private String name;
    private String team;
    private int games;
    private int goals;
    private int assists;
    private int penalties;
    private int playerId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getAssists() {
        return assists;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getGames() {
        return games;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getGoals() {
        return goals;
    }

    public void setPenalties(int penalties) {
        this.penalties = penalties;
    }

    public int getPenalties() {
        return penalties;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getPlayerId() {
        return playerId;
    }  
    
    public String getNoName(){
        return team+" "+games+" "+goals+"+"+assists+"="+getPoints()+" "+penalties;
    }
    
    @Override
    public String toString() {
        return name+" "+team+" "+games+" "+goals+"+"+assists+"="+getPoints()+" "+penalties;
    }

    public String getAsText(){
        return name+";"+team+";"+games+";"+goals+";"+assists+";"+penalties;
    }
    
    public int getPoints() {
        return goals+assists;
    }

    @Override
    public int compareTo(Player t) {
        int pointDifference = t.getPoints()-getPoints();
        
        if ( pointDifference!=0 ) return pointDifference;
        
        return t.getGoals()-getGoals();
    }
}
