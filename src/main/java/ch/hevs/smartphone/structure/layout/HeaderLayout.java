package ch.hevs.smartphone.structure.layout;

import ch.hevs.smartphone.bases.ButtonIcon;
import ch.hevs.smartphone.enums.ScreenSizeEnum;

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
    private ButtonIcon btnShutdown;

    // ICON
    private Icon iconPower;

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public HeaderLayout(){
        setPreferredSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMinimumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setMaximumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_FOOTER_HEIGHT.getSize()));
        setBackground(Color.WHITE);

        // Ajout des éléments
        add(buildButtonIconShutdown());
        add(buildLabelDate());
        add(buildLabelTime());
        //this.add(buildBattery());
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private JButton buildButtonIconShutdown(){
        URL imagePower = HeaderLayout.class.getClassLoader().getResource("HeaderIcon/powerIcon.png");
        iconPower = new ImageIcon(imagePower);
        btnShutdown = new ButtonIcon(iconPower);

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
    public void updateTime()
    {
        lblTime.setText(DateTimeFormatter.ofPattern("HH:mm:ss").format(LocalDateTime.now()));
    }
    public void updateDate()
    {
        lblDate.setText(DateTimeFormatter.ofPattern("dd.MM.yyyy").format(LocalDateTime.now()));
    }
}