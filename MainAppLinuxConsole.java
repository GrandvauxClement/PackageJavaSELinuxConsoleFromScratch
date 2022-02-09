package com.CDAHb.debutClassJavaSE.FileManagement;

import com.CDAHb.debutClassJavaSE.FileManagement.Services.LinuxConsoleService;
import com.CDAHb.debutClassJavaSE.FileManagement.Services.LinuxConsoleWithMenuService;

public class MainAppLinuxConsole {
    public static void main(String[] args) {
      //  LinuxConsoleWithMenuService linuxConsoleWithMenuService = new LinuxConsoleWithMenuService();
      //  linuxConsoleWithMenuService.run();
        LinuxConsoleService linuxConsoleService = new LinuxConsoleService();
        linuxConsoleService.run();
    }
}
