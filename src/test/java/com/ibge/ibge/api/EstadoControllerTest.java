package com.ibge.ibge.api;


import org.junit.Test;
import lombok.var;
import com.ibge.ibge.model.Estado;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

public class EstadoControllerTest extends AbstractController{
    @Override
    @org.junit.Before
    public void setUp() {
        super.setUp();
    } 
    
    @Test
    public void DownloadJsonSucesso() throws Exception {
        var result = this.mvc.perform(get("/api/estado/DownloadJson")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();
        var json = super.mapFromJson(result.getResponse().getContentAsString(), Estado[].class);
        assertThat(json).isNotNull();
        assertTrue(json.length >= 0);
    } 

}
