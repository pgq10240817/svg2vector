@ ECHO OFF  

if  "%JAVA_HOME%" == "" (goto setJava) else (goto convert)
:setJava
@ ECHO 您还没有安装JDK请 安装 并 设置环境变量
goto end

:convert
java -jar convertSVG2Vector.jar svg vector
@ ECHO 转换完毕
:end 
pause

