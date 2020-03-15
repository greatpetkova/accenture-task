package utils;

public class EnvironmentHelper {
    public static boolean isDockerEnv() {
        return System.getenv("RUN_ENV") != null && System.getenv("RUN_ENV").equals("DOCKER");
    }
}
