package com.javanalta.bffagendadortarefas.business;

import com.javanalta.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.javanalta.bffagendadortarefas.business.dto.out.TarefasDTOResponse;
import com.javanalta.bffagendadortarefas.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void BuscaTarefasProximaHora(){
        String token = login(converterParaRequestDTO());
        log.info("Iniciada a busca de tarefas");
        //hora atual + uma hora
        LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
        //se agora é 22h = qualquer tarefa entre 23h e 23h05
        LocalDateTime horasFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);

        List<TarefasDTOResponse> listaTarefa = tarefasService.buscaTarefasAgendadasPorPeriodo(horaFutura, horasFuturaMaisCinco, token );
        log.info("Tarefas encontradas" + listaTarefa);
        listaTarefa.forEach(tarefa -> {
            emailService.enviaEmail(tarefa);
            log.info("Email enviado para o usuario" + tarefa.getEmailUsuario());
            tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, tarefa.getId(),
                token);
        });
        log.info("Finalizada a busca e notificação de tarefas");
    }

    public String login(LoginRequestDTO dto){
        return usuarioService.loginUsuario(dto);
    }

    public LoginRequestDTO converterParaRequestDTO(){
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }

}
