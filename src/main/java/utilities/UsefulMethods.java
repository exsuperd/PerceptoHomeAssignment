package utilities;


import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class UsefulMethods {
    public static void uploadFileWithRobot(String imagePath) {
        StringSelection stringSelection = new StringSelection(imagePath);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        Robot robot = null;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(200);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    public static String getData(String Configuration) {
        DocumentBuilder dBuilder;
        Document doc = null;
        File fXmlFile = new File("./Configuration/DataConfig.xml");
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        } catch (Exception e) {
            System.out.println("Exception in reading XML file: " + e);
        }

        doc.getDocumentElement().normalize();
        return doc.getElementsByTagName(Configuration).item(0).getTextContent();
    }

    public static String getProjectPath() {
        return System.getProperty("user.dir");
    }

    public static LocalDateTime getPCDateTime(String dateFormat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormat);
        LocalDateTime now = LocalDateTime.now();
        System.out.println("Current PC time and date is " + dtf.format(now));
        return now;
    }

    public static String calculateTimeDifference(LocalDateTime baseTime, LocalDateTime newTime) {
        String timeDifferenceInMilliseconds = String.valueOf(ChronoUnit.MILLIS.between(baseTime, newTime));
        System.out.println("The time difference in milliseconds is " + timeDifferenceInMilliseconds);
        return timeDifferenceInMilliseconds;
    }

    public static int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

}
