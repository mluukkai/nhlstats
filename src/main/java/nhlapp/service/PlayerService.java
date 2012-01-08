package nhlapp.service;

import java.util.List;
import nhlapp.util.S3Util;
import nhlapp.dao.PlayerDao;
import nhlapp.domain.Player;
import nhlapp.downloader.PlayerDownloader;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerService implements PlayerDao {

    @Autowired
    PlayerDao playerDao;
    @Autowired
    PlayerDownloader playerDownloader;
    @Autowired
    S3Util s3Util;

    @Override
    public List<Player> findAll() {
        return playerDao.findAll();
    }

    @Override
    public Player findByName(String name) {
        return playerDao.findByName(name);
    }

    @Override
    public void save(Player player) {
        playerDao.save(player);
    }

    public boolean savePageSource(int page, String file) {
        playerDownloader.setPageLimit(page);

        return s3Util.saveFile(playerDownloader.rawText(page), file);
    }

    public void download() {
        playerDownloader.setPageLimit(30);

        //int pl = 1;

        for (Player player : playerDownloader.getPlayers()) {
            Player old = playerDao.findByName(player.getName());
            if (old != null) {
                player.setId(old.getId());
            }
            if (unchanged(player, old)) {
                continue;
            }

            //System.out.println(pl++);

            playerDao.save(player);
        }
    }

    private boolean unchanged(Player player, Player old) {
        if (old == null) {
            return false;
        }

        if (player.getTeam().equals(old.getTeam())) {
            return false;
        }
        if (player.getGames() != old.getGames()) {
            return false;
        }
        if (player.getGoals() != old.getGoals()) {
            return false;
        }
        if (player.getAssists() != old.getAssists()) {
            return false;
        }
        if (player.getPenalties() != old.getPenalties()) {
            return false;
        }

        return true;
    }
}
