package rest;

import acoesBasics.Acao;
import acoesBasics.AcaoVazia;
import commons.PersistenceManager;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import java.io.Serializable;
import java.util.List;

@Path("/execucao")
public class ExecucaoResource implements Serializable{

    private EntityManager entityManager = PersistenceManager.INSTANCE.getEntityManager();

    @GET
    @Produces({"application/json"})
    public List<Acao> getAll() {
        //http://localhost:12345/rest/execucao
        System.out.println("geeet " +entityManager);
        List<Acao> listaAcoes = entityManager.createQuery("SELECT a FROM Acao a", Acao.class).getResultList();
        return listaAcoes;
    }


    @POST
    @Consumes({"application/json"})
    public Boolean executar(
            @NotNull
            List<Long> acaoIds){

        //Ao criar um request para este método, não esquecer de setar Content-Type : application/json no HEADER
        //Outras configurações
        //Host/port = http://localhost:12345
        //Path = /rest/execucao
        //Request Body (text) = [1,2,3,4,5,6,7,8]

        List<Acao> listaAcoes = entityManager.createQuery("SELECT a FROM Acao a where a.id IN :acaoIds", Acao.class). setParameter("acaoIds", acaoIds).getResultList();

        Acao teste = new AcaoVazia();
        for (Acao acao : listaAcoes) {
            teste.adicionar(acao);
        }
        teste.executar();

        return true;
    }


}
