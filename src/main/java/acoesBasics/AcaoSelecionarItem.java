package acoesBasics;

import acoesVisitors.AcaoVisitor;

/**
 * Created by Marilia on 25/01/2018.
 */
public class AcaoSelecionarItem extends Acao {

    @Override
    public void executarOperacaoPrincipal() {
        System.out.print("\nAcaoSelecionarItem > ");
        //super.executar();
    }

    @Override
    public Acao accept(AcaoVisitor av) {
        return av.visit(this);
    }
}
