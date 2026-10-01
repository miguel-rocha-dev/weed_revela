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
    }

    //Fução do PIX - Versão beta 1.0v (CASO MODIFICADO, ALTERAR A VERSÃO BETA PARA 1.1v!)

    //Utilizando para criar a leitura do Scanner
    static Scanner leia = new Scanner(System.in);

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
                System.out.println("0 - Sair");
                System.out.println("Escolha uma opção:");
                int opcao = leia.nextInt();
                switch(opcao){ // depois trocar isso aqui por um RULE SWITCH
                    case 0:
                        System.out.println("até a próxima vez! \n");
                        break saidaMenu;

                    case 1:
                        //aqui vai chamar a função de consultar saldo
                        break;
                    case 2:
                        //aqui vai chamar a função de realizar depósito
                        break;
                    case 3:
                        System.out.println("1 - Realizar por PIX");
                        System.out.println("2 - Transferecia por TED");
                        System.out.println("Escolha uma opção:");
                        int menusaque = leia.nextInt();
                        switch (menusaque) {
                            case 1:
                                realizarSaquePix(clienteLogado);
                                break;
                            case 2:
                                realizarSaqueTed(clienteLogado);
                                break;
                            default:
                                System.out.println("Opção invalida ");
                                break;
                        }
                        // agora aqui é a função de realizar saque
                        break;
                    case 4:
                        // aqui é a função de exibir o extrato
                        break;
                    case 5:
                        // aqui vai mostrar o maior depósito já realizado
                        break;
                    default:
                        System.out.println("Entrada inválida!"); // depois arruma para ele fazer um loop infinito de perguntar a opção
                }
            }
        }
    }
}