package com.CDAHb.debutClassJavaSE.FileManagement.Services;

import com.CDAHb.debutClassJavaSE.FileManagement.Services.actions.LinuxConsoleWithMenuActions;
import com.CDAHb.debutClassJavaSE.Garage.Services.actions.ServiceAction;
import com.CDAHb.debutClassJavaSE.Garage.utils.ConsoleManager;
import com.CDAHb.debutClassJavaSE.Garage.Services.actions.ActionsConstants;

import java.io.File;
import java.util.Arrays;

public class LinuxConsoleWithMenuService {
    private File file ;

    public LinuxConsoleWithMenuService(){
        this.file = new File("D:\\");
    }

    public void run() {
        printTitle("Welcome to the linux console from scratch");

        String answer;

        do {
            answer = printMenu(LinuxConsoleWithMenuActions.values());
            ConsoleManager.getInstance().consoleLineBreak();

            // handle the user action
            handleAction(answer);
        } while (!answer.equalsIgnoreCase(ActionsConstants.EXIT_ID)); // loop while until user wants to exit

        // TODO sauvegarde
    }

    private void handleAction(String answer) {

        if (answer.equalsIgnoreCase(LinuxConsoleWithMenuActions.NAVIGATE_FILE_SYSTEM.getValue())) {
            navigateFileSystem();
        }

        if (answer.equalsIgnoreCase(LinuxConsoleWithMenuActions.LIST_FILES_AND_FOLDER.getValue())) {
            listFileAndFolder();
        }

        if (answer.equalsIgnoreCase(LinuxConsoleWithMenuActions.CREATE_FOLDER.getValue())) {

        }

        if (answer.equalsIgnoreCase(LinuxConsoleWithMenuActions.CREATE_FILES.getValue())) {

        }

        if (answer.equalsIgnoreCase(LinuxConsoleWithMenuActions.DELETE_FILES.getValue())) {

        }

        if (answer.equalsIgnoreCase(LinuxConsoleWithMenuActions.DELETE_FOLDER.getValue())) {

        }
    }

    private void navigateFileSystem(){
        boolean navigateDone = false;
        do{
            displayActualAbsolutPath();
            listFileAndFolder();
            ConsoleManager.getInstance().printToConsole("Saisissez le nom du dossier à accéder ou saisir .. pour revenir au dossier parents");
            String folder = ConsoleManager.getInstance().readUserInput();
            if (folder.equalsIgnoreCase("..")){
                if (this.file.getParent() != null){

                    this.file = this.file.getParentFile();
                    navigateDone = true;
                }else{
                    ConsoleManager.getInstance().printToConsoleError("Impossible ce dossier ne possède pas de parents !!", true);
                }

            }else {
                File testDirectory = new File(this.file.getAbsolutePath() +"\\"+ folder);
                if ( testDirectory.isDirectory()){
                    this.file = new File(this.file.getAbsolutePath() +"\\"+ folder);
                    navigateDone = true;
                } else {
                    ConsoleManager.getInstance().printToConsoleError("Ce dossier n'existe pas !!", true);
                }
            }
        } while (!navigateDone);

        displayActualAbsolutPath();
        ConsoleManager.getInstance().printLine();
    }

    private void displayActualAbsolutPath(){
        ConsoleManager.getInstance().printToConsole("Nous sommes actuellement : ", false);
        ConsoleManager.getInstance().printToConsole(this.file.getAbsolutePath());
        ConsoleManager.getInstance().consoleLineBreak();
    }

    private void listFileAndFolder(){
        String [] fileNames = this.file.list();
        ConsoleManager.getInstance().printToConsole(Arrays.toString(fileNames));
        ConsoleManager.getInstance().consoleLineBreak();
    }

    private void createFolder(){
        displayActualAbsolutPath();
        ConsoleManager.getInstance().printToConsole("Voulez vous créer votre fichier ici ? Oui/Non");
        String response = ConsoleManager.getInstance().readUserInput();
        if (response.equalsIgnoreCase("oui")){
            ConsoleManager.getInstance().printToConsole("Saisissez le nom du dossier ");
            String folderName = ConsoleManager.getInstance().readUserInput();
        } else {
            ConsoleManager.getInstance().printToConsole("Déplacer vous vers le dossier voulu ");
        }

    }

    private void printTitle(String title) {
        ConsoleManager.getInstance().consoleLineBreak();
        ConsoleManager.getInstance().printLine();
        ConsoleManager.getInstance().printToConsole(title, true);
        ConsoleManager.getInstance().printLine();
        ConsoleManager.getInstance().consoleLineBreak();
    }

    private String printMenu(ServiceAction[] actions) {
        boolean rightAnswer = false;
        String answer = "";

        do {
            // print the option menu
            ConsoleManager.getInstance().printLine();
            ConsoleManager.getInstance().consoleLineBreak();

            for (ServiceAction action : actions) {
                ConsoleManager.getInstance().printToConsole(action.toString(), true);
            }

            ConsoleManager.getInstance().printToConsole("What do you want to do : ", false);

            // ask user answer
            answer = ConsoleManager.getInstance().readUserInput();

            if (LinuxConsoleWithMenuActions.containsAction(answer)) {
                rightAnswer = true;
            }
        } while (!rightAnswer);

        return answer;
    }
}
