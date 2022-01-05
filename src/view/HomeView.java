package view;

import controller.BuscarUserController;
import exception.DadosNaoPreenchidos;
import exception.ZeroUsuariosEncontrados;
import model.db.UserDB;
import model.entidades.User;
import utils.JOptionUtils;

import java.util.List;
import java.util.Locale;

public class HomeView {

    public static final int ALTERAR_CONTA = 1;
    public static final int DELETAR_CONTA = 2;
    public static final int BUSCAR_USUARIO = 3;
    public static final int SAIR = 4;

    public static void homeView(User user) {
        while (true) {
            int option = JOptionUtils.dialogInt("Olá,  " + user.getNome().toUpperCase(Locale.ROOT) + ".  Escolha uma opção; \n" +
                    ALTERAR_CONTA + " - Alterar conta\n" +
                    DELETAR_CONTA + " - Deletar conta\n" +
                    BUSCAR_USUARIO + " - Buscar usuário\n" +
                    SAIR + " - Sair");

            switch (option) {
                case ALTERAR_CONTA:
                    AlterarContaView.alterarContaStart(user);
                    break;

                case DELETAR_CONTA:
                    UserDB.removerUser(user);
                    JOptionUtils.message("Sua conta foi deletada com sucesso!");
                    voltarMenuAnterior();

                    break;
                case BUSCAR_USUARIO:
                    String emailDeBusca = JOptionUtils.input("Informe o nome do usuario que deseja buscar");
                    try {
                        String mensagemRetorno = "";
                        List<String> users = BuscarUserController.buscarUserController(emailDeBusca);
                        mensagemRetorno += ("Usuarios encontrados:  " +  users.size() + "\n");
                        mensagemRetorno += getNamesUsers(users);
                        JOptionUtils.message(mensagemRetorno);
                    } catch (DadosNaoPreenchidos dadosNaoPreenchidos) {
                        dadosNaoPreenchidos.printStackTrace();
                        JOptionUtils.message("Dados não preenchidos");
                    } catch (ZeroUsuariosEncontrados zeroUsuariosEncontrados) {
                        JOptionUtils.message("Nenhum usuario encontrado");
                    }
                    break;

                case SAIR:
                    voltarMenuAnterior();
                    break;

                default:
                    JOptionUtils.message("Opção inválida");
                    break;
            }
        }
    }

    private static String getNamesUsers(List<String> users) {
        int i = 1;
        String messageReturn = "";
        for (String email : users) {
            messageReturn += i + " - " + email + " \n";
            i++;
        }
        return messageReturn;
    }

    public static void voltarMenuAnterior() {
        LoginView.loginView();
    }
}
