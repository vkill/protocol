package com.space.register;

import com.space.register.GuiView.JavaApplication1.src.guiview.JFrame.MainFrame;
import com.space.register.configurer.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RegisterApplication {

    public static void main(String[] args) {
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new MainFrame().setVisible(true);
//                SpringApplication.run(RegisterApplication.class, args);
//            }
//        });
        SpringApplication.run(RegisterApplication.class, args);
    }
}
