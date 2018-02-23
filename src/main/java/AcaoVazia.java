/**
 * Created by Marilia on 25/01/2018.
 */
public class AcaoVazia extends Acao {

    @Override
    protected void executarOperacaoPrincipal() {
        System.out.print("AcaoVazia > ");
        //super.executar();
    }

    @Override
    public Acao accept(AcaoVisitor av) {
        return av.visit(this);
    }
}
