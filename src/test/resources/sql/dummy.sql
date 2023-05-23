insert into summoner (id, puuid, name, profile_icon_id, summoner_level)
values ('swsVftNdzs0OKJqJpbKwEtICk9_5oImBLO2Rdms-Dhzkcg', 'jE5u83HK1QB-aZCzI-hPuQ0ne2Btnutj6IVKPpgXB4OOjJkRTwRU1uJdo-CsfD6wWxUrAtiTslMFJA', '리거누', 1234, 344);

insert into summoner (id, puuid, name, profile_icon_id, summoner_level)
values ('summoner_id2', 'puuid2', '인생스토리', 1234, 344);

insert into match_info(id)
values ('matchId');

insert into match_record (team_id, summoner_id, team_position, champion_id, champion_name, solo_kills,
                          vision_score, vision_wards_bought_in_game, total_minions_killed,
                          total_damage_dealt_to_champions,
                          gold_earned, kills, deaths, assists, win, match_info_id)
values (1, 'swsVftNdzs0OKJqJpbKwEtICk9_5oImBLO2Rdms-Dhzkcg', 'TOP', 1, 'Jayce', 1, 1, 1, 1, 1, 1, 1, 1, 1, true, 'matchId');

insert into match_record (team_id, summoner_id, team_position, champion_id, champion_name, solo_kills,
                          vision_score, vision_wards_bought_in_game, total_minions_killed,
                          total_damage_dealt_to_champions,
                          gold_earned, kills, deaths, assists, win, match_info_id)
values (2, 'summoner_id2', 'TOP', 2, 'Gnar', 1, 1, 1, 1, 1, 1, 1, 1, 1, false, 'matchId');


