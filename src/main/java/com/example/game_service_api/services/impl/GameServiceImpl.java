package com.example.game_service_api.services.impl;

import com.example.game_service_api.commons.entities.Game;
import com.example.game_service_api.commons.exceptions.GameException;
import com.example.game_service_api.repositories.GameRepository;
import com.example.game_service_api.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;

    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game createGame(Game gameRequest) {
        Game gameCreatedInDataBase = this.gameRepository.save(gameRequest);
        return gameCreatedInDataBase;
    }

    @Override
    public Game getGame(String id) {
        return this.gameRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new GameException(HttpStatus.NOT_FOUND, "Error finding game"));
    }

    @Override
    public Game updateGame(String id, Game updatedGame) {
        Game existingGame = this.gameRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new GameException(HttpStatus.NOT_FOUND, "Game not found for update"));
        existingGame.setName(updatedGame.getName());
        return this.gameRepository.save(existingGame);
    }

    @Override
    public void deleteGame(String id) {
        if (!this.gameRepository.existsById(Long.valueOf(id))) {
            throw new GameException(HttpStatus.NOT_FOUND, "Game not found for deletion");
        }
        this.gameRepository.deleteById(Long.valueOf(id));
    }
}
