package io.github.matheusgit11.service.impl;

import io.github.matheusgit11.domain.entity.Cliente;
import io.github.matheusgit11.domain.entity.ItemPedido;
import io.github.matheusgit11.domain.entity.Pedido;
import io.github.matheusgit11.domain.entity.Produto;
import io.github.matheusgit11.domain.enums.StatusPedido;
import io.github.matheusgit11.domain.repository.Clientes;
import io.github.matheusgit11.domain.repository.ItemsPedido;
import io.github.matheusgit11.domain.repository.Pedidos;
import io.github.matheusgit11.domain.repository.Produtos;
import io.github.matheusgit11.exception.PedidoNaoEncontradoException;
import io.github.matheusgit11.exception.RegraNegocioException;
import io.github.matheusgit11.rest.dto.ItemPedidoDTO;
import io.github.matheusgit11.rest.dto.PedidoDTO;
import io.github.matheusgit11.service.PedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;

    private final ItemsPedido itemsPedidoRepository;



    @Override
    @Transactional // garantir que ou tudo acontece com sucesso ou se algo acontecer ira acontecer um ROLLBACK , tudo sendo desfeito
    public Pedido salvar(PedidoDTO dto) {
        Integer idCliente = dto.getCliente();
        Cliente cliente = clientesRepository.findById(idCliente).orElseThrow(() -> new RegraNegocioException("Codigo de cliente invalido"));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItems());
        repository.save(pedido);
        itemsPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    private List<ItemPedido> converterItems(Pedido pedido ,List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("nao e possivel realizar seu pedido sem items");
        }
        return items
                .stream()
                .map(dto ->{
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(() -> new RegraNegocioException("Codigo de Produto invalido : " + idProduto));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());
    }

    @Override
    public Optional<Pedido> obterPedidoCompleto(Integer id) {
        return repository.findByIdFetchItens(id);
    }

    @Override
    @Transactional
    public void atualizaStatus(Integer id, StatusPedido status) {
        repository.findById(id)
                  .map(pedido -> {
                      pedido.setStatus(status);
                      return repository.save(pedido);
                  }).orElseThrow( () -> new PedidoNaoEncontradoException());
    }


}
