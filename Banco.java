import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Banco{
    public static class Extrato{
        String tipoTrasacao; // aqui só existem 3 tipos: depósito, saque ou transferência
        Double valorMovimentacao; // aqui aparece o valor do tipo da transação
    }
    
     // criação da lista de extratos

    public static class Cliente{
        String nome;
        String conta;
        String CPF;
        String agencia;
        Double saldo;
        String senha;
        String chavePix;
        List<Extrato> listaExtratos = new ArrayList<>(); // criei uma lista de extratos aqui, pq assim cada cliente tem seu extrato 
    }

    


    static double consultarSaldo(Cliente cliente){
        return cliente.saldo;
    }

    // Função de Realizar Depósito abaixo playbas
    static void realizarDeposito(Cliente cliente, Double valorDeposito){
        cliente.saldo += valorDeposito; // ajeitei a função para ela apenas executar
        Extrato extratoTemporario = new Extrato();
        extratoTemporario.tipoTrasacao = "Depósito";
        extratoTemporario.valorMovimentacao = valorDeposito;
        cliente.listaExtratos.add(extratoTemporario);
        

    }

    //Fução do PIX - Versão beta 1.2v (CASO MODIFICADO, ALTERAR A VERSÃO BETA PARA 1.3v!)
    static void realizarTranferenciaPix(Cliente clientePagador, Cliente clienteRecebedor, Double valorTransferencia) {
        //aqui esta a redução do saldo do cliente pagador (Lázaro)
        clientePagador.saldo -= valorTransferencia;
        //aqu esta o aumento do saldo do cliente recebedor (Lázaro)
        clienteRecebedor.saldo += valorTransferencia;

        //a integração ao extrato deixa que eu faço (Miguel)
        Extrato extratoPagador = new Extrato();
        Extrato extratoRecebedor = new Extrato();
        extratoPagador.tipoTrasacao = "Transferência";
        extratoRecebedor.tipoTrasacao = "Transferência";
        extratoPagador.valorMovimentacao = valorTransferencia;
        extratoRecebedor.valorMovimentacao = valorTransferencia;
        clientePagador.listaExtratos.add(extratoPagador);
        clienteRecebedor.listaExtratos.add(extratoRecebedor);
        
    }

    //Fução do TED - Versão beta 1.0v (CASO MODIFICADO, ALTERAR A VERSÃO BETA PARA 1.1v!)
    static void realizarTransferenciaTed(Cliente clientePagador, Double taxa, Cliente clienteRecebedor, Double valorTransferencia) {
        //aqui deve ficar apenas a redução do saldo do cliente pagador (Lázaro)
        clientePagador.saldo -= valorTransferencia + taxa;
        //aumento do saldo do cliente recebedor (Lázaro)
        clienteRecebedor.saldo += valorTransferencia;

        //a integração ao extrato deixa que eu faço (Miguel)
        Extrato extratoPagador = new Extrato();
        Extrato extratoRecebedor = new Extrato();
        extratoPagador.tipoTrasacao = "Transferência";
        extratoRecebedor.tipoTrasacao = "Transferência";
        extratoPagador.valorMovimentacao = valorTransferencia+taxa; 
        extratoRecebedor.valorMovimentacao = valorTransferencia; //retirei a soma da taxa aqui pq como a taxa de TED é paga inteiramente por quem envia (o pagador), o recebedor só ganha o valorTransferencia puro (Enzo)
        clientePagador.listaExtratos.add(extratoPagador);
        clienteRecebedor.listaExtratos.add(extratoRecebedor);
    }

    static Extrato maiorDeposito(Cliente cliente){
        Extrato extratoMaiorDeposito = new Extrato();
        extratoMaiorDeposito = cliente.listaExtratos.get(0);
        for (int i= 1; i < cliente.listaExtratos.size(); i++){
            Extrato comparativo = new Extrato();
            comparativo = cliente.listaExtratos.get(i);
            if (comparativo.tipoTrasacao.equals("Depósito") && extratoMaiorDeposito.tipoTrasacao.equals("Depósito") ){
                if(extratoMaiorDeposito.valorMovimentacao < comparativo.valorMovimentacao){
                    extratoMaiorDeposito = comparativo;
                }
           }
        }
        
        return extratoMaiorDeposito;
    }

    public static void main(String[] args){
        
        Cliente[] listaClientes = new Cliente[2]; // começar a criar os dois exemplos de cliente
        Cliente cliente1 = new Cliente();
        cliente1.nome = "Roberto";
        cliente1.conta = "123";
        cliente1.CPF = "12345678900";
        cliente1.agencia = "456";
        cliente1.saldo = 1000.0;
        cliente1.senha = "senha-secreta";
        cliente1.chavePix = "chavepix2";

        Cliente cliente2 = new Cliente();
        cliente2.nome = "Cláudio";
        cliente2.conta = "678";
        cliente2.CPF = "23412345699";
        cliente2.agencia = "789";
        cliente2.saldo = 1000.0;
        cliente2.senha = "senha-mais-secreta";
        cliente2.chavePix = "chavepix1";

        listaClientes[0]  = cliente1; 
        listaClientes[1]  = cliente2; // O array listaClientes armazenará todos os clientes do nosso sistema
        
        while(true){
            Cliente clienteLogado; // Quando o login for realizado, vamos saber qual foi o cliente que logou
            System.out.println("Olá! Bem vindo ao sistema bancário do Weed Revela!");
            System.out.println("Para prosseguir, Por favor, realize o login:");
            Scanner leia = new Scanner(System.in);
            validacao: // aqui eu criei um rótulo, pra n ficar preso dentro do loop de verificar o login
            while (true){
                System.out.println("Digite o seu CPF sem pontos e traços: \n");
                String checarCPF = leia.next();
                System.out.println("Digite a sua senha: \n");
                String checarSenha = leia.next();
                
                for(Cliente cliente : listaClientes){
                    if(checarCPF.equals(cliente.CPF) && checarSenha.equals(cliente.senha)){
                        clienteLogado = cliente; // O cliente logado vai receber o cliente que entrou e dps sai do while true
                        break validacao;
                    }
                }
                System.out.println("Credenciais incorretas! Tente novamente.");
            }

            System.out.println("Olá " + clienteLogado.nome + "! Bem vindo ao Weed Revela!");
            saidaMenu:
            while (true){
                System.out.println("===== BANCO Weed Revela  =====");
                System.out.println("1 - Consultar saldo");
                System.out.println("2 - Realizar depósito");
                System.out.println("3 - Realizar saque");
                System.out.println("4 - Exibir extrato");
                System.out.println("5 - Mostrar maior depósito");
                System.out.println("6 - Realizar transferência");
                System.out.println("0 - Sair");
                System.out.println("Escolha uma opção:");
                int opcao;

                while(true){
                    opcao = leia.nextInt();
                    if(List.of(1,2,3,4,5,6,0).contains(opcao)){
                        break;
                    }
                    System.out.println("Opção inválida! Escreva novamente");
                }
                switch(opcao){ // depois trocar isso aqui por um RULE SWITCH
                    case 0:
                        System.out.println("até a próxima vez! \n");
                        break saidaMenu;

                    case 1:
                        //aqui vai chamar a função de consultar saldo
                        Double saldoAtual = consultarSaldo(clienteLogado);
                        System.out.printf("\nSeu saldo atual é: R$ %.2f\n\n", saldoAtual);
                        break;
                    case 2:
                        //aqui vai chamar a função de realizar depósito
                        System.out.print("Digite o valor que deseja depositar:");
                        double valor = leia.nextDouble();
                        if(valor<=0){
                            System.out.print("Depósito Inválido!");
                        }
                        else{
                            realizarDeposito(clienteLogado, valor);
                            System.out.printf("Depósito de R$ %.2f%n realizado com sucesso!", valor);
                        }
                        break;
                    case 3:
                        
                        // agora aqui é a função de realizar saque
                        break;
                    case 4:
                        // aqui é a função de exibir o extrato
                        System.out.println("-------------------------------------------");
                        for( Extrato item : clienteLogado.listaExtratos){
                            System.out.println("Tipo de transação: " + item.tipoTrasacao);
                            System.out.println("Valor: R$" + item.valorMovimentacao);
                            System.out.println("------------------------------------------- \n");
                        }
                        break;
                    case 5:
                        // aqui vai mostrar o maior depósito já realizado
                        Extrato maiorDepRealizado = maiorDeposito(clienteLogado);
                        System.out.println("-------------------------------------------");
                        System.out.println("Tipo do depósito: " + maiorDepRealizado.tipoTrasacao);
                        System.out.println("Valor: R$" + maiorDepRealizado.valorMovimentacao);
                        System.out.println("-------------------------------------------");

                        break;
                    case 6:
                        System.out.println("1 - Realizar por PIX");
                        System.out.println("2 - Transferecia por TED");
                        System.out.println("Escolha uma opção:");
                        int menusaque;
                        while(true){ // adicionei só uma verificação caso o usuário erre o número
                            menusaque = leia.nextInt();
                            if (menusaque > 0 && menusaque < 3){
                                break;
                            }
                            System.out.println("Opção inválida! Escreva apenas 1 ou 2 !");
                        }
                        
                        switch (menusaque) {
                            case 1:
                                //primeiro precisa perguntar para o usuário qual a chave Pix do destinatário
                                System.out.println("Digite a chave Pix do destinatário:");
                                String chavePixDestinatario = leia.next();
                                Cliente clienteRecebedor = null;

                                //Aqui ele vai procurar o cliente que tem a chave pix digitada pelo usuário
                                for (Cliente cliente : listaClientes) {
                                    if (cliente.chavePix.equals(chavePixDestinatario)) {
                                        clienteRecebedor = cliente;
                                        break;
                                    }
                                }
                                //Bora ver se essa bomba de chave pix existe ou não
                                if (clienteRecebedor == null) {
                                    System.out.println("Chave Pix inválida! Tente novamente.");
                                    break;
                                }
                                //Um detalhe importante (miguel) é que o cliente não pode transferir para ele mesmo (útil viu, visionario não tem jeito)
                                if (clienteRecebedor == clienteLogado) {
                                    System.out.println("Você não pode transferir para você mesmo!");
                                    break;
                                }
                                System.out.println("Digite o valor do Pix:");
                                double valorPix = leia.nextDouble();
                                //Caso o valor seja 0 ou menor que 0:
                                if (valorPix <= 0) {
                                    System.out.println("Valor Inválido!");
                                //Caso o valor seja maior que o saldo do cliente
                                }else if (valorPix > clienteLogado.saldo){
                                    System.out.println("Saldo insuficiente!");
                                //A realização do Pix
                                }else{
                                    realizarTranferenciaPix(clienteLogado, clienteRecebedor, valorPix);
                                    System.out.printf("Transferência de R$ %.2f realizada com sucesso para %s!\n", valorPix, clienteRecebedor.nome);
                                }
                                break;
                            case 2:
                                Cliente clienteRecebedorTed = null;
                                // primeiro precisa perguntar para o usuário qual a conta que ele quer transferir
                                while (true) {
                                    clienteRecebedorTed = null; // adicionado agora pq limpa a variável a cada ciclo (Enzo)
                                    System.out.print("Digite o CPF: ");
                                    String cpfTed = leia.next();

                                    System.out.print("Digite a agência: ");
                                    String agenciaTed = leia.next();

                                    System.out.print("Digite a conta: ");
                                    String contaTed = leia.next();

                                    // Após ele digitar tudo, faça a validação. Se for correto, permita o usuário prosseguir ao pagamento
                                    boolean dadosCorretos = false;
                                    for (Cliente c : listaClientes){ //Para cada Cliente que eu to chamando temporariamente de c dentro de listaClientes, faça isso que eu to mandando
                                        if (c.CPF.equals(cpfTed) && c.agencia.equals(agenciaTed) && c.conta.equals(contaTed)) { //O CPF do cliente e a agência do cliente e a conta do cliente batem com tudo o que o usuário acabou de digitar no terminal? o equal está verificando se tudo bate ;)
                                            clienteRecebedorTed = c; 
                                            dadosCorretos = true;
                                            break;
                                        }
                                    }
                                    // Caso algum dado seja incosistente, retorne ao ponto de partida.
                                        if ((dadosCorretos && clienteRecebedorTed != clienteLogado)) {
                                            System.out.println("Dados válidados com sucesso!");
                                            break;
                                        }
                                        else{
                                            System.out.println("DADOS INCONSISTENTES!");
                                        }
                                }
                                // O programa precisa dessas informações: CPF, conta e agência 
                                System.out.println("Digite o valor do TED: ");
                                double valorTED = leia.nextDouble();
                                //Valor da taxa do TED eu mandei para dentro da função
                                double taxa = 15.67;
                                //Utilizando mesma estrategia do Pix
                                if(valorTED <= 0) {
                                    System.out.println("Valor Inválido");
                                }else if (valorTED + taxa > clienteLogado.saldo) {
                                    System.out.println("Saldo insuficiente");   
                                }else{
                                    realizarTransferenciaTed(clienteLogado, taxa, clienteRecebedorTed, valorTED);
                                    System.out.printf("TED de R$ %.2f realizado com sucesso para %s!\n", valorTED, clienteRecebedorTed.nome);
                                }
                                break;
                        }
                        break;
                }
            }
        }
    }
}