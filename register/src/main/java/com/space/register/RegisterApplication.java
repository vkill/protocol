package com.space.register;

import com.space.register.GuiView.JavaApplication1.src.guiview.JFrame.MainFrame;
import com.space.register.configurer.RegisterThreadDatabaseImpl;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import platform.threadManager.BussinessController;

@SpringBootApplication
public class RegisterApplication {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
                SpringApplication.run(RegisterApplication.class, args);

                //RegisterThreadDatabaseImpl registerThreadDatabase = new RegisterThreadDatabaseImpl();
            }
        });
//        SpringApplication.run(RegisterApplication.class, args);

    }
}
