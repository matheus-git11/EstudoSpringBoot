package io.github.matheusgit11.rest.dto;
/*
{
    "cliente" : 1,
    "total" : 100,
    "items": [
        {
            "produto" : 1,
            "quantidade" : 10
        }
    ]
}
 */

import io.github.matheusgit11.validation.NotEmptyList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoDTO { // objeto para representar o json passado ao criar um produto
    @NotNull(message = "{campo.codigo-cliente.obrigatorio}")
    private Integer cliente;
    @NotNull(message = "{campo.total-pedido.obrigatorio}")
    private BigDecimal total;
    @NotEmptyList(message = "{campo.items-pedido.obrigatorio}") // anotacao customizada / validacao customizada
    private List<ItemPedidoDTO> items;

}
