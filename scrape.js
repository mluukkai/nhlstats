const axios = require("axios");
const fs = require("fs");

function onlyUnique(value, index, self) {
  return self.indexOf(value) === index;
}

const players_team = {};

const season = "20232024";
const fileName = `./data-${season}.json`;

async function skrape_ids() {
  const teams_url = `https://statsapi.web.nhl.com/api/v1/teams?expand=team.roster&season=${season}`;
  const resp = await axios.get(teams_url);
  const teams = resp.data.teams.map((t) => ({
    roster: t.roster.roster,
    name: t.abbreviation,
  }));

  let player_ids = [];
  teams.forEach((team) => {
    const new_ids = team.roster.map((tr) => tr.person.id);
    new_ids.forEach((id) => {
      players_team[id] = team.name;
    });
    player_ids = player_ids.concat(new_ids);
  });
  player_ids = player_ids.filter(onlyUnique);

  return player_ids;
}

async function skrape_player(id) {
  const url = `https://statsapi.web.nhl.com/api/v1/people/${id}/stats?stats=statsSingleSeason&season=${season}`;
  const x = await axios.get(url);
  try {
    const stats = x.data.stats[0].splits[0].stat;

    const url2 = `https://statsapi.web.nhl.com/api/v1/people/${id}`;
    const y = await axios.get(url2);
    const player = y.data.people[0];

    return {
      name: player.fullName,
      nationality: player.nationality,
      assists: stats.assists ? stats.assists : 0,
      goals: stats.goals ? stats.goals : 0,
      penalties: stats.pim ? stats.pim : 0,
      team: players_team[id],
      games: stats.games,
    };
  } catch (e) {}
}

async function do_acual_shit() {
  const ids = await skrape_ids();

  const players = [];
  //for (let i=200; i<203; i++ ) {
  for (let i = 0; i < ids.length; i++) {
    const player = await skrape_player(ids[i]);
    if (player) {
      players.push(player);
    }

    console.log(i);
  }

  console.log(players.length);
  fs.writeFileSync(fileName, JSON.stringify(players), "utf-8");
}

do_acual_shit();