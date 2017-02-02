package controller;

import command.*;

/**
 * Enum contains actions of the app
 *
 * @author Dmitry V
 * @version 1.0
 */
public enum Action {
    REG("/registration.jsp", "/login.jsp", new CommandReg()),
    LOGIN("/login.jsp", "/index.jsp", new CommandLogin()),
    LOGOUT("/logout.jsp", "/login.jsp", new CommandLogout()),
    ERROR("/error.jsp", "/error.jsp", new CommandError()),
    FRIDGE("/Fridge.jsp", "/Fridge.jsp", new CommandFridge()),
    WASHER("/Washer.jsp", "/Washer.jsp", new CommandWasher()),
    MOBILE("/Mobile.jsp", "/Mobile.jsp", new CommandMobile()),
    TV("/Tv.jsp", "/Tv.jsp", new CommandTv()),
    BASKET("/basket.jsp", "/basket.jsp", new CommandBasket()),
    SENDMAIL("/sendMail.jsp", "/login.jsp", new CommandSendMail()),
    MODIFYUSERS("/users.jsp", "/users.jsp", new CommandModifyUsers()),
    MODIFYGOODS("/goods.jsp", "/goods.jsp", new CommandModifyGoods()),
    MODIFYPROFILE("/profile.jsp", "/profile.jsp", new CommandModifyProfile()),
    DESCRIPTIONTV("/descriptionTv.jsp", "/descriptionTv.jsp",
            new CommandDescriptionTv()),
    DESCRIPTIONFRIDGE("/descriptionFridge.jsp", "/descriptionFridge.jsp",
            new CommandDescriptionFridge()),
    DESCRIPTIONWASHER("/descriptionWasher.jsp", "/descriptionWasher.jsp",
            new CommandDescriptionWasher()),
    DESCRIPTIONMOBILE("/descriptionMobile.jsp", "/descriptionMobile.jsp",
            new CommandDescriptionMobile());

    Action(String inPage, String okPage, ActionCommand command) {
        this.inPage = inPage;
        this.okPage = okPage;
        this.command = command;
    }

    public String inPage = "/error.jsp";
    public String okPage = "/error.jsp";
    public ActionCommand command;

    public ActionCommand getCurrentCommand() {
        return command;
    }

    public static final String msgError = "msg_error";
    public static final String msgMessage = "message";

}