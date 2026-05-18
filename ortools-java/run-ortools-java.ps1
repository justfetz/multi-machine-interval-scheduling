$env:JAVA_HOME = "C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot"
$env:Path = "$env:JAVA_HOME\bin;$env:Path"
$maven = "..\..\tools\apache-maven-3.9.9\bin\mvn.cmd"
$mavenRepo = "..\..\tools\m2-repository"
New-Item -ItemType Directory -Force $mavenRepo | Out-Null
& $maven -q "-Dmaven.repo.local=$mavenRepo" compile exec:java "-Dexec.mainClass=mmis.ortools.Main" "-Dexec.args=..\input\sample_jobs.csv 3 ..\output\sample_schedule_ortools_java.csv ..\output\sample_unassigned_jobs_ortools_java.csv"
exit $LASTEXITCODE
