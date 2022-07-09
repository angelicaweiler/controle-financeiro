package com.papler.controlefinanceiro.api;

import com.papler.controlefinanceiro.api.dto.*;
import com.papler.controlefinanceiro.business.service.BO.TotalValoresCreditoBO;
import com.papler.controlefinanceiro.business.service.BO.TotalValoresDebitoBO;
import com.papler.controlefinanceiro.business.service.ValoresPagarService;
import com.papler.controlefinanceiro.business.service.ValoresReceberService;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controle-financeiro")
@Api(value = "controle-financeiro")
public class ControleFinanceiroController {

    private final ValoresReceberService service;
    private final ValoresPagarService pagarService;

    public ControleFinanceiroController(ValoresReceberService service, ValoresPagarService pagarService) {
        this.service = service;
        this.pagarService = pagarService;
    }

    @ApiOperation(value = "Cadastra os valores a receber", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cadastramento efetuado com sucesso", response = ValoresReceberOutDTO.class),
            @ApiResponse(code = 500, message = "Erro ao realizar o cadastramento", response = ResponseEntity.class),
    })
    @PostMapping("/credito")
    public ResponseEntity<ValoresReceberOutDTO> cadastrar(@RequestBody ValoresReceberInDTO dto) {
        return ResponseEntity.ok(service.cadastrarValores(dto));
    }

    @ApiOperation(value = "Busca o crédito por tipo e altera seu status", httpMethod = "PATCH")
    @ApiParam(name = "tipoCredito", value = "Nome do Pagador", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status alterado com sucesso", response = ValoresReceberOutDTO.class),
            @ApiResponse(code = 500, message = "Erro ao alterar status", response = ResponseEntity.class),
    })
    @PatchMapping("/credito")
    public ResponseEntity<ValoresReceberOutDTO> alterarStatus(@RequestParam("tipoCredito") String tipoCredito) {

        return ResponseEntity.ok(service.alterarStatus(tipoCredito));
    }

    @ApiOperation(value = "Retorna o total de valores recebidos e à receber", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total de valores recebidos e à receber"),
            @ApiResponse(code = 500, message = "Erro ao buscar o total de recebidos"),
    })
    @GetMapping("/credito")
    public ResponseEntity<TotalValoresCreditoBO> buscarTotal() {
        return ResponseEntity.ok(service.buscarValores());
    }


    @ApiOperation(value = "Retorna os creditos cadastrados", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total de creditos cadastrados"),
            @ApiResponse(code = 500, message = "Erro ao buscar o total de creditos"),
    })
    @GetMapping("/todos-creditos")
    public ResponseEntity<List<ValoresReceberTotalOutDTO>> buscarTotalCadastros() {
        List<ValoresReceberTotalOutDTO> dto = service.buscarTodos();

        return ResponseEntity.ok(dto);
    }

    @ApiOperation(value = "Cadastra os valores devidos", httpMethod = "POST")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Cadastramento efetuado com sucesso", response = ValoresReceberOutDTO.class),
            @ApiResponse(code = 500, message = "Erro ao realizar o cadastramento", response = ResponseEntity.class),
    })
    @PostMapping("/debito")
    public ResponseEntity<ValoresPagarOutDTO> cadastrarDebitos(@RequestBody ValoresPagarInDTO dto) {
        return ResponseEntity.ok(pagarService.cadastrarValores(dto));
    }

    @ApiOperation(value = "Retorna o total de valores devidos e pagos", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total de valores devidos e pagos"),
            @ApiResponse(code = 500, message = "Erro ao buscar o total devido"),
    })
    @GetMapping("/debito")
    public ResponseEntity<TotalValoresDebitoBO> buscarTotalDevido() {
        return ResponseEntity.ok(pagarService.buscarValores());
    }


    @ApiOperation(value = "Retorna os debitos cadastrados", httpMethod = "GET")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna o total de debitos cadastrados"),
            @ApiResponse(code = 500, message = "Erro ao buscar o total de debitos"),
    })
    @GetMapping("/todos-debitos")
    public ResponseEntity<List<ValoresPagarTodosOutDTO>> buscarTotalDebitosCadastrados() {
        List<ValoresPagarTodosOutDTO> dto = pagarService.buscarTodos();

        return ResponseEntity.ok(dto);
    }

    @ApiOperation(value = "Busca o debito por tipo e altera seu status", httpMethod = "PATCH")
    @ApiParam(name = "tipoDebito", value = "Nome da Conta", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status alterado com sucesso", response = ValoresReceberOutDTO.class),
            @ApiResponse(code = 500, message = "Erro ao alterar status", response = ResponseEntity.class),
    })
    @PatchMapping("/debito")
    public ResponseEntity<ValoresPagarOutDTO> alterarStatusDebito(@RequestParam("tipoDebito") String tipoDebito) {

        return ResponseEntity.ok(pagarService.alterarStatus(tipoDebito));
    }


}
