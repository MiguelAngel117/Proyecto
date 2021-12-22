package views.information;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import controllers.InformationController;
import controllers.UserController;
import models.Date;
import models.User;
import views.home.JFrameHome;

public class InformationListener implements ActionListener {

    private static final String MESSAGE_DELETE = "Registro eliminado con éxito";
    private static final String MESSAGE_REGISTER = "Registro actualizado con éxito";
    private static final String MESSAGE_LIMIT_NAME = "El nombre debe contener entre 1 a 30 caracteres";
    private static final String MESSAGE_LIMIT_LAST_NAME = "El Apellido debe contener entre 1 a 30 caracteres";
    private static final String MESSAGE_LIMIT_DOCUMENT = "El Numero de documento debe contener entre 1 a 20 caracteres";
    private static final String MESSAGE_AGE_LIMIT = "Su edad debe ser mayor a 17 años";
    private static final String MESSAGE_DOCUMENT_FOUND = "El numero de documento ya se encuentra en el registro";
    private JFrameInformation jFrameInformation;
    private JFrameHome jFrameHome;
    private InformationController informationController;
    private UserController userController;
    private Date date;
    private int month;
    private int year;
    private User user;

    public InformationListener(JFrameInformation jFrameInformation, UserController userController,
            JFrameHome jFrameHome) {
        date = new Date();
        date.dateSelected(2000, 0, 1);
        this.jFrameInformation = jFrameInformation;
        this.jFrameHome = jFrameHome;
        this.userController = userController;
        informationController = new InformationController();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ActionsInformation actionsInformation = ActionsInformation.valueOf(e.getActionCommand());
        switch (actionsInformation) {
            case UPDATE:
                update();
                break;
            case DELETE:
                delete();
                break;
            case YEAR:
                year = Integer.parseInt(jFrameInformation.getJComboBoxYear().getSelectedItem() + "");
                date.dateSelected(year, 0, 1);
                jFrameInformation.addDaysJComboBoxDay(date.getDaysInMonth(),
                        jFrameInformation.getJComboBoxDay().getSelectedItem() + "");
                break;
            case MONTH:
                month = Integer.parseInt(jFrameInformation.getJComboBoxMonth().getSelectedItem() + "") - 1;
                date.dateSelected(year, month, 1);
                jFrameInformation.addDaysJComboBoxDay(date.getDaysInMonth(),
                        jFrameInformation.getJComboBoxDay().getSelectedItem() + "");
                break;
            case DAY:
                int day = Integer.parseInt(jFrameInformation.getJComboBoxDay().getSelectedItem() + "");
                date.dateSelected(year, month, day);
                break;
            case CLOSE:
                informationController.goToHome(jFrameInformation, jFrameHome);
                break;
            default:
                break;
        }
    }

    public void update() {
        this.user = jFrameInformation.getUser();
        try {
            String name = jFrameInformation.getjTextFieldName().getText().toUpperCase();
            userController.checkNotEmpty(name, 30);
            try {
                String lastName = jFrameInformation.getjTextFieldSecondName().getText().toUpperCase();
                userController.checkNotEmpty(lastName, 30);
                try {
                    String typeDocument = jFrameInformation.getjComboBoxTypeDocument().getSelectedItem() + "";
                    String documentNumber = jFrameInformation.getjTextFieldNumberDocument().getText().toUpperCase();
                    userController.checkNotEmpty(documentNumber, 20);
                    int year = Integer.parseInt(jFrameInformation.getJComboBoxYear().getSelectedItem() + "");
                    int month = Integer.parseInt(jFrameInformation.getJComboBoxMonth().getSelectedItem() + "");
                    int day = Integer.parseInt(jFrameInformation.getJComboBoxDay().getSelectedItem() + "");
                    try {
                        userController.validateAge(new GregorianCalendar(year, month, day));
                        try {
                            userController.checkIdentifyNumberUpdate(typeDocument, documentNumber,
                                    user.getDocumentNumber());
                            setData(name, lastName, typeDocument, documentNumber,
                                    new GregorianCalendar(year, month, day));
                        } catch (Exception e) {
                            jFrameInformation
                                    .showMessageError(MESSAGE_DOCUMENT_FOUND);
                        }
                    } catch (Exception e) {
                        jFrameInformation.showMessageError(MESSAGE_AGE_LIMIT);
                    }
                } catch (Exception e) {
                    jFrameInformation
                            .showMessageError(MESSAGE_LIMIT_DOCUMENT);
                }
            } catch (Exception e) {
                jFrameInformation.showMessageError(MESSAGE_LIMIT_LAST_NAME);
            }
        } catch (Exception e) {
            jFrameInformation.showMessageError(MESSAGE_LIMIT_NAME);
        }
    }

    public void setData(String name, String lastName, String typeDocument, String documentNumber,
            GregorianCalendar date) {
        user.setNames(name);
        user.setLastName(lastName);
        user.setTypeDocument(typeDocument);
        user.setDocumentNumber(documentNumber);
        user.setDate(date);
        jFrameInformation.getjTextFieldAge().setText(userController.getAge(user.getDate()) + "");
        userController.saveData();
        userController.readData();
        jFrameInformation.showMessageConfirm(MESSAGE_REGISTER);
    }

    public void delete() {
        userController.delete(jFrameInformation.getUser());
        userController.saveData();
        userController.readData();
        jFrameInformation.assignedEmpty();
        jFrameInformation.showMessageConfirm(MESSAGE_DELETE);
    }

    public Date getInstanceDate() {
        return date;
    }
}
