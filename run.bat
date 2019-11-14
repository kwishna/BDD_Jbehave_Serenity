:: Comments
SET PROJECT_DIR=%CD%

:: (+) means included. (-) means excluded
SET METAFILTERS="+RegressionSuite,-skip"
SET THREAD=singlethread
SET MVNPROFILE=%THREAD%
SET DRIVER=chrome
SET BASEURL=https://www.google.co.in

CD PROJECT_DIR

echo started cleaning project...
CALL mvn clean
echo cleaning completed successfully!

echo started compiling project...
CALL mvn compile
echo compiling completed successfully!

echo running in %MVNPROFILE% mode
CALL mvn verify -P %MVNPROFILE% -Dmetafilter=%METAFILTERS% -Ddriver=%DRIVER% -Dwebdriver.base.url=%BASEURL% -Dwebdriver.driver=%DRIVER%


