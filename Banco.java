import java.util.List;
import java.util.Scanner;
public class Banco{
    public class Extrato{
        int id;
        String tipoTrasacao;
        float valorMovimentacao;
    }

    public static class Cliente{
        int id;
        String nome;
        String conta;
        String CPF;
        String agencia;
        Double saldo;
        String senha;
        String chavePix;
    }

    //Utilizando para criar a leitura do Scanner
    static Scanner leia = new Scanner(System.in);


    static double consultarSaldo(Cliente cliente){
        return cliente.saldo;
    }

    // Função de Realizar Depósito abaixo playbas
    static void realizarDeposito(Cliente cliente){
        System.out.print("Digite o valor que deseja depositar:");
        double valor = leia.nextDouble();
        if( valor<=0){
            System.out.print("Depósito Inválido!");
        }
        else{
            cliente.saldo += valor;
            System.out.printf("Depósito de R$.2f realizado com sucesso!", valor);
        }
    }

    //Fução do PIX - Versão beta 1.0v (CASO MODIFICADO, ALTERAR A VERSÃO BETA PARA 1.1v!)
    static void realizarSaquePix(Cliente cliente) {
        System.out.println("Digite o valor do Pix:");
        double valor = leia.nextDouble();
        //Caso o valor seja 0 ou menor que 0:
        if (valor <= 0) {
            System.out.println("Valor Inválido!");
        //Caso o valor seja maior que o saldo do cliente
        }else if (valor > cliente.saldo){
            System.out.println("Saldo insuficiente!");
        //A realização do Pix
        }else{
            cliente.saldo -= valor;
            System.out.println("Pix de R$ " + valor + " realizado!");  
        }
    }

    //Fução do TED - Versão beta 1.0v (CASO MODIFICADO, ALTERAR A VERSÃO BETA PARA 1.1v!)
    static void realizarSaqueTed(Cliente cliente) {
        System.out.println("Digite o valor do TED: ");
        double valor = leia.nextDouble();
        //Valor da taxa do TED
        double taxa = 15.67;

        //Utilizando mesma estrategia do Pix
        if(valor < 0) {
            System.out.println("Valor Inválido");
        }else if (valor + taxa > cliente.saldo) {
            System.out.println("Saldo insuficiente");
        }else{
            //Aqui e quando o valor e a taxa se somam e logo é enviado para o cliente
            cliente.saldo -= (valor + taxa);
            System.out.println("TED de R$: " + valor + " Realido! Taxa: R$ " + taxa);
        }
    }

    public static void main(String[] args){
        
        Cliente[] listaClientes = new Cliente[2]; // começar a criar os dois exemplos de cliente
        Cliente cliente1 = new Cliente();
        cliente1.id = 1;
        cliente1.nome = "Roberto";
        cliente1.conta = "123";
        cliente1.CPF = "12345678900";
        cliente1.agencia = "456";
        cliente1.saldo = 1000.0;
        cliente1.senha = "senha-secreta";

        Cliente cliente2 = new Cliente();
        cliente2.id = 2;
        cliente2.nome = "Cláudio";
        cliente2.conta = "678";
        cliente2.CPF = "23412345699";
        cliente2.agencia = "789";
        cliente2.saldo = 1000.0;
        cliente2.senha = "senha-mais-secreta";

        listaClientes[0]  = cliente1; // atribuindo ao array de lista clientes
        listaClientes[1]  = cliente2; // atribuindo ao array de lista clientes
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
                        realizarDeposito(clienteLogado);
                        break;
                    case 3:
                        
                        // agora aqui é a função de realizar saque
                        break;
                    case 4:
                        // aqui é a função de exibir o extrato
                        break;
                    case 5:
                        // aqui vai mostrar o maior depósito já realizado
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
                                realizarSaquePix(clienteLogado);
                                break;
                            case 2:
                                realizarSaqueTed(clienteLogado);
                                break;
                        }
                        break;
                }
            }
        }
    }
}