package com.api.rest.repository;

import com.api.rest.model.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;



@DataJpaTest
public class EmpleadoRepositoryTests {


    @Autowired
    private EmpleadoRepository empleadoRepository;

   private Empleado empleado;

    @BeforeEach
     void setup(){
        empleado = Empleado.builder()
                .nombre("fabian")
                .apellido("martinez")
                .email("fa@gmail.com")
                .build();
    }

    @DisplayName("Test para guardar un empleado")
    @Test
    void testGuardarEmpleado(){

        //given - dado o condicion previa o configuracion
        Empleado empleado2 = Empleado.builder()
                .nombre("pepe")
                .apellido("lipez")
                .email("pepe@gamial.com")
                .build();

        //when - accion o el comportamiento que vamos a hacer

        Empleado empleadoGuardado = empleadoRepository.save(empleado2);

        //then - verificar la salida

        assertThat(empleadoGuardado).isNotNull();
        assertThat(empleadoGuardado.getId()).isGreaterThan(0);
    }

    @DisplayName("Test para listar los empleados")
    @Test
    void testListarEmpleo(){

        //given
        Empleado empleado1 = Empleado.builder()
                .nombre("eve")
                .apellido("gonzales")
                .email("eve@gamil.com")
                .build();

        //when
        empleadoRepository.save(empleado1);
        empleadoRepository.save(empleado);

        //the
        List<Empleado> listaDeEmpleados = empleadoRepository.findAll();
        assertThat(listaDeEmpleados).isNotNull();
        assertThat(listaDeEmpleados.size()).isEqualTo(2);
    }

    @DisplayName("Test Para Obtener Empleado Por ID")
    @Test
    void testObtenerEmpleadoPorId(){

        empleadoRepository.save(empleado);

        //when
        Empleado empleadoBd = empleadoRepository.findById(empleado.getId()).get();

        //then
        assertThat(empleadoBd).isNotNull();

    }

    @DisplayName("Test actualizando Empleado")
    @Test
    void testActualizarEmpleado(){
        empleadoRepository.save(empleado);

        //when
        Empleado empleadoGuardado = empleadoRepository.findById(empleado.getId()).get();
        empleadoGuardado.setNombre("agus");
        empleadoGuardado.setApellido("gonza");
        empleadoGuardado.setEmail("agus@gmail.com");

        Empleado empleadoActualizado = empleadoRepository.save(empleadoGuardado);

        //then
        assertThat(empleadoActualizado.getNombre()).isEqualTo("agus");
        assertThat(empleadoActualizado.getApellido()).isEqualTo("gonza");
        assertThat(empleadoActualizado.getEmail()).isEqualTo("agus@gmail.com");


    }
    @DisplayName("Test para eliminar un empleado")
    @Test
    void testEliminarEmpleado(){
        empleadoRepository.save(empleado);

        //when
        empleadoRepository.deleteById(empleado.getId());
        Optional<Empleado > empleadoEliminado = empleadoRepository.findById(empleado.getId());

        //then
        assertThat(empleadoEliminado).isEmpty();

    }

}
