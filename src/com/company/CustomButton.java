package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomButton extends JButton{



    public CustomButton(String text){

        super(text);
        Font font = new Font("Arial", Font.BOLD, 10);
        setFont(font);
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
        setHorizontalTextPosition(JButton.CENTER);
        setFocusable(false);

    }

    public void setPosition(int x,int y){

        setLocation(x,y);
    }
    public void setSizeForCbtn(int width,int height){
        setSize(width, height);
    }

    @Override
    public void addActionListener(ActionListener l) {
        super.addActionListener(l);
    }
}
