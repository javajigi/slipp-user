package net.slipp.support;

public class ChromWebDriverUtils {
    private static String OS = System.getProperty("os.name").toLowerCase();
    
    public static String getChromeWebDriverPath() {
        return ChromWebDriverUtils.class.getResource("/webdriver/" + getChromeWebDriverFileNameForOS()).getPath();
    }
    
    private static String getChromeWebDriverFileNameForOS() {
        if (isWindows()) {
            return "chromedriver-win.exe";
        }
        if (isMac()) {
            return "chromedriver-mac";
        }
        if (isLinux32()) {
            return "chromedriver-linux32";
        }
        if (isLinux64()) {
            return "chromedriver-linux64";
        }

        throw new RuntimeException("지원하는 OS 가 없습니다.");
    }

    private static boolean isWindows() {
        return (OS.indexOf("win") >= 0);
    }

    private static boolean isMac() {
        return (OS.indexOf("mac") >= 0);
    }

    private static boolean isLinux32() {
        throw new UnsupportedOperationException("리눅스는 아직 누가 구문을 채워주세용");
    }

    private static boolean isLinux64() {
        throw new UnsupportedOperationException("리눅스는 아직 누가 구문을 채워주세용");
    }
}
