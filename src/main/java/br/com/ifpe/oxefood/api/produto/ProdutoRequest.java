package br.com.ifpe.oxefood.api.produto;

import org.hibernate.validator.constraints.Length;

import br.com.ifpe.oxefood.modelo.produto.Produto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Ele é  ajunção do get e o set
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ProdutoRequest {

    private Long idCategoria;

    @NotBlank(message = "O código é de preenchimento obrigatório")
    private String codigo;
    
    @NotBlank(message = "O título é de preenchimento obrigatório")
    @Length(max = 100, message = "O título deverá ter no máximo {max} caracteres")
    private String titulo;

    private String descricao;

    @NotNull(message = "O valor unitário é de preenchimento obrigatório")
    private Double valorUnitario;

    private Integer tempoEntregaMinimo;
    
    private Integer tempoEntregaMaximo;

    public Produto build() {

        return Produto.builder()
            .codigo(codigo)
            .titulo(titulo)
            .descricao(descricao)
            .valorUnitario(valorUnitario)
            .tempoEntregaMinimo(tempoEntregaMinimo)
            .tempoEntregaMaximo(tempoEntregaMaximo)
            .build();
    }

    public Long getIdCategoriaProduto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getIdCategoriaProduto'");
    }
}
