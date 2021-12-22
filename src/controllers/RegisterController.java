package controllers;

import views.home.JFrameHome;
import views.register.JFrameRegister;

public class RegisterController {

    public RegisterController() {

    }

    public void goToHome(JFrameRegister jFrameRegister, JFrameHome jFrameHome) {
        jFrameRegister.setVisible(false);
        jFrameHome.reload();
    }
}