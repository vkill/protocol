package com.space.register;

import com.space.register.GuiView.JavaApplication1.src.guiview.JFrame.MainFrame;
import com.space.register.configurer.RegisterThreadDatabaseImpl;
import com.space.register.entity.DYUserEntity;
import com.space.register.entity.DeviceEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegisterApplication {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MainFrame().setVisible(true);
                SpringApplication.run(RegisterApplication.class, args);
                RegisterThreadDatabaseImpl registerThreadDatabase = new RegisterThreadDatabaseImpl();
                DYUserEntity dyUserEntity = new DYUserEntity();
                dyUserEntity.setSimulationID(1+"");
                DeviceEntity deviceEntity = new DeviceEntity();
                deviceEntity.setSession_id("wewe");
                registerThreadDatabase.saveDevice(deviceEntity);
                registerThreadDatabase.saveUser(dyUserEntity);

            }
        });
//        SpringApplication.run(RegisterApplication.class, args);

    }
}
