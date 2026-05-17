Write-Host "Running Java baseline..."
Push-Location ".\java"
powershell -ExecutionPolicy Bypass -File .\run-java.ps1
Pop-Location

Write-Host "Running Python greedy baseline..."
Push-Location ".\python"
powershell -ExecutionPolicy Bypass -File .\run-python.ps1
Pop-Location

Write-Host "Running Python OR-Tools baseline..."
Push-Location ".\ortools-python"
powershell -ExecutionPolicy Bypass -File .\run-ortools-python.ps1
Pop-Location

Write-Host "Completed all solver runs."
