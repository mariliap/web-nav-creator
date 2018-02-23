/**
 * Created by Marilia on 07/02/2018.
 */
public class AcaoPreencherCampo extends Acao{

    @Override
    protected void executarOperacaoPrincipal() {
        System.out.print("\nAcaoPreencherCampo (");
        System.out.print("Verifica existencia do campo, ");
        System.out.print("Verifica visibilidade do campo, ");
        System.out.print("Send keys para o campo, ");
        System.out.print("Remove focus do elemento, ");
        System.out.print("Espera tela de processando da página atual sumir");//opcional
        System.out.print(") >");
        //super.executar();
    }

    @Override
    public Acao accept(AcaoVisitor av) {
        return av.visit(this);
    }
}
