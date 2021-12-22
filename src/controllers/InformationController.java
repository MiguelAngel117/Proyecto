package controllers;

import views.home.JFrameHome;
import views.information.JFrameInformation;

public class InformationController {
    public InformationController() {

    }

    public void goToHome(JFrameInformation jFrameInformation, JFrameHome jFrameHome) {
        jFrameHome.reload();
        jFrameInformation.setVisible(false);
    }
}
