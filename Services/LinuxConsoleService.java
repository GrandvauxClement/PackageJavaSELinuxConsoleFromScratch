package com.CDAHb.debutClassJavaSE.FileManagement.Services;

import com.CDAHb.debutClassJavaSE.FileManagement.Services.actions.LinuxConsoleActions;
import com.CDAHb.debutClassJavaSE.Garage.utils.ConsoleManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

public class LinuxConsoleService {

    private File file;

    public LinuxConsoleService(){
        this.file = new File("D:\\");
    }

    public void run(){
        printTitle("Welcome to the linux console from scratch");

        String answer;

        do{
            displayActualAbsolutPath();
            answer = ConsoleManager.getInstance().readUserInput();
            ConsoleManager.getInstance().consoleLineBreak();
            handleAction(answer);
        }while (!answer.equalsIgnoreCase("exit"));
    }

    private void handleAction(String answer){
        String folderName;
        String action = answer.split(" ")[0];
        String[] stock = answer.split(" ");
        if (answer.split(" ").length < 2){
            folderName = "";
        }else {
            folderName = answer.split(" ")[1];
        }

        if (action.equals(LinuxConsoleActions.NAVIGATE_FILE_SYSTEM.getValue()) ){
            navigate(folderName);
        }

        if (action.equals(LinuxConsoleActions.LIST_FILES_AND_FOLDER.getValue())){
            listFileAndFolder();
        }

        if (action.equals(LinuxConsoleActions.CREATE_FOLDER.getValue())){
            createFolder(folderName);
        }

        if (action.equals(LinuxConsoleActions.CREATE_FILES.getValue())){
            createFile(folderName);
        }

        if (action.equals(LinuxConsoleActions.DELETE.getValue())){
            remove(folderName);
        }

        if (action.equals(LinuxConsoleActions.READ_FILE_AND_DISPLAY_CONSOLE.getValue())){
            displayFileContainToConsole(folderName);
        }
    }

    private void navigate(String folderName){
        if (folderName.equals("..")){
            if (this.file.getParent() != null){

                this.file = this.file.getParentFile();
            }else{
                ConsoleManager.getInstance().printToConsoleError("Error, this folder havent's any parents !!", true);
                ConsoleManager.getInstance().consoleLineBreak();
            }

        }else if (folderName.isBlank()){
            this.file = new File("D:\\");
        } else {
            File testDirectory = new File(this.file.getAbsolutePath() +"\\"+ folderName);
            if ( testDirectory.isDirectory()){
                this.file = new File(this.file.getAbsolutePath() +"\\"+ folderName);
            } else {
                ConsoleManager.getInstance().printToConsoleError("This folder doesn't exist !", true);
            }
        }
    }

    private void displayActualAbsolutPath(){
        ConsoleManager.getInstance().printToConsole("cleme@DESKTOP-G9B0VFP  ", false);
        ConsoleManager.getInstance().printToConsole(this.file.getAbsolutePath(), true);
        ConsoleManager.getInstance().printToConsole("$ ", false);
    }

    private void listFileAndFolder(){
        String [] fileNames = this.file.list();
        ConsoleManager.getInstance().printToConsole(Arrays.toString(fileNames));
        ConsoleManager.getInstance().consoleLineBreak();
    }

    private void createFolder(String folderName){
        File tempFile = new File(this.file.getAbsolutePath() + "\\" + folderName);
        if (tempFile.mkdir()){
            ConsoleManager.getInstance().printToConsole("Succes : "+folderName+" folder has been create");
        }else {
            ConsoleManager.getInstance().printToConsoleError("Error cannot create folder "+folderName+" Please retry", true);
        }

    }

    private void createFile(String fileName){
        File tempFile = new File(this.file.getAbsolutePath() + "\\" + fileName);
        try {
            if (tempFile.createNewFile()){
                ConsoleManager.getInstance().printToConsole("Succes : "+fileName+" file has been create");
            }else {
                ConsoleManager.getInstance().printToConsoleError("Error cannot create file "+fileName+" Please retry", true);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void remove(String fileName){
        File tempFile = new File(this.file.getAbsolutePath() + "\\" + fileName);
        if (checkIfExist(tempFile)){
            if (tempFile.delete()){
                ConsoleManager.getInstance().printToConsole("Succes : "+fileName+" has been remove");
            } else {
                ConsoleManager.getInstance().printToConsoleError("Error, a folder must be empty to be deleted", true);
            }
        } else {
            ConsoleManager.getInstance().printToConsoleError("This file or folder doesn't exist !", true);
        }

    }

    private boolean checkIfExist(File file){
        return file.exists();
    }

    private void displayFileContainToConsole(String fileName){
        File tempFile = new File(this.file.getAbsolutePath() + "\\" + fileName);
        if (checkIfExist(tempFile)){

           /* try( FileInputStream fileInputStream = new FileInputStream(tempFile)){
                int data;
                // read data from the steam
                while (( data = fileInputStream.read()) >= 0) {
                    System.out.println(data);
                }

            }catch (IOException e){
                e.printStackTrace();
            }*/
            try {
                Stream<String> lines = Files.lines(Paths.get(tempFile.getAbsolutePath()));
                lines.forEachOrdered(System.out::println);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ConsoleManager.getInstance().printToConsoleError("This file doesn't exist !", true);
        }

    }

    private void printTitle(String title) {
        ConsoleManager.getInstance().consoleLineBreak();
        ConsoleManager.getInstance().printLine();
        ConsoleManager.getInstance().printToConsole(title, true);
        ConsoleManager.getInstance().printLine();
        ConsoleManager.getInstance().consoleLineBreak();
    }
}
