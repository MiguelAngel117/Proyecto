package views.home;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controllers.HomeController;
import models.User;

public class HomeListener implements ActionListener, ListSelectionListener {
    /**
     *
     */
    private static final String NOT_FOUND = "No se encontro al usuario";
    private JFrameHome jFrameHome;
    private HomeController homeController;

    public HomeListener(JFrameHome jFrameHome) {
        this.jFrameHome = jFrameHome;
        homeController = new HomeController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ActionsHome actionsHome = ActionsHome.valueOf(e.getActionCommand());
        switch (actionsHome) {
            case REGISTER:
                goToRegister();
                break;
            case SEARCH:
                searchUser();
                break;
            default:
                break;
        }
    }

    public void searchUser() {
        try {
            String name = jFrameHome.getjTextFieldSearch().getText().toUpperCase();
            if (name.isEmpty())
                jFrameHome.reload();
            else
                jFrameHome.reload(jFrameHome.getUserController().obtainUser(name, name));
        } catch (Exception e) {
            jFrameHome.showUserFind(NOT_FOUND, "Usuario");
        }
    }

    public void goToRegister() {
        homeController.goToRegister(jFrameHome.getJFrameRegister());
    }

    public void goToInformation() {
        if (((User) jFrameHome.getjList().getSelectedValue()) != null) {
            homeController.goToInformation(
                    jFrameHome.getJFrameInformation(),
                    (User) jFrameHome.getjList().getSelectedValue());
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getSource() == jFrameHome.getjList()) {
            goToInformation();
        }
    }
}
