package co.edu.unisabana.parcial.service;

import co.edu.unisabana.parcial.controller.dto.CheckpointDTO;
import co.edu.unisabana.parcial.service.CheckpointService;
import co.edu.unisabana.parcial.service.model.Checkin;
import co.edu.unisabana.parcial.service.model.Checkout;
import co.edu.unisabana.parcial.service.port.CheckpointPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CheckpointServiceTest {

    @InjectMocks
    CheckpointService checkpointService;

    @Mock
    CheckpointPort checkpointPort;

    @Test
    void Given_day_of_moth_greater_than_30_When_invoke_checkin_Then_IllegalArgumentException(){
        CheckpointDTO checkpointDTO = new CheckpointDTO("prueba", "prueba", 31);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checkpointService.checkin(checkpointDTO);
        });
    }

    @Test
    void Given_day_of_moth_less_than_0_When_invoke_checkin_Then_IllegalArgumentException(){
        CheckpointDTO checkpointDTO = new CheckpointDTO("prueba", "prueba", 0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checkpointService.checkin(checkpointDTO);
        });
    }

    @Test
    void Given_day_of_moth_between_30_and_1_When_invoke_checkin_Then_success_message(){
        CheckpointDTO checkpointDTO = new CheckpointDTO("prueba", "prueba", 2);

        Checkin checkin = new Checkin(checkpointDTO.facility, checkpointDTO.driver, checkpointDTO.dayOfMonth);

        Assertions.assertEquals("check in added", checkpointService.checkin(checkpointDTO));
        Mockito.verify(checkpointPort).saveCheckin(checkin);
    }

    @Test
    void Given_last_checkin_equals_null_When_invoke_checkout_Then_IllegalArgumentException(){
        CheckpointDTO checkpointDTO = new CheckpointDTO("prueba", "prueba", 2);

        Mockito.when(checkpointPort.findLastCheckin(checkpointDTO.driver, checkpointDTO.facility)).thenReturn(null);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checkpointService.checkout(checkpointDTO);
        });
        Mockito.verify(checkpointPort).findLastCheckin(checkpointDTO.driver, checkpointDTO.facility);
    }

    @Test
    void Given_day_of_moth_greater_than_30_When_invoke_checkout_Then_IllegalArgumentException(){
        CheckpointDTO checkpointDTO = new CheckpointDTO("prueba", "prueba", 31);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checkpointService.checkout(checkpointDTO);
        });
    }

    @Test
    void Given_day_of_moth_less_than_0_When_invoke_checkout_Then_IllegalArgumentException(){
        CheckpointDTO checkpointDTO = new CheckpointDTO("prueba", "prueba", 0);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            checkpointService.checkout(checkpointDTO);
        });
    }

    @Test
    void Given_day_of_moth_between_30_and_1_and_last_checkin_is_not_null_When_invoke_checkout_Then_success_message(){
        CheckpointDTO checkpointDTO = new CheckpointDTO("prueba", "prueba", 2);
        Checkout checkout = new Checkout(checkpointDTO.facility, checkpointDTO.driver, checkpointDTO.dayOfMonth);

        Mockito.when(checkpointPort.findLastCheckin(checkpointDTO.driver, checkpointDTO.facility)).thenReturn(new Checkin(checkpointDTO.facility, checkpointDTO.driver,  checkpointDTO.dayOfMonth));
        Assertions.assertEquals("check out added", checkpointService.checkout(checkpointDTO));
        Mockito.verify(checkpointPort).findLastCheckin(checkpointDTO.driver, checkpointDTO.facility);
        Mockito.verify(checkpointPort).saveCheckout(checkout);
    }
}
