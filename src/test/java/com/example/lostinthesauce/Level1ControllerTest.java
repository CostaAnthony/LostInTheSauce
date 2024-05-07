package com.example.lostinthesauce;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Level1ControllerTest {

    @Test
    public void testCoin1ValueGetterSetter() {
        level1Controller controller = new level1Controller();
        controller.setCoin1Value(100);
        assertEquals(100, controller.getCoin1Value());
    }
    @Test
    public void testCoin2ValueGetterSetter() {
        level1Controller controller = new level1Controller();
        controller.setCoin1Value(100);
        assertEquals(100, controller.getCoin1Value());
    }

}

