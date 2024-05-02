package constants;

public class PlatformConstants {

    public static class Android {
        public static final String PLATFORM_VERSION = "13";
        public static final String DEVICE_NAME = "emulator-5554";
        public static final String APP = "E:\\apkOnliner\\by.onliner.catalog-216400.apk";
        public static final String APP_WAIT_ACTIVITY = "*";
        public static final String AUTOMATER_NAME = "UiAutomator2";
        public static final String NO_RESET = "true";
    }

    public static class IOS {
        public static final String PLATFORM_VERSION = "7";
        public static final String DEVICE_NAME = "emulator-1111";
        public static final String APP_PACKAGE = "com.ios.chrome";
        public static final String NO_RESET = "true";
        public static final String APP_ACTIVITY = null;
    }
}