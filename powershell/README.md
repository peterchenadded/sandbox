notes for powershell from reading:
Learn Windows PowerShell 3 in a Month of Lunches, 2nd Edition

# Get version
$PSVersionTable

# Get help
help get-service
help *log*
help *event*

Get-EventLog Application -computer (Get-Content names.txt)

help get-eventlog -examples

