package br.com.ifpe.oxefood.api.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import br.com.ifpe.oxefood.modelo.acesso.UsuarioService;
import br.com.ifpe.oxefood.modelo.cliente.Cliente;
import br.com.ifpe.oxefood.modelo.cliente.ClienteService;
import br.com.ifpe.oxefood.modelo.cliente.EnderecoCliente;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Tag(
    name = "API Cliente",
    description = "API responsável pelos servidos de cliente no sistema"
)


@RestController
@RequestMapping("/api/cliente")
@CrossOrigin

public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private UsuarioService usuarioService;

    @Operation(
       summary = "Serviço responsável por salvar um cliente no sistema."
   )
    @PostMapping
    public ResponseEntity<Cliente> save(@RequestBody @Valid ClienteRequest clienteRequest, HttpServletRequest request) {

        Cliente cliente = clienteService.save(clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Serviço responsável por listar todos os clientes no sistema."
    )
    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @Operation(
        summary = "Serviço responsável por buscar um cliente no sistema pelo ID."
    )
    @GetMapping("/{id}")
    public Cliente obterPorID(@PathVariable Long id) {
        return clienteService.obterPorID(id);
    }


    @Operation(
        summary = "Serviço responsável por alterar um cliente no sistema pelo ID."
    )
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable("id") Long id, @RequestBody ClienteRequest clienteRequest, HttpServletRequest request) {

	    clienteService.update(id, clienteRequest.build(), usuarioService.obterUsuarioLogado(request));
	    return ResponseEntity.ok().build();
    }


    @Operation(
        summary = "Serviço responsável por deletar um cliente no sistema pelo ID."
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        clienteService.delete(id);
        return ResponseEntity.ok().build();
    }

    // Criando métodos para criar, alterar e excluir endereço de um cliente

    @Operation(
        summary = "Serviço responsável por cadastrar um endereço do cliente pelo ID."
    )
    @PostMapping("/endereco/{clienteId}")
    public ResponseEntity<EnderecoCliente> adicionarEnderecoCliente(@PathVariable("clienteId") Long clienteId,
            @RequestBody @Valid EnderecoClienteRequest request) {

        EnderecoCliente endereco = clienteService.adicionarEnderecoCliente(clienteId, request.build());
        return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.CREATED);
    }

    @Operation(
        summary = "Serviço responsável por buscar um endereço do cliente pelo ID."
    )
    @PutMapping("/endereco/{enderecoId}")
    public ResponseEntity<EnderecoCliente> atualizarEnderecoCliente(@PathVariable("enderecoId") Long enderecoId,
            @RequestBody EnderecoClienteRequest request) {

        EnderecoCliente endereco = clienteService.atualizarEnderecoCliente(enderecoId, request.build());
        return new ResponseEntity<EnderecoCliente>(endereco, HttpStatus.OK);
    }

    @Operation(
        summary = "Serviço responsável por deletrar um endereço do cliente pelo ID."
    )
    @DeleteMapping("/endereco/{enderecoId}")
    public ResponseEntity<Void> removerEnderecoCliente(@PathVariable("enderecoId") Long enderecoId) {

        clienteService.removerEnderecoCliente(enderecoId);
        return ResponseEntity.noContent().build();
    }

}
