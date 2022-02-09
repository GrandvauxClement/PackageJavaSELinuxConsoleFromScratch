package com.CDAHb.debutClassJavaSE.FileManagement.Services.actions;

import com.CDAHb.debutClassJavaSE.Garage.Services.actions.ActionsConstants;

public enum LinuxConsoleActions {



    NAVIGATE_FILE_SYSTEM("cd"),
    LIST_FILES_AND_FOLDER("ls"),
    CREATE_FOLDER("mkdir"),
    CREATE_FILES("cat"),
    DELETE("rm"),
    READ_FILE_AND_DISPLAY_CONSOLE("read"),
    EXIT("exit");

    private String value;


    LinuxConsoleActions(String value){
        this.value = value;

    }

    public String getValue() {
        return value;
    }

    public static boolean containsAction(String value) {
        LinuxConsoleActions[] actions = LinuxConsoleActions.values();

        for (LinuxConsoleActions action : actions) {
            if (action.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return value ;
    }
}
