package br.com.ifpe.oxefood.api.empresa;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;


import br.com.ifpe.oxefood.modelo.empresa.Empresa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Ele é  a junção do get e o set
@Builder
@NoArgsConstructor
@AllArgsConstructor



public class EmpresaRequest {
    private String site;

    private String cnpj;

    private String inscricaoEstadual;
 
    private String nomeEmpresarial;

    private String nomeFantasia;

    private String fone;
 
    private String foneAlternativo;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEmpresa;

    private Boolean empresaStatus;

   
   public Empresa build() {

       return Empresa.builder()
           .site(site)
           .cnpj(cnpj)
           .inscricaoEstadual(inscricaoEstadual)
           .nomeEmpresarial(nomeEmpresarial)
           .nomeFantasia(nomeFantasia)
           .fone(fone)
           .foneAlternativo(foneAlternativo)
           .dataEmpresa(dataEmpresa)
           .empresaStatus(empresaStatus)
           .build();
   }

}
