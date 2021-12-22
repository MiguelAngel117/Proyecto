import controllers.*;
import views.home.*;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        JFrameHome jFramehome = new JFrameHome(userController);
        jFramehome.setVisible(true);
    }
}
