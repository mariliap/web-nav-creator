package acoesVisitors;

import acoesBasics.*;
import acoesDecorators.AcaoEsperaVisibilidadeElementoPaginaSeguinteDecorator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Marilia on 03/03/2018.
 */
@Entity
@DiscriminatorValue("ZimbraLoginAcoesVisitor")
public class ZimbraLoginAcoesVisitor extends PaginaAcoesVisitor {

    public Acao visit(AcaoClicarBotao acaoOriginal) {
        return new AcaoEsperaVisibilidadeElementoPaginaSeguinteDecorator(acaoOriginal);
    }

    public Acao visit(AcaoPreencherCampo acaoOriginal) {
        return acaoOriginal;
    }

    public Acao visit(AcaoSelecionarItem acaoOriginal) {
        return acaoOriginal;
    }

    public Acao visit(AcaoValidarInformacao acaoOriginal) {
        return acaoOriginal;
    }

    public Acao visit(AcaoVazia acaoOriginal) {
        return acaoOriginal;
    }


}
