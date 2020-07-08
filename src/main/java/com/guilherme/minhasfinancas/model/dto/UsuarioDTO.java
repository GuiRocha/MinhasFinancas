package com.guilherme.minhasfinancas.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
public class UsuarioDTO {
    private String email;
    private String nome;
    private String senha;

}
