package com.example.game_service_api.controller.impl;

import com.example.game_service_api.commons.entities.Game;
import com.example.game_service_api.controller.GameApi;
import com.example.game_service_api.services.GameService;
import com.example.game_service_api.services.impl.GameServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController implements GameApi {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @Override
    public ResponseEntity<Game> createGame(Game game) {
        var gameCreated = this.gameService.createGame(game);
        return ResponseEntity.ok(gameCreated);
    }

    @Override
    public ResponseEntity<Game> getGame(String id) {
        return ResponseEntity.ok(this.gameService.getGame(id));
    }

    @Override
    public ResponseEntity<Game> updateGame(String id, Game updatedGame) {
        var gameUpdated = this.gameService.updateGame(id, updatedGame);
        return ResponseEntity.ok(gameUpdated);
    }

    @Override
    public ResponseEntity<Void> deleteGame(String id) {
        this.gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
