package acoesVisitors;

import acoesBasics.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Marilia on 22/02/2018.
 */
@Entity
@DiscriminatorValue("PaginaGenenericaAcoesVisitor")
public class PaginaGenenericaAcoesVisitor extends PaginaAcoesVisitor {

    public Acao visit(AcaoClicarBotao acaoOriginal) {
        return acaoOriginal;
        //return d.getElemento().getPagina().decorarAcao(d);
    }

    public Acao visit(AcaoPreencherCampo acaoOriginal) {
        return acaoOriginal;
//       return  d.getElemento().getPagina().decorarAcao(d);
    }

    public Acao visit(AcaoSelecionarItem acaoOriginal) {
        return acaoOriginal;
//        return d.getElemento().getPagina().decorarAcao(d);
    }

    public Acao visit(AcaoValidarInformacao acaoOriginal) {
        return acaoOriginal;
//        return d.getElemento().getPagina().decorarAcao(d);
    }

    public Acao visit(AcaoVazia acaoOriginal) {
        return acaoOriginal;
    }


}
