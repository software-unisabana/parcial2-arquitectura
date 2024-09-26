package co.edu.unisabana.parcial.integration.controller;


import co.edu.unisabana.parcial.AbstractTest;
import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.controller.dto.ResponseGate;
import co.edu.unisabana.parcial.service.model.Checkin;
import org.apache.el.parser.AstFalse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GateControllerTest extends AbstractTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    private static final String PATH_POST_CHECKPOINT_CHECKIN = "/checkpoint/checkin";
    private static final String PATH_POST_CHECKPOINT_CHECKOUT = "/checkpoint/checkout";

    @Test
    void  Given_day_of_moth_between_30_and_1_When_invoke_checkin_Then_success_message(){
        CheckpointDTO checkpointDTO = new CheckpointDTO("prueba", "prueba", 14);

        ResponseEntity<ResponseGate> response = testRestTemplate.postForEntity(PATH_POST_CHECKPOINT_CHECKIN, checkpointDTO, ResponseGate.class);

        Assertions.assertEquals(HttpStatus.valueOf(200), response.getStatusCode());
    }

    @Test
    void  Given_day_of_moth_between_30_and_1_and_last_checkin_is_not_null_When_invoke_checkout_Then_success_message(){
        CheckpointDTO checkpointDTO = new CheckpointDTO("prueba1", "prueba1", 14);
        new Checkin(checkpointDTO.facility, checkpointDTO.driver,  checkpointDTO.dayOfMonth);

        ResponseEntity<ResponseGate> response = testRestTemplate.postForEntity(PATH_POST_CHECKPOINT_CHECKOUT, checkpointDTO, ResponseGate.class);

        Assertions.assertEquals(HttpStatus.valueOf(200), response.getStatusCode());
    }

}
