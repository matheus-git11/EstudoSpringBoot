package io.github.matheusgit11.rest.dto;

/*
    "items": [
        {
            "produto" : 1,
            "quantidade" : 10
        }
    ]
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedidoDTO {  // objeto para representar o json passado ao criar um produto
    private Integer produto;
    private Integer quantidade;
}
