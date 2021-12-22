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

import com.ibge.ibge.externo.IIBGEMunicipio;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class MunicipioServiceTest {
    @Autowired
    private MunicipioService municipioService;

    @Mock
    private IIBGEMunicipio iibgeMunicipio;

    @Test
    public void injectionDependencyMunicipioServiceSuccess()  {
        assertThat(this.municipioService).isNotNull();
    } 
    
    @Test
    public void injectionDependencyIBGEMunicipioSuccess()  {
        assertThat(this.iibgeMunicipio).isNotNull();
    }

    @Test
    public void DownloadJsonSuccess() {
        String municiosJson = this.municipioService.MunicipiosJson("SC");
        assertThat(municiosJson).isNotNull();
        assertThat(municiosJson).isNotEmpty();
        assertThat(municiosJson.length()).isGreaterThan(0);
    }

    @Test
    public void DownloadCSVSuccess() {
        ByteArrayOutputStream municiosCSV = this.municipioService.MunicipiosCSV("SC");
        assertThat(municiosCSV).isNotNull();                
        assertThat(municiosCSV.size()).isGreaterThan(0);
    }

    @Test
    public void GetIdMunicipioSuccess() {
        Long IdMunicipio = this.municipioService.GetIdMunicipioPorNome("SC", "Itajaí");
        assertThat(IdMunicipio).isNotNull();        
        assertThat(IdMunicipio).isNotZero();
        assertThat(IdMunicipio).isGreaterThan(0);
    }    
}
