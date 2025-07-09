package com.api.rest.repository;

import com.api.rest.model.Empleado;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@DisplayName("Test para guardar un empleado")
@DataJpaTest
public class EmpleadoRepositoryTests {


    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Test
    void testGuardarEmpleado(){

        //given - dado o condicion previa o configuracion
        Empleado empleado = Empleado.builder()
                .nombre("pepe")
                .apellido("lipez")
                .email("pepe@gamial.com")
                .build();

        //when - accion o el comportamiento que vamos a hacer

        Empleado empleadoGuardado = empleadoRepository.save(empleado);

        //then - verificar la salida

        assertThat(empleadoGuardado).isNotNull();
        assertThat(empleadoGuardado.getId()).isGreaterThan(0);
    }

}
