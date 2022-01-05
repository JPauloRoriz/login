package model.db;

import model.entidades.User;

import java.util.ArrayList;
import java.util.List;

public class UserDB {

    private static final List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public static void removerUser(User user) {
        users.remove(user);
    }

    public User getUser(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    public boolean doLogin(String email, String senha) {
        for (User user : users) {
            if (user.getEmail().equals(email) && user.getSenha().equals(senha)) {
                return true;
            }
        }
        return false;
    }

    public static List<String> buscaPorNome(String nome) {
        List <String> emails = new ArrayList<>();
        for (User user : users) {
            if (user.getNome().contains(nome) || nome.contains(user.getNome())) {
                emails.add(user.getEmail());
            }
        }
        return emails;
    }


}
