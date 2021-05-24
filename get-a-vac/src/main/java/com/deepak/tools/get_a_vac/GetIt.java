package com.deepak.tools.get_a_vac;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Change Date every day in URL and also the If-None-match with every login
 * @author abrahd2
 *
 */
public class GetIt {

    private static final String TRAY_TITLE = "GET THE VAC";
    private static final Logger logger = LoggerFactory.getLogger(GetIt.class);
    
    public static void main(String[] args) throws AWTException, InterruptedException {
        int minAgeLimit = 18;

        int maxAttempts = (int) Duration.ofHours(1).getSeconds();
        int unAuthErrors = 0;
        boolean trayMsgShown = false;
        int attempt = 0;
        
        try {
        while (maxAttempts-- > 0) {
            attempt++;
            RestTemplate restTemplate = new RestTemplate();
            String url = "";
            String url2 = "";
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            // headers.set("Accept-Encoding", "gzip, deflate, br");
            headers.set("Accept-Language", "en-US,en;q=0.5");
            headers.set("Authorization", "");
            headers.set("Connection", "keep-alive");
            headers.set("Host", "");
            headers.set("If-None-Match", "");
            headers.set("Origin", "");
            headers.set("Referer", "");
            headers.set("TE", "Trailers");
//            headers.set("Upgrade-Insecure-Requests", "1");
            headers.set("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:88.0) Gecko/20100101 Firefox/88.0");
            
            try {
                if (!callAndParseResult(restTemplate, url, headers, minAgeLimit)) {
                    logger.debug("Not found for url!  Attempt: {}/{}", attempt, maxAttempts);
                }
                if (!callAndParseResult(restTemplate, url2, headers, minAgeLimit)) {
                    logger.debug("Not found for url1!  Attempt: {}/{}", attempt, maxAttempts);
                }
                unAuthErrors = 0;
            } catch (HttpClientErrorException e) {
                e.printStackTrace();
                unAuthErrors++;
            }

            if (unAuthErrors > 1) {
                logger.warn(
                        "Too many HTTP erros were continuously thrown, not continuing.. check header vals to be set..");
                TrayIcon trayIcon = createSysTray();
                trayIcon.displayMessage(TRAY_TITLE, "Too many errors, exiting", MessageType.ERROR);
                trayMsgShown = true;
                break;
            }

            Thread.sleep(5000);
        }
        } finally {
            if (!trayMsgShown) {
                TrayIcon trayIcon = createSysTray();
                trayIcon.displayMessage(TRAY_TITLE, "Exiting! either timeout or some other issue", MessageType.WARNING);
            }
        }
    }

    private static boolean callAndParseResult(RestTemplate restTemplate, String url, HttpHeaders headers, int minAgeLimit)
            throws AWTException {
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(headers), String.class);
        
        String json = result.getBody();
        return parseResult(minAgeLimit, json);
    }

    public static boolean parseResult(int minAgeLimit, String json) throws AWTException {
        Assert.hasText(json, "Returned JSON should have some text...");
//        logger.debug("Json ret:{}", StringUtils.substring(json, 0, 200));
//        int districtIndex = StringUtils.indexOfIgnoreCase(json, "APOLLO HOSPITAL");
//        if (districtIndex != -1) {
//            logger.info("district: {}", StringUtils.substring(json, districtIndex - 50, districtIndex + 500));
//        }
        Pattern centerSubJsonPattern = Pattern.compile("\\{\"center_id\":.+?\\}\\]\\}");//ends in another center_id or end of centers
        Matcher centerMatcher = centerSubJsonPattern.matcher(json);
        boolean found = false;
        while (centerMatcher.find()) {
            String subJson = centerMatcher.group(0);
            if (found) {
                findInCentreJsonSubStr(minAgeLimit, subJson);
            } else {
                found = findInCentreJsonSubStr(minAgeLimit, subJson);
            }
        }
        
        return found; 
    }

    private static boolean findInCentreJsonSubStr(int minAgeLimit, String json) throws AWTException {
        Pattern pattern = Pattern.compile(String.format("\"available_capacity\":[1-9]\\d*,\"min_age_limit\":%d,.+?\"available_capacity_dose1\":(\\d+)", minAgeLimit));
        
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            if (Integer.parseInt(matcher.group(1)) == 0) {
                return false;
            }
            String matchedText = matcher.group();
            int matchedIndex = json.indexOf(matchedText);
            int startIndex = matchedIndex - 1500;
            if (startIndex < 0) {
                startIndex = 0;
            }
            int endIndex = matchedIndex + 100;
            if (endIndex > json.length()) {
                startIndex = json.length();
            }
            String strPart = json.substring(startIndex, endIndex);
            logger.info("FOUND: {}", json);
            
            TrayIcon trayIcon = createSysTray();
            
            String displayStr = strPart.substring(0, strPart.length() >= 100 ? 100 : strPart.length());
            int pinStartIndex = strPart.lastIndexOf("\"pincode\"");
            if (pinStartIndex != -1) {
                try {
                    displayStr = strPart.substring(pinStartIndex - "\"pincode\"".length(), pinStartIndex + 20);
                } catch (IndexOutOfBoundsException e) {
                    logger.warn("{}", e.getMessage());
                }
            }
            logger.info("Display str msg: {}", displayStr);
            trayIcon.displayMessage(TRAY_TITLE, "Found: " + displayStr, MessageType.INFO);
            return true;
        }
        return false;
    }

    private static TrayIcon createSysTray() throws AWTException {
        SystemTray tray = SystemTray.getSystemTray();
        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");
        //Alternative (if the icon is on the classpath):
        //Image image = Toolkit.getDefaultToolkit().createImage(getClass().getResource("icon.png"));

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("Get the vac flash message");
        tray.add(trayIcon);
        return trayIcon;
    }
}