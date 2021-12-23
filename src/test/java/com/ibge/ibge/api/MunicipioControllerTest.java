package com.ibge.ibge.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.var;

import com.ibge.ibge.model.Municipio;
import com.ibge.ibge.service.MunicipioService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

public class MunicipioControllerTest extends AbstractController {
    @MockBean
    private MunicipioService municipioService;

    @Override
    @org.junit.Before
    public void setUp() {
        super.setUp();
    }   

    @Test
    public void injectionDependencyMunicipioServiceSuccess()  {
        assertThat(this.municipioService).isNotNull();
    }

    @Test
    public void DownloadJsonSucesso() throws Exception {
        var result = this.mvc.perform(get("/api/municipio/DownloadJson/{uf}", "SC")                
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        var json = super.mapFromJson(result.getResponse().getContentAsString(), Municipio[].class);
        assertThat(json).isNotNull();
        assertTrue(json.length >= 0);    
    }   
    
    @Test
    public void GetIdMunicipioSucesso() throws Exception {
        this.mvc.perform(get("/api/municipio/GetIdMunicipio/SC/Navegantes")).andDo(print()).andExpect(status().isOk());
    }   
}

