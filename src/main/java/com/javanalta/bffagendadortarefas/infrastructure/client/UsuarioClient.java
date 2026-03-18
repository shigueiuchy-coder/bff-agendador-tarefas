package com.javanalta.bffagendadortarefas.infrastructure.client;


import com.javanalta.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.javanalta.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.javanalta.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.javanalta.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.javanalta.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.javanalta.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.javanalta.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @GetMapping
    UsuarioDTOResponse buscaUsuarioPorEmail(@RequestParam("email") String email,
                                            @RequestHeader(name ="Authorization", required = false) String token);

    @PostMapping
    UsuarioDTOResponse salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);


    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDTO);


    @DeleteMapping("/{email}")
    void  deletaUsuarioPorEmail(@PathVariable String email,
                                @RequestHeader(name ="Authorization", required = false) String token);

    @PutMapping
    UsuarioDTOResponse atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                            @RequestHeader(name ="Authorization", required = false) String token);


    @PutMapping("/endereco")
    EnderecoDTOResponse atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestParam("id") Long id);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizaTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestParam("id") Long id);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                         @RequestHeader("Authorization") String  token);


    @PostMapping("/telefone")
    TelefoneDTOResponse cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                         @RequestHeader("Authorization") String  token);

}
