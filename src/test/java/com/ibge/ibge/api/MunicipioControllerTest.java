package com.ibge.ibge.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import javax.servlet.http.HttpServletResponse;
import com.ibge.ibge.api.interfaces.IMunicipioController;
import com.ibge.ibge.service.MunicipioService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(IMunicipioController.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class MunicipioControllerTest {
    @MockBean
    private MunicipioService municipioService;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HttpServletResponse response;

    @Test
    public void injectionDependencyMunicipioServiceSuccess()  {
        assertThat(this.municipioService).isNotNull();
    }

    @Test
    public void DownloadJsonSucesso() throws Exception {
        this.mockMvc.perform(get("/api/Municipio/DownloadJson/SC")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    public void DownloadCSVSucesso() throws Exception {
        this.mockMvc.perform(get("/api/Municipio/DownloadCSV/SC")).andDo(print()).andExpect(status().isOk());
    }    

    @Test
    public void DownloadCSVbyteSucesso() throws Exception {
        this.mockMvc.perform(get("/api/Municipio/DownloadCSVbyte/SC")).andDo(print()).andExpect(status().isOk());
    }   
    
    @Test
    public void GetIdMunicipioSucesso() throws Exception {
        this.mockMvc.perform(get("/api/Municipio/GetIdMunicipio/SC/Itajaí")).andDo(print()).andExpect(status().isOk());
    }    


}
