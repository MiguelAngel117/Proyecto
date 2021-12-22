package views.register;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import controllers.RegisterController;
import controllers.UserController;
import models.Date;
import models.User;
import views.home.JFrameHome;

public class RegisterListener implements ActionListener {

    /**
     *
     */
    private static final int MAX_LENGTH_DOCUMENT = 20;
    private static final String REGISTER_OK = "Registro guardado con éxito";
    private static final String MESSAGE_LIMIT_NAME = "El nombre debe contener entre 1 a 30 caracteres";
    private static final String MESSAGE_LIMIT_LAST_NAME = "El Apellido debe contener entre 1 a 30 caracteres";
    private static final String MESSAGE_LIMIT_DOCUMENT = "El Numero de documento debe contener entre 1 a 20 caracteres";
    private static final String MESSAGE_AGE_LIMIT = "Su edad debe ser mayor a 17 años";
    private static final String MESSAGE_DOCUMENT_FOUND = "El numero de documento ya se encuentra en el registro";

    private JFrameRegister jFrameRegister;
    private JFrameHome jFrameHome;

    private Date date;
    private int year;
    private int month;

    private UserController userController;
    private RegisterController registerController;

    public RegisterListener(JFrameRegister jFrameRegister, UserController userController, JFrameHome jFrameHome) {
        date = new Date();
        date.dateSelected(2000, 0, 1);
        this.jFrameRegister = jFrameRegister;
        this.jFrameHome = jFrameHome;
        this.userController = userController;
        registerController = new RegisterController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ActionsRegister actionsRegister = ActionsRegister.valueOf(e.getActionCommand());
        switch (actionsRegister) {
            case SAVE:
                save();
                break;
            case YEAR:
                year = Integer.parseInt(jFrameRegister.getJComboBoxYear().getSelectedItem() + "");
                month = Integer.parseInt(jFrameRegister.getJComboBoxMonth().getSelectedItem() + "") - 1;
                date.dateSelected(year, month, 1);
                jFrameRegister.addDaysJComboBoxDay(date.getDaysInMonth());
                break;
            case MONTH:
                month = Integer.parseInt(jFrameRegister.getJComboBoxMonth().getSelectedItem() + "") - 1;
                date.dateSelected(year, month, 1);
                jFrameRegister.addDaysJComboBoxDay(date.getDaysInMonth());
                break;
            case DAY:
                int day = Integer.parseInt(jFrameRegister.getJComboBoxDay().getSelectedItem() + "");
                date.dateSelected(year, month, day);
                break;
            default:
                break;
        }
    }

    public void save() {
        try {
            String name = jFrameRegister.getjTextFieldName().getText().toUpperCase();
            userController.checkNotEmpty(name, 30);
            try {
                String lastName = jFrameRegister.getJTextFieldLastName().getText().toUpperCase();
                userController.checkNotEmpty(lastName, 30);
                try {
                    String typeDocument = jFrameRegister.getJComboBoxTypeDocument().getSelectedItem() + "";
                    String documentNumber = jFrameRegister.getJComboBoxNumberDocument().getText().toUpperCase();
                    userController.checkNotEmpty(documentNumber, MAX_LENGTH_DOCUMENT);
                    int day = Integer.parseInt(jFrameRegister.getJComboBoxDay().getSelectedItem() + "");
                    int month = Integer.parseInt(jFrameRegister.getJComboBoxMonth().getSelectedItem() + "");
                    int year = Integer.parseInt(jFrameRegister.getJComboBoxYear().getSelectedItem() + "");
                    try {
                        userController.validateAge(new GregorianCalendar(year, month, day));
                        try {
                            userController.checkIdentifyNumber(typeDocument, documentNumber);
                            addData(new User(name, lastName, typeDocument, documentNumber,
                                    new GregorianCalendar(year, month, day)));
                            registerController.goToHome(jFrameRegister, jFrameHome);
                        } catch (Exception e) {
                            jFrameRegister.showMessageConfirm(MESSAGE_DOCUMENT_FOUND);
                        }
                    } catch (Exception e) {
                        jFrameRegister.showMessageConfirm(MESSAGE_AGE_LIMIT);
                    }
                } catch (Exception e) {
                    jFrameRegister.showMessageConfirm(MESSAGE_LIMIT_DOCUMENT);
                }
            } catch (Exception e) {
                jFrameRegister.showMessageConfirm(MESSAGE_LIMIT_LAST_NAME);
            }
        } catch (Exception e) {
            jFrameRegister.showMessageConfirm(MESSAGE_LIMIT_NAME);
        }
    }

    public void addData(User user) {
        userController.addUser(user);
        userController.saveData();
        userController.readData();
        jFrameRegister.showMessageConfirm(REGISTER_OK);
    }

    public Date getInstanceDate() {
        return date;
    }
}
