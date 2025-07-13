package com.api.rest.controller;

import com.api.rest.model.Empleado;
import com.api.rest.service.EmpleadoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class EmpleadoControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmpleadoService empleadoService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGuardarEmpleado() throws Exception {

        //given
        Empleado empleado = Empleado.builder()
                .id(1l)
                .nombre("fabian")
                .apellido("martinez")
                .email("fa@gmail.com")
                .build();
        given(empleadoService.saveEmpleado(any(Empleado.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        //when
        ResultActions response =mockMvc.perform(post("/Api/empleados")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empleado)));

        //then
        response.andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre",is(empleado.getNombre())))
                .andExpect(jsonPath("$.apellido",is(empleado.getApellido())))
                .andExpect(jsonPath("$.email",is(empleado.getEmail())));
    }
    @Test
    void testListarEmplados() throws Exception{

        //given
        List<Empleado> empleadoList = new ArrayList<>();
        empleadoList.add(Empleado.builder().nombre("fabian").apellido("martinez").email("fabi@egamil").build());
        empleadoList.add(Empleado.builder().nombre("eve").apellido("gonzales").email("eve@egamil").build());
        empleadoList.add(Empleado.builder().nombre("valen").apellido("gonza").email("valen@egamil").build());

        given(empleadoService.getAllEmpleados()).willReturn(empleadoList);

        //when
        ResultActions result = mockMvc.perform(get("/Api/empleados"));

        //then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.size()",is(empleadoList.size())));

    }
    @Test
    void testObtenerEmpleadoPorId() throws Exception{
          Long idEmpleado = 1l;

        //given
        Empleado empleado1 = Empleado.builder()
                .nombre("fabian")
                .apellido("martinez")
                .email("fabi@gmail").build();
        given(empleadoService.getEmpleadoById(idEmpleado)).willReturn(Optional.of(empleado1));

        //when
        ResultActions resultActions = mockMvc.perform(get("/Api/empleados/{id}",idEmpleado));

        //then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nombre",is(empleado1.getNombre())))
                .andExpect(jsonPath("$.apellido",is(empleado1.getApellido())))
                .andExpect(jsonPath("$.email",is(empleado1.getEmail())));


    }
    @Test
    void testObtenerEmpleadoVacio() throws Exception{
        Long idEmpleado = 1l;

        //given
        Empleado empleado1 = Empleado.builder()
                .nombre("fabian")
                .apellido("martinez")
                .email("fabi@gmail").build();
        given(empleadoService.getEmpleadoById(idEmpleado)).willReturn(Optional.empty());

        //when
        ResultActions resultActions = mockMvc.perform(get("/Api/empleados/{id}",idEmpleado));

        //then
        resultActions.andExpect(status().isNotFound())
                .andDo(print());


    }

    @Test
    void testActualizarEmpleadoNoEncontrado() throws Exception{

        long empleadoId = 1l;
        //given
        Empleado empleado1 = Empleado.builder()
                .nombre("fabian")
                .apellido("martinez")
                .email("fabi@gmail").build();


        Empleado empleadoActualizado = Empleado.builder()
                .nombre("eve")
                .apellido("gonza")
                .email("eve@gamail")
                .build();

        given(empleadoService.getEmpleadoById(empleadoId)).willReturn(Optional.empty());
        given(empleadoService.updateEmpleado(any(Empleado.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        //when

        ResultActions response = mockMvc.perform(put("/Api/empleados/{id}",empleadoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empleadoActualizado)));


        //then
        response.andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    void testActualizarEmpleado() throws Exception{

        long empleadoId = 1l;
        //given
        Empleado empleado1 = Empleado.builder()
                .nombre("fabian")
                .apellido("martinez")
                .email("fabi@gmail").build();


        Empleado empleadoActualizado = Empleado.builder()
                .nombre("eve")
                .apellido("gonza")
                .email("eve@gamail")
                .build();

        given(empleadoService.getEmpleadoById(empleadoId)).willReturn(Optional.of(empleado1));
        given(empleadoService.updateEmpleado(any(Empleado.class)))
                .willAnswer((invocation)-> invocation.getArgument(0));

        //when

        ResultActions response = mockMvc.perform(put("/Api/empleados/{id}",empleadoId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(empleadoActualizado)));


        //then
        response.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.nombre",is(empleadoActualizado.getNombre())))
                .andExpect(jsonPath("$.apellido",is(empleadoActualizado.getApellido())))
                .andExpect(jsonPath("$.email",is(empleadoActualizado.getEmail())));

    }
    @Test
    void testEliminarEmpleado() throws Exception{

        //given
        long empleadoId = 1l;

        willDoNothing().given(empleadoService).deleteEmpleado(empleadoId);

        //when
        ResultActions response = mockMvc.perform(delete("/Api/empleados/{id}",empleadoId));


        //then
        response.andExpect(status().isOk())
                .andDo(print());

    }
}
