/**
 * Created by Marilia on 07/02/2018.
 */
public abstract class Pagina {

    AcaoVisitor visitor = new AcaoConcretaVisitor();

    public Acao decorY(Acao original){
        //return decorarAcao(original);
        return original.accept(visitor);
    }

//    public <T extends Acao> Acao decorX(T original){
//        return decorarAcaoX(original);
//    }



    public abstract Acao getAcaoEspera();

    public abstract Acao decorarAcao(AcaoClicarBotao original);

    public abstract Acao decorarAcao(AcaoPreencherCampo original);

    public abstract Acao decorarAcao(AcaoValidarInformacao original);

    public abstract Acao decorarAcao(AcaoSelecionarItem original);

    //public abstract <? super AcaoClicarBotao> Acao decorarAcaoX(? original);

    //public abstract <T extends AcaoPreencherCampo> AcaoPreencherCampo decorarAcaoX(T original);
}
