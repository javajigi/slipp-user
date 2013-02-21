package net.slipp.support;

public class ChromWebDriverUtils {
    private static String OS = System.getProperty("os.name").toLowerCase();
    
    public static String getChromeWebDriverPath() {
        return ChromWebDriverUtils.class.getResource("/webdriver/" + getChromeWebDriverFileNameForOS()).getPath();
    }
    
    private static String getChromeWebDriverFileNameForOS() {
        if (isWindows()) {
            return "chromedriver-win.exe";
        } else if (isMac()) {
            return "chromedriver-mac";
        } else if (isLinux32()) {
            return "chromedriver-linux32";
        } else if (isLinux64()) {
            return "chromedriver-linux64";
        } else {
            throw new RuntimeException("지원하는 OS 가 없습니다.");
        }
    }

    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    private static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    private static boolean isLinux32() {
        return (OS.indexOf("linux") >= 0);
    }

    private static boolean isLinux64() {
        return (OS.indexOf("linux") >= 0);
    }
}
