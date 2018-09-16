    set jhome=C:\Program Files\Java\jdk1.8.0_181
    set maven=C:\DEV\apache-maven-3.5.4
    setx /m JAVA_HOME "%jhome%"
    setx /m Path "%path%;%jhome%\bin"
    setx /m Path "%path%;%maven%\bin"
pause