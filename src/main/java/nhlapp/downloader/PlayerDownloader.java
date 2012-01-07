package nhlapp.downloader;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import nhlapp.domain.Player;

public class PlayerDownloader {

    private List<Player> players;
    private String URL_BASE = "http://www.nhl.com/ice/playerstats.htm?pg=";
    private int NUMBER_OF_PLAYERS;
    private long consumed;
    private int pageNumber;
    private int pageLimit;

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public String rawText(int page) {
        pageNumber = page;
        Parser parser = null;
        try {
            URL url = new URL(URL_BASE + pageNumber);
            parser = new Parser(url.openStream());
            
            return parser.rawMode();
        } catch (Exception ex) {            
        }
        return "";
    }

    public List<Player> getPlayers() {
        pageNumber = 1;
        players = new ArrayList<Player>();

        long start = System.currentTimeMillis();

        while (true) {
            Parser parser = null;
            try {
                URL url = new URL(URL_BASE + pageNumber);
                parser = new Parser(url.openStream());
            } catch (Exception ex) {
                return players;
            }

            parser.parse();
            for (Player player : parser.getPlayers()) {
                players.add(player);
            }

            pageNumber++;
            if (parser.playersRead() < NUMBER_OF_PLAYERS) {
                break;
            }
            if (pageLimit > 0 && pageNumber > pageLimit) {
                break;
            }
        }

        consumed = (System.currentTimeMillis() - start) / 1000;

        return players;
    }

    public void stats() {
        System.out.println("pages: " + pageNumber);
        System.out.println("players " + players.size());
        System.out.println("time: " + consumed + " sec");
    }
}
