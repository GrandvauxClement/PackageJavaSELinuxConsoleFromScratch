package com.CDAHb.debutClassJavaSE.FileManagement.Services.actions;

import com.CDAHb.debutClassJavaSE.Garage.Services.actions.ActionsConstants;
import com.CDAHb.debutClassJavaSE.Garage.Services.actions.ServiceAction;

public enum LinuxConsoleWithMenuActions implements ServiceAction {

    NAVIGATE_FILE_SYSTEM("1", "Navigate in file system"),
    LIST_FILES_AND_FOLDER("2", "List files and folder"),
    CREATE_FOLDER("3", "Create one Folder"),
    CREATE_FILES("4", "Create one File"),
    DELETE_FILES("5", "Delete one file"),
    DELETE_FOLDER("6", "Delete one Folder"),
    EXIT(ActionsConstants.EXIT_ID, ActionsConstants.EXIT_ACTION);

    private String value;

    private String title;

    LinuxConsoleWithMenuActions(String value, String title){
        this.value = value;

        this.title = title;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public static boolean containsAction(String value) {
        LinuxConsoleWithMenuActions[] actions = LinuxConsoleWithMenuActions.values();

        for (LinuxConsoleWithMenuActions action : actions) {
            if (action.getValue().equalsIgnoreCase(value)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String toString() {
        return value + " - " + title;
    }
}
