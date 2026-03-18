package com.javanalta.bffagendadortarefas.controller;


import com.javanalta.bffagendadortarefas.business.UsuarioService;
import com.javanalta.bffagendadortarefas.business.dto.in.EnderecoDTORequest;
import com.javanalta.bffagendadortarefas.business.dto.in.LoginRequestDTO;
import com.javanalta.bffagendadortarefas.business.dto.in.TelefoneDTORequest;
import com.javanalta.bffagendadortarefas.business.dto.in.UsuarioDTORequest;
import com.javanalta.bffagendadortarefas.business.dto.out.EnderecoDTOResponse;
import com.javanalta.bffagendadortarefas.business.dto.out.TelefoneDTOResponse;
import com.javanalta.bffagendadortarefas.business.dto.out.UsuarioDTOResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usaurio", description = "Cadastro e lgin e usuarios")
public class usuarioController {

    private final UsuarioService usuarioService;


    @PostMapping
    @Operation(summary = "salvar usuários", description = "Cria um novo usuário")
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<UsuarioDTOResponse> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO){
      return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));

    }
    @PostMapping("/login")
    @Operation(summary = "Login usuários", description = "Login do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado com sucesso")
    @ApiResponse(responseCode = "400", description = "Credenciais invalidas")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO){

        return usuarioService.loginUsuario(usuarioDTO);
    }
    @GetMapping
    @Operation(summary = "Buscar dados de usuários por email",
            description = "Buscar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode = "400", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<UsuarioDTOResponse> buscaUsuarioPorEmail(@RequestParam("email") String email,
                                                                   @RequestHeader(name ="Authorization",required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }
    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta usuários", description = "Deleta usuário")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<Void> deletaUsuarioPorEmail(@PathVariable String email,
                                                      @RequestHeader(name ="Authorization",required = false) String token){
        usuarioService.deletaUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualizar dados do usuário",
            description = "Atualizar dados do usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<UsuarioDTOResponse> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                   @RequestHeader(name ="Authorization",required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));

    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualizar endereço de usuário",
            description = "Atualizar endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<EnderecoDTOResponse> atualizaEndereco(@RequestBody EnderecoDTORequest dto,
                                                                @RequestParam("id") Long id,
                                                                @RequestHeader(name ="Authorization",required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualizar telfone de usuário",
            description = "Atualizar telefone de usuário")
    @ApiResponse(responseCode = "200", description = "telefone atualizado com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")

    public ResponseEntity<TelefoneDTOResponse> atualizaTelefon(@RequestBody TelefoneDTORequest dto,
                                                               @RequestParam("id") Long id,
                                                               @RequestHeader(name ="Authorization",required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva endereço de usuário",
            description = "Salva endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<EnderecoDTOResponse>cadastraEndereco(@RequestBody EnderecoDTORequest dto,
                                                               @RequestHeader("Authorization") String  token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva telefone de usuário",
            description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200", description = "telefone salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Telefone e usuário não cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro do servidor")
    public ResponseEntity<TelefoneDTOResponse>cadastraTelefone(@RequestBody TelefoneDTORequest dto,
                                                               @RequestHeader("Authorization") String  token){
        return ResponseEntity.ok(usuarioService.cadastroTelefone(token, dto));
    }


}
