import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WebCam {

/*
    public static void main(String[] args) {

        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        WebcamPanel webcamPanel = new WebcamPanel(webcam);
        webcamPanel.setImageSizeDisplayed(true);
        webcamPanel.setMirrored(true);

        JFrame frame = new JFrame("S3CURUS");
        JPanel panel = new JPanel();
        JButton openBt = new JButton("Confirm Your Face");
        openBt.addActionListener(actionEvent1 -> {
            webcam.close();
            FaceDetector faceDetector = new FaceDetector();
            try {
                faceDetector.detectFace();
            } catch (InterruptedException | IOException e) {
                e.printStackTrace();

            }
            Webcam webcam1 = Webcam.getDefault();
            webcam1.open();
            try {
                ImageIO.write(webcam1.getImage(), "JPG", new File("Securus.JPG"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            webcam1.close();


        });
        panel.add(openBt);
        frame.add(webcamPanel, BorderLayout.CENTER);
        frame.add(panel, BorderLayout.PAGE_END);
        frame.setLocation(100, 100);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

 */
}
