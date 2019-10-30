
import com.aventstack.extentreports.ExtentReports;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class MobileAPI {


    public static AppiumDriver ad = null;
    public static ExtentReports extent;
    public String OS = null;
    public String deviceName = null;
    public String deviceType = null;
    public String appType = null;
    public String version = null;
    public File appDirectory = null;
    public File findApp = null;
    public DesiredCapabilities cap = null;


    @Parameters({"OS", "deviceType", "deviceName", "version"})
    @BeforeMethod
    public void setUp(@Optional("ios") String OS, @Optional("Simulator") String deviceType,
                      @Optional("iPhone 8 Plus") String deviceName,
                      @Optional("12.2") String version) throws IOException {

        if (OS.equalsIgnoreCase("ios")) {
            if (appType.contains("iPhone")) {
                appDirectory = new File("/Users/djafarouldslimane/Documents/PhoneAutomation/Generic/src/main/resources/VLC for iOS.app");
                findApp = new File(appDirectory, "UICatalog6.1.app.zip");
                if (deviceType.equalsIgnoreCase("RealDevice")) {
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                    cap.setCapability(MobileCapabilityType.APP, findApp.getAbsolutePath());
                    ad = new IOSDriver(new URL("http://10.11.11.169:1122/wd/hub"), cap);
                    ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


                } else if (deviceType.equalsIgnoreCase("Simulator")) {
                    cap = new DesiredCapabilities();
                    cap.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
                    cap.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.IOS);
                    cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, version);
                    cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
                    cap.setCapability(MobileCapabilityType.APP, appDirectory);//findApp.getAbsolutePath()
                    ad = new IOSDriver(new URL("http://10.11.11.169:1122/wd/hub"), cap);
                    ad.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
                }
            }
        }
    }
}



