package config;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {
    public static ExtentReports getReportObject() {
        String reportPath = System.getProperty("user.dir") + "/" + ConfigReader.get("reportPath");
        //metadata
        ExtentSparkReporter report = new ExtentSparkReporter(reportPath);
        report.config().setReportName("Web Automation Results");
        report.config().setDocumentTitle("Automation Results");
        report.config().setTheme(Theme.STANDARD);

        ExtentReports extent = new ExtentReports();
        extent.attachReporter(report);
        extent.setSystemInfo("Tester", "Chrysomall");
        return extent;
    }
}
