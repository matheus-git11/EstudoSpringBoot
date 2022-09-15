package io.github.matheusgit11.rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/api/clientes") //definindo a url , toda requisiscao para api clientes vai cair neste controller
public class ClienteController {

    @RequestMapping(value = "/hello/{nome}" , method = RequestMethod.GET)
    @ResponseBody
    public String helloCliente(@PathVariable("nome") String nomeCliente){
        return String.format("Hello %s ",nomeCliente);
    }
}
