package com.example.game_service_api.services.impl;

import com.example.game_service_api.commons.entities.Game;
import com.example.game_service_api.commons.exceptions.GameException;
import com.example.game_service_api.repositories.GameRepository;
import com.example.game_service_api.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createGame(Game gameRequest) {
        return  this.gameRepository.save(gameRequest);
    }

    @Override
    public Game getGame(String id) {
        return gameRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new GameException(HttpStatus.NOT_FOUND, "Error finding game"));
    }

    @Override
    public Game updateGame(String id, Game updatedGame) {
        return Optional.of(updatedGame)
                .map(game -> {
                    game.setId(Long.valueOf(id));
                    return gameRepository.save(game);
                })
                .orElseThrow(() -> new GameException(HttpStatus.NOT_FOUND, "Game not found for update"));
    }

    @Override
    public void deleteGame(String id) {

        gameRepository.deleteById(Long.valueOf(id));
    }
}
