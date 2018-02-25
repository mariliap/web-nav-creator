package acoesVisitors;

import acoesBasics.*;

/**
 * Created by Marilia on 22/02/2018.
 */
public interface AcoesVisitor {
    Acao visit(AcaoClicarBotao acaoOriginal);
    Acao visit(AcaoPreencherCampo acaoOriginal);
    Acao visit(AcaoSelecionarItem acaoOriginal);
    Acao visit(AcaoValidarInformacao acaoOriginal);
    Acao visit(AcaoVazia acaoOriginal);
}
