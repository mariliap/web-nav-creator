package main.java;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marilia on 07/02/2018.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

//        List<AcaoD> listaAcoes = new ArrayList<>();
//        AcaoD acaoD = new AcaoDVazia();
//        listaAcoes.adicionar(acaoD);

//        acaoD = new AcaoPreencherCampoDecorator(acaoD);
//        acaoD.executar();
//
//        acaoD = new ChainResponsability.AcaoEsperaVisibilidadeDecorator(acaoD);
//        acaoD.executar();
//
//        acaoD = new AcaoValidarInformacaoDecorator(acaoD);
//        acaoD.executar();

//        acaoD = acaoD.compor(new AcaoPreencherCampoDecorator());
//
//        acaoD = acaoD.compor(new ChainResponsability.AcaoEsperaVisibilidadeDecorator());
//
//        acaoD = acaoD.compor(new AcaoValidarInformacaoDecorator());
//
//        acaoD.executar();


//        Acao acaoX = new AcaoPreencherCampo();
//        acaoX.adicionar(new AcaoClicarBotao());
//        acaoX.adicionar(new AcaoSelecionarItem());
//        acaoX.adicionar(new AcaoValidarInformacao());
//        acaoX.executar();


        List<Acao> listaAcoes = new ArrayList<>();
        Acao acao1 = new AcaoPreencherCampo();
        Acao acao2 = new AcaoPreencherCampo();
        Acao acao3 = new AcaoPreencherCampo();
        Acao acao4 = new AcaoClicarBotao();
        Acao acao5 = new AcaoSelecionarItem();
        Acao acao6 = new AcaoValidarInformacao();
        Acao acao7 = new AcaoValidarInformacao();
        Acao acao8 = new AcaoValidarInformacao();
        listaAcoes.add(acao1);
        listaAcoes.add(acao2);
        listaAcoes.add(acao3);
        listaAcoes.add(acao4);
        listaAcoes.add(acao5);
        listaAcoes.add(acao6);
        listaAcoes.add(acao7);
        listaAcoes.add(acao8);

        Pagina pagAtual = null;
        Acao teste = new AcaoVazia();
        for (Acao acao : listaAcoes) {
            if(pagAtual == null){
                pagAtual = acao.getElemento().getPagina();
            } else if(!pagAtual.equals(acao.getElemento().getPagina())){
                acao = new AcaoEsperaVisibilidadeDecorator(acao);
            }
            teste.adicionar(acao);
        }
        teste.executar();

    }
}
