package org.imagimatica.model;

import com.easyraffle.process.AdminClientSorteos;
import com.easyraffle.process.CustomerLoader;
import com.easyraffle.process.GeneradorSorteos;
import java.util.Date;
import java.util.List;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService()
public class ServiceCoreWS {

    GeneradorSorteos gs = new GeneradorSorteos();
    AdminClientSorteos acs = new AdminClientSorteos();
    CustomerLoader cl = new CustomerLoader();

    @WebMethod(operationName = "process")
    @Oneway
    public void process(
            @WebParam(name = "idSala") int idSala,
            @WebParam(name = "idGrupo") int idGrupo,
            @WebParam(name = "config") Config config) {
        gs.process(idSala, idGrupo, config);
    }

    @WebMethod(operationName = "getRaffles")
    public List<Sorteos> getRaffles(
            @WebParam(name = "idSala") int idSala,
            @WebParam(name = "idGrupo") int idGrupo) {
        List<Sorteos> raffles = null;
        raffles = gs.getRaffles(idSala, idGrupo);
        return raffles;
    }

    @WebMethod(operationName = "getUser")
    public Usuario getUser(
            @WebParam(name = "login") String login,
            @WebParam(name = "password") String password) {
        Usuario usr = null;
        usr = gs.getUser(login, password);
        return usr;
    }

    @WebMethod(operationName = "getConfig")
    public Config getConfig(
            @WebParam(name = "idSala") int idSala) {
        Config c = null;
        c = gs.getConfig(idSala);
        return c;
    }

    @WebMethod(operationName = "updateRaffle")
    @Oneway
    public void updateRaffle(
            @WebParam(name = "s") Sorteos s,
            @WebParam(name = "config") Config config) {
        gs.updateRaffle(s, config);
    }

    @WebMethod(operationName = "updateRaffle2")
    @Oneway
    public void updateRaffle2(
            @WebParam(name = "s") Sorteos s,
            @WebParam(name = "config") Config config,
            @WebParam(name = "j") List<Jugadores> j,
            @WebParam(name = "idSala") int idSala,
            @WebParam(name = "idGrupo") int idGrupo) {
        gs.updateRaffle2(s, config, j, idSala, idGrupo);
    }
    
    @WebMethod(operationName = "updateRaffle3")
    @Oneway
    public void updateRaffle3(
            @WebParam(name = "s") Sorteos s,
            @WebParam(name = "config") Config config,
            @WebParam(name = "j") List<Jugadores> j,
            @WebParam(name = "idSala") int idSala,
            @WebParam(name = "idGrupo") int idGrupo) {
        gs.updateRaffle3(s, config, j, idSala, idGrupo);
    }

    @WebMethod(operationName = "updatePlayersMachine")
    @Oneway
    public void updatePlayersMachine(
            @WebParam(name = "j") List<Jugadores> j,
            @WebParam(name = "idSala") int idSala,
            @WebParam(name = "idGrupo") int idGrupo) {
        gs.updatePlayers(j, idSala, idGrupo);
    }

    @WebMethod(operationName = "getDataToUpdateGUI")
    public String getDataToUpdateGUI(
            @WebParam(name = "s") Sorteos s) {
        List<Jugadores> participantes = null;
        String countString = null;
        participantes = gs.getClientsInRaffleToShow(s);
        countString = gs.getStringToShow(participantes, s);
        return countString;
    }

    @WebMethod(operationName = "getMonthlyWinners")
    public String getMonthlyWinners(
            @WebParam(name = "month") int month,
            @WebParam(name = "year") int year,
            @WebParam(name = "idSala") int idSala,
            @WebParam(name = "idGrupo") int idGrupo) {
        String winnersanddates = null;
        winnersanddates = gs.getMonthlyWinners(month, year, idSala, idGrupo);
        return winnersanddates;
    }

    @WebMethod(operationName = "getWinnerInRaffle")
    public Jugadores getWinnerInRaffle(
            @WebParam(name = "sorteo") Sorteos sorteo) {
        Jugadores winner = null;
        winner = gs.getWinnerInRaffle(sorteo);
        if (winner != null) {
            return winner;
        } else {
            return new Jugadores();
        }
    }

    @WebMethod(operationName = "insertBitacora")
    public int insertBitacora(
            @WebParam(name = "bitacora") Bitacora bitacora) {
        int counter = 0;
        counter = gs.insertAccion(bitacora);
        return counter;
    }
    
    /*
    @WebMethod(operationName = "getRafflesWinnersByDates")
    public List<Sorteos> getWinnersByDates(
            @WebParam(name = "idSala") int idSala,
            @WebParam(name = "fi") Date fi,
            @WebParam(name = "ff") Date ff) {
        List<Sorteos> WinnersByDates = null;
        WinnersByDates = acs.getRafflesWinnersByDates(idSala, fi, ff);
        return WinnersByDates;
    }
    
    @WebMethod(operationName = "getRafflesWinnersMonth")
    public List<Sorteos> getWinnersMonth(
            @WebParam(name = "idSala") int idSala) 
    {
        List<Sorteos> WinnersMonth = null;
        WinnersMonth = acs.getWinnersMonth(idSala);
        return WinnersMonth;
    }*/
}
