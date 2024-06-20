package extentUtility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentUtility {
    private static final String pathToProject = System.getProperty("user.dir") + "/target/extentReports/";
    private static ExtentReports extent;
    private static ExtentTest testReport;

    public static void initiateReport() {
        createDirectory();
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(pathToProject + "ExtentReport.html");
        htmlReporter.config().setTheme(Theme.DARK);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    public static void startTest(String testName) {
        testReport = extent.createTest(testName + " - report");
        attachReportLog(ReportStep.INFO_STEP, "---- START TEST ----" + testName);
    }

    public static void finishTest(String testName) {
        attachReportLog(ReportStep.INFO_STEP, "---- FINISH TEST ----" + testName);
    }

    public static void attachReportLog(String attachType, String message) {
        if (attachType.equals(ReportStep.INFO_STEP)) {
            testReport.log(Status.INFO, message);
        }
        if (attachType.equals(ReportStep.PASS_STEP)) {
            testReport.log(Status.PASS, message);
        }
        if (attachType.equals(ReportStep.FAIL_STEP)) {
            testReport.log(Status.FAIL, message);
        }
    }

    public static void generateReport() {
        extent.flush();
    }

    private static void createDirectory() {
        File directory = new File(pathToProject);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    // metoda care face un test report
    // metoda care adauga un entry in test report
    // metoda care genereaza raportul


}
