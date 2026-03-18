package com.javanalta.bffagendadortarefas.business;


import com.javanalta.bffagendadortarefas.business.dto.in.TarefasDTORequest;
import com.javanalta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanalta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import com.javanalta.bffagendadortarefas.infrastructure.client.EmailClient;
import com.javanalta.bffagendadortarefas.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviaEmail (TarefasDTOResponse dto){
         emailClient.enviarEmail(dto);
    }

}
