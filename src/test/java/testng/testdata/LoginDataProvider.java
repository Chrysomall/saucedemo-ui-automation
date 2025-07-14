package testng.testdata;

import org.testng.annotations.DataProvider;

import java.util.HashMap;

public class LoginDataProvider {

    @DataProvider(name = "loginUsers")
    public static Object[][] getLoginUsers() {
        String password = "secret_sauce";

        HashMap<String, String> standardUser = new HashMap<>();
        standardUser.put("username", "standard_user");
        standardUser.put("password", password);
        standardUser.put("expectedSuccess", "true");

        HashMap<String, String> lockedOutUser = new HashMap<>();
        lockedOutUser.put("username", "locked_out_user");
        lockedOutUser.put("password", password);
        lockedOutUser.put("expectedSuccess", "false");
        lockedOutUser.put("expectedError", "Epic sadface: Sorry, this user has been locked out.");

        HashMap<String, String> problemUser = new HashMap<>();
        problemUser.put("username", "problem_user");
        problemUser.put("password", password);
        problemUser.put("expectedSuccess", "true");

        HashMap<String, Object> performanceGlitchUser = new HashMap<>();
        performanceGlitchUser.put("username", "performance_glitch_user");
        performanceGlitchUser.put("password", password);
        performanceGlitchUser.put("expectedSuccess", "true");

        HashMap<String, Object> errorUser = new HashMap<>();
        errorUser.put("username", "error_user");
        errorUser.put("password", password);
        errorUser.put("expectedSuccess", "true");

        HashMap<String, Object> visualUser = new HashMap<>();
        visualUser.put("username", "visual_user");
        visualUser.put("password", password);
        visualUser.put("expectedSuccess", "true");

        return new Object[][]{
                {standardUser},
                {lockedOutUser},
                {problemUser},
                {performanceGlitchUser},
                {errorUser},
                {visualUser}
        };
    }
}

