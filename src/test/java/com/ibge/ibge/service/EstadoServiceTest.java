package com.ibge.ibge.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import java.io.ByteArrayOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

import com.ibge.ibge.externo.IIBGEEstado;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class EstadoServiceTest {
    @Autowired
    private EstadoService EstadoService;

    @Mock
    private IIBGEEstado ibgeEstado;

    @Test
    public void injectionDependencyEstadoServiceSuccess()  {
        assertThat(this.EstadoService).isNotNull();
    } 
    
    @Test
    public void injectionDependencyIBGEEstadoSuccess()  {
        assertThat(this.ibgeEstado).isNotNull();
    }

    @Test
    public void DownloadJsonSuccess() {
        String estadoJson = this.EstadoService.EstadosJson();
        assertThat(estadoJson).isNotNull();
        assertThat(estadoJson).isNotEmpty();
        assertThat(estadoJson.length()).isGreaterThan(0);
    }

    @Test
    public void DownloadCSVSuccess() {
        ByteArrayOutputStream estadoCSV = this.EstadoService.EstadosCSV();        
        assertThat(estadoCSV).isNotNull();
    }
}
