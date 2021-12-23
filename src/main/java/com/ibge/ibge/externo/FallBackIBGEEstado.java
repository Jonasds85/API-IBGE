package com.ibge.ibge.externo;

import java.util.ArrayList;
import java.util.List;
import com.ibge.ibge.model.Estado;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FallBackIBGEEstado implements IIBGEEstado{
    @Override
    public List<Estado> Estados() {
        log.error("Fallback IGBE Estados");
        return new ArrayList<Estado>(); 
   }
    
}
