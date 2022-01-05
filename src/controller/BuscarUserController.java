package controller;

import exception.DadosNaoPreenchidos;
import exception.ZeroUsuariosEncontrados;
import model.db.UserDB;


import java.util.List;

public class BuscarUserController {

    public static List<String> buscarUserController(String nome) throws DadosNaoPreenchidos, ZeroUsuariosEncontrados {
        if (nome.isEmpty()) {
            throw new DadosNaoPreenchidos();
        }
        List<String> emails = UserDB.buscaPorNome(nome);
        if (emails.isEmpty()) {
            throw new ZeroUsuariosEncontrados();
        } else {
            return emails;
        }

    }
}
