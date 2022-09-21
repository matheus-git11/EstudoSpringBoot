package io.github.matheusgit11.rest.dto;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoItemPedidoDTO { // classe feita para filtrar o que mostramos ao procurar um pedido
    private String descricaoProduto;   // feita em conjkunto com informacaoPedidoDTO
    private BigDecimal precoUnitario;
    private Integer quantidade;
}
