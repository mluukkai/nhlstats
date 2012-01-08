package nhlapp.service;

import java.util.List;
import mvc.util.S3Util;
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
        playerDownloader.setPageLimit(1);

        for (Player player : playerDownloader.getPlayers()) {
            Player old = playerDao.findByName(player.getName());
            if (old != null) {
                player.setId(old.getId());
            }
            if (unchanged(player, old)) {
                continue;
            }

            playerDao.save(player);
        }
    }

    private boolean unchanged(Player player, Player old) {
        return false;
    }
}
