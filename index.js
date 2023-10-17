const axios = require("axios");
const express = require("express");
const app = express();

const stats = {
  "2018-19": require("./data-20182019.json"),
  "2019-20": require("./data-20192020.json"),
  "2020-21": require("./data-20202021.json"),
  "2021-22": require("./data-20212022.json"),
  "2022-23": require("./data-20222023.json"),
  "2023-24": require("./data-20232024.json"),
};

const url =
  "https://api.nhle.com/stats/rest/skaters?isAggregate=false&reportType=basic&isGame=false&reportName=skatersummary&cayenneExp=leagueId=133%20and%20gameTypeId=2%20and%20seasonId%3E=20192020%20and%20seasonId%3C=20192020";

app.get("/:year/players.txt", (req, res) => {
  const players = stats[req.params.year];
  if (!players) {
    return res.status(400).json({
      error: "following years only available " + Object.keys(stats).join(", "),
    });
  }

  const strings = players.map((p) => {
    return `${p.name};${p.team};;${p.goals};${p.assists}`;
  });

  res.send(strings.join("\n"));
});

app.get("/:year/players", (req, res) => {
  const players = stats[req.params.year];
  if (!players) {
    return res.status(400).json({
      error: "following years only available " + Object.keys(stats).join(", "),
    });
  }

  res.json(players);
});

const PORT = process.env.PORT || 3001;
app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});