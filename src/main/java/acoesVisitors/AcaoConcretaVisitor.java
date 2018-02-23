package acoesVisitors;

import acoesBasics.*;

/**
 * Created by Marilia on 22/02/2018.
 */
public class AcaoConcretaVisitor implements AcaoVisitor{

    public Acao visit(AcaoClicarBotao d) {
        return d.getElemento().getPagina().decorarAcao(d);
    }

    public Acao visit(AcaoPreencherCampo d) {
        //d.roar();
       return  d.getElemento().getPagina().decorarAcao(d);
    }

    @Override
    public Acao visit(AcaoSelecionarItem d) {
        return d.getElemento().getPagina().decorarAcao(d);
    }

    @Override
    public Acao visit(AcaoValidarInformacao d) {
        return d.getElemento().getPagina().decorarAcao(d);
    }

    @Override
    public Acao visit(AcaoVazia d) {
        return d;
    }


}
