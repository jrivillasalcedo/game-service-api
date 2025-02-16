package com.example.game_service_api.services;

import com.example.game_service_api.commons.entities.Game;

public interface GameService {
    Game createGame(String userId, Game gameRequest);
    Game getGame(String id);
    Game updateGame(String id, Game updatedGame);
    void deleteGame(String id);

}
