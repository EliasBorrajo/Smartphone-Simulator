package ch.hevs.smartphone.Smartphone;

import ch.hevs.smartphone.Bases.ScreenSizeEnum;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HeaderLayout extends JPanel {
    //*****************************************************************************
    // A T T R I B U T S
    //*****************************************************************************
    private JPanel pnlIconShutdown = new JPanel();
    private JPanel pnlDate = new JPanel();
    private JPanel pnlTime = new JPanel();

    private JButton btnShutdown = new JButton("0/1");
    private JLabel lblDate = new JLabel();
    private JLabel lblTime = new JLabel();

    //*****************************************************************************
    // C O N S T R U C T E U R
    //*****************************************************************************
    public HeaderLayout(){

        this.setPreferredSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_HEIGHT.getSize()));
        this.setMinimumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_HEIGHT.getSize()));
        this.setMaximumSize(new Dimension(ScreenSizeEnum.WIDTH.getSize(), ScreenSizeEnum.HEADER_HEIGHT.getSize()));

        this.setLayout(new BorderLayout());     // @TODO: Changer de layout dns le turfu
        this.setBackground(Color.BLUE);

        this.add(buildPanelIconShutdown(),BorderLayout.WEST);
        this.add(buildPanelDate(), BorderLayout.CENTER);
        //this.add(buildPanelTime(),BorderLayout.NORTH);
        //this.add(buildBattery());
    }

    //*****************************************************************************
    // M E T H O D E S
    //*****************************************************************************
    private JPanel buildPanelIconShutdown(){
        btnShutdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        pnlIconShutdown.add(btnShutdown);
        return pnlIconShutdown;
    }

    private JPanel buildPanelDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDateTime date = LocalDateTime.now();
        lblDate.setText(dtf.format(date));
        pnlDate.add(lblDate);
        return pnlDate;
    }

    private JPanel buildPanelTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        lblTime.setText(dtf.format(time));
        pnlTime.add(lblTime);
        return pnlTime;
    }

}
