/**
 * Created by Marilia on 07/02/2018.
 */
public class AcaoClicarBotao extends Acao {

    @Override
    protected void executarOperacaoPrincipal() {
        System.out.print("\nAcaoClicarBotao > ");
        //super.executar();
    }

    @Override
    public Acao accept(AcaoVisitor av) {
        return av.visit(this);
    }
}
