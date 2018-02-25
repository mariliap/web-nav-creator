package acoesVisitors;

import acoesBasics.*;
import acoesDecorators.AcaoAnteriorDecorator;
import acoesDecorators.AcaoEsperaVisibilidadeDecorator;
import acoesDecorators.AcaoPosteriorDecorator;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Marilia on 22/02/2018.
 */
@Entity
@DiscriminatorValue("TestingDecoratorsAcoesVisitor")
public class TestingDecoratorsAcoesVisitor extends PaginaAcoesVisitor {

    public Acao visit(AcaoClicarBotao acaoOriginal) {
        return new AcaoEsperaVisibilidadeDecorator(acaoOriginal);
    }

    public Acao visit(AcaoPreencherCampo acaoOriginal) {
        return new AcaoAnteriorDecorator(new AcaoPosteriorDecorator(acaoOriginal));
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
