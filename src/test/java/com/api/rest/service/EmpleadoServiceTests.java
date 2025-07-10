package com.api.rest.service;

import com.api.rest.exception.ResourceNotFoundException;
import com.api.rest.model.Empleado;
import com.api.rest.repository.EmpleadoRepository;
import com.api.rest.service.impl.EmpleadoServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class EmpleadoServiceTests {


    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoServiceImpl empleadoService;

    private Empleado empleado;

    @BeforeEach
    void setup(){
        empleado = Empleado.builder()
                .id(0l)
                .nombre("fabian")
                .apellido("martinez")
                .email("fa@gmail.com")
                .build();
    }

    @DisplayName("Test para guardar empleado")
    @Test
    void testGuardarEmpleado(){

        //given
        given(empleadoRepository.findByEmail(empleado.getEmail()))
                .willReturn(Optional.empty());
        given(empleadoRepository.save(empleado)).willReturn(empleado);

        //when
        Empleado empleadoGuardado = empleadoService.saveEmpleado(empleado);

        //then
        assertThat(empleadoGuardado).isNotNull();
    }

    @DisplayName("Test para guardar un empleado con throw EXCEPTION")
    @Test
    void testGuardarEmpleadoConThrowException(){

        //given
        given(empleadoRepository.findByEmail(empleado.getEmail()))
                .willReturn(Optional.of(empleado));

        //when
         assertThrows(ResourceNotFoundException.class,()->{
            empleadoService.saveEmpleado(empleado);
         });

         //then
        verify(empleadoRepository,never()).save(any(Empleado.class));
    }

    @DisplayName("Test para listar Empleados")
    @Test
    void testListarEmpleados(){
        //given
        Empleado empleado1 = Empleado.builder()
                .nombre("valen")
                .apellido("gonzaa")
                .email("valen@gmail")
                .id(1l).build();
        given(empleadoRepository.findAll()).willReturn(List.of(empleado,empleado1));

        //when
        List<Empleado> listaEmpleados = empleadoService.getAllEmpleados();

        //then
        assertThat(listaEmpleados).isNotNull();
        assertThat(listaEmpleados.size()).isEqualTo(2);
    }

    @DisplayName("Test para Listar Empleados vacios")
    @Test
    void testListaEmpleadosVacios(){
        //given
        Empleado empleado1 = Empleado.builder()
                .nombre("valen")
                .apellido("gonzaa")
                .email("valen@gmail")
                .id(1l).build();
        given(empleadoRepository.findAll()).willReturn(Collections.emptyList());

        //when
        List<Empleado> listaEmpleados = empleadoService.getAllEmpleados();

        //then
        assertThat(listaEmpleados).isEmpty();
        assertThat(listaEmpleados.size()).isEqualTo(0);

    }

    @DisplayName("Obtener empleado por id")
    @Test
    void testObtenerEmpleadoPorId(){


        //given
        given(empleadoRepository.findById(0l)).willReturn(Optional.of(empleado));

        //when
        Empleado empleadoBd = empleadoService.getEmpleadoById(empleado.getId()).get();

        //then
        assertThat(empleadoBd).isNotNull();

    }

    @DisplayName("Test actualizar empleado")
    @Test
    void testActualizarEmpleado(){

        //given
        given(empleadoRepository.save(empleado)).willReturn(empleado);
            empleado.setNombre("hellow");
            empleado.setEmail("emailNuevo");

        //when
        Empleado empleadoActualizado = empleadoService.updateEmpleado(empleado);

        //then
        assertThat(empleadoActualizado.getNombre()).isEqualTo("hellow");
        assertThat(empleadoActualizado.getEmail()).isEqualTo("emailNuevo");



    }


    @DisplayName("Test para Eliminar un Empleado")
    @Test
    void testElimirarEmpleado(){
        //given
        long empleadoId = 0l;
        willDoNothing().given(empleadoRepository).deleteById(empleadoId);

        //when
        empleadoService.deleteEmpleado(empleadoId);

        //then
        verify(empleadoRepository,times(1)).deleteById(empleadoId);
    }
}
