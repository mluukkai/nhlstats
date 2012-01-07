package nhlapp.downloader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import nhlapp.domain.Player;

public class Parser {

    private Scanner lukija;
    private List<Player> players;

    public Parser(InputStream stream) {
        lukija = new Scanner(stream);
        players = new ArrayList<Player>();
    }

    public void parse() {
        while (lukija.hasNextLine()) {
            String line = lukija.nextLine();
            if (line.contains("/ice/player.htm")) {
                parseLine(line);
            }
        }
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int playersRead() {
        return players.size();
    }

    private void parseLine(String line) {

        for (String pl : line.split("/ice/player.htm")) {
            if (pl.charAt(0) == '<'  || pl.charAt(1) == ' '  ) {
                continue;
            }
                       
            parsePlayer(pl);
        }
    }

    private void parsePlayer(String pl) {

        String[] fields = pl.split("</td>");
        
        String name = fields[0].substring(13, fields[0].length() - 4);
        String team = findOutTeams(fields[1]);      
        
        int playerId = Integer.parseInt(fields[0].substring(4, 11));
        int games = asInt(fields[3]);
        int goals = asInt(fields[4]);
        int assists = asInt(fields[5]);
        int penaltied = asInt(fields[8]);

        Player player = new Player(name, team, games, goals, assists, penaltied, playerId);
        players.add(player);

    }

    private String findOutTeams(String field) {
        if (field.charAt(field.length() - 1) == '>') {
            int last = field.substring(0, field.length() - 1).lastIndexOf(">") + 1;
            return field.substring(last, field.length() - 4);
        }
        int first = field.lastIndexOf(">") + 1;

        return field.substring(first);
    }

    private int asInt(String string) {
        int last = string.lastIndexOf(">") + 1;
        return Integer.parseInt(string.substring(last));
    }
}
