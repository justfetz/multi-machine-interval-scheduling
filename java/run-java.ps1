$javaHome = "C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot\bin"
$javac = Join-Path $javaHome "javac.exe"
$java = Join-Path $javaHome "java.exe"

New-Item -ItemType Directory -Force out | Out-Null
& $javac -d out src\mmis\*.java
& $java -cp .\out mmis.Main ..\input\sample_jobs.csv 3 ..\output\sample_schedule_greedy_java.csv ..\output\sample_unassigned_jobs_java.csv
