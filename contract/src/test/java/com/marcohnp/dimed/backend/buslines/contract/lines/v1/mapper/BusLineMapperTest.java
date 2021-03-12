package com.marcohnp.dimed.backend.buslines.contract.lines.v1.mapper;

import com.marcohnp.dimed.backend.buslines.contract.lines.v1.stub.BusLineStub;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BusLineMapperTest {



    @Test
    void must_return_true_when_constructor_is_private() throws NoSuchMethodException {
        assertTrue(Modifier.isPrivate(BusLineMapper.class
                .getDeclaredConstructor().getModifiers()));
    }

    @Test
    void mapToResponse_must_return_response() {
        var busLine = BusLineStub.createBusLine();
        var expected = BusLineStub.createBusLine();
        var actual = BusLineMapper.mapToResponse(busLine);
        assertEquals(expected, actual);
    }
}
