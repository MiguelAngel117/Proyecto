package controllers;

import models.User;
import views.information.JFrameInformation;
import views.register.JFrameRegister;

public class HomeController {

    public HomeController() {
    }

    public void goToRegister(JFrameRegister jFrameRegister) {
        jFrameRegister.assignedEmpty();
        jFrameRegister.setVisible(true);
    }

    public void goToInformation(JFrameInformation jFrameInformation, User user) {
        jFrameInformation.setVisible(true);
        jFrameInformation.loadData(user);
    }
}
