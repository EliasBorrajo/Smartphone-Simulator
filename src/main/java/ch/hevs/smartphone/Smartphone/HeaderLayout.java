package ch.hevs.smartphone.Smartphone;

import ch.hevs.smartphone.Bases.MyButton;
import ch.hevs.smartphone.Bases.ScreenSizeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HeaderLayout extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    // LABEL
    private JLabel lblDate;
    private JLabel lblTime;

    // BUTTON
    private MyButton btnShutdown;

    // ICON
    private Icon iconPower;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public HeaderLayout(){
        setPreferredSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMaximumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        add(buildButtonIconShutdown(),BorderLayout.WEST);
        add(buildLabelDate(),BorderLayout.EAST);
        add(buildLabelTime(),BorderLayout.CENTER);
        //this.add(buildBattery());
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private JButton buildButtonIconShutdown(){
        URL imagePower = HeaderLayout.class.getClassLoader().getResource("HeaderIcon/powerIcon.png");
        iconPower = new ImageIcon(imagePower);
        btnShutdown = new MyButton(iconPower);

        btnShutdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        return btnShutdown;
    }

    private JLabel buildLabelDate() {
        lblDate = new JLabel();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime date = LocalDateTime.now();
        lblDate.setText(dtf.format(date));
        return lblDate;
    }
    //@TODO: revoir code soit classe soit réactualisation
    private JLabel buildLabelTime(){
        lblTime = new JLabel();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        lblTime.setText(dtf.format(time));
        return lblTime;
    }
}