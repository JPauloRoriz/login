package controller;

import exception.DadoIncorreto;
import exception.DadosNaoPreenchidos;
import exception.SenhaIgualAnteriorException;
import model.entidades.User;

public class AlterarContaController {

    public static void alterarContaController(User user, String newName, String newPassword) throws Exception {
        if (newPassword.equals(user.getSenha())) {
            throw new SenhaIgualAnteriorException();
        }
        if ((newName.equals("") && newPassword.equals(""))) {
            throw new DadosNaoPreenchidos();
        }
        if ((!newPassword.isEmpty() && newPassword.length() < 3) || (!newName.isEmpty() && newName.length() < 3)) {
            throw new DadoIncorreto();
        }

        if (!newName.isEmpty()) {
            user.setNome(newName);
        }
        if (!newPassword.isEmpty()) {
            user.setSenha(newPassword);
        }
    }
}
