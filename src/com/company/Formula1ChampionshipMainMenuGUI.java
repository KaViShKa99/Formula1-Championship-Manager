package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Formula1ChampionshipMainMenuGUI extends JFrame {

    public Formula1ChampionshipMainMenuGUI(){


        menuTopicLabel();
        menuBackground();
        standingButton();

        setLayout(new FlowLayout());
        setVisible(true);

        setSize(850,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void menuBackground(){
        ImageIcon bgImage = new ImageIcon("src/images/blackbg.png");

        JLabel bgLabel = new JLabel();
        bgLabel.setIcon(bgImage);
        add(bgLabel);

    }
    public void menuTopicLabel(){
        Font fn = new Font("Arial",Font.BOLD,30);
        JLabel mainTopic = new JLabel("Formula1 Championship Management System");
        mainTopic.setFont(fn);
        mainTopic.setHorizontalTextPosition(0);

        add(mainTopic);

    }
    public void standingButton(){
        Font fn = new Font("Arial",Font.BOLD,20);
        JButton b1 = new JButton("Standing");
        b1.setFont(fn);
        b1.setHorizontalTextPosition(JButton.CENTER);
        b1.setFocusable(false);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        add(b1);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Formula1ChampionshipTableGUI();
                dispose();
            }
        });
    }

}
