package co.edu.unisabana.parcial.service;

import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;

public interface ICheckpointService {

    void checkin(CheckpointDTO checkpoint);
    void checkout(CheckpointDTO checkpoint);
}
