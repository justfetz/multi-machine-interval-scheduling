$javaHome = "C:\Program Files\Eclipse Adoptium\jdk-21.0.11.10-hotspot\bin"
$javac = Join-Path $javaHome "javac.exe"
$java = Join-Path $javaHome "java.exe"

New-Item -ItemType Directory -Force out | Out-Null
& $javac -d out src\mmis\*.java tests\TestGreedyScheduler.java
if ($LASTEXITCODE -ne 0) {
    exit $LASTEXITCODE
}

& $java -cp .\out mmis.tests.TestGreedyScheduler
exit $LASTEXITCODE
