/**
 * Created by Marilia on 22/02/2018.
 */
public interface AcaoVisitor {
    Acao visit(AcaoClicarBotao l);
    Acao visit(AcaoPreencherCampo d);
    Acao visit(AcaoSelecionarItem l);
    Acao visit(AcaoValidarInformacao d);
    public Acao visit(AcaoVazia d);
}
