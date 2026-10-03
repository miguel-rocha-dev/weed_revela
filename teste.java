
import java.util.ArrayList;

public class teste {

    public static class Extrato{
        int id;
        Double valor;
    }

    static void main(String args[]){
        ArrayList<Extrato> testeExtrato = new ArrayList<Extrato>();
        Extrato teste = new Extrato();
        teste.id = 1;
        teste.valor = 1.1;
        testeExtrato.add(teste);
        System.out.println(testeExtrato);

    }
}
