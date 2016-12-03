## Get version
$PSVersionTable

## Get help
help get-service
help *log*
help *event*

Get-EventLog Application -computer (Get-Content names.txt)

help get-eventlog -examples

## search for command
get-comment *count*
get-help command-name
"string" | get-member
"string" | measure-object
get-history

## escape sequences
`n
`t

dir | out-string | get-member

"a" -like "*a*"
"a" -match "[a]"

for($counter = 0; $counter -lt 10; $counter++) { Write-Host "Processing item $counter" }
foreach ($i in dir) { write-host $i}

 "Hello World" -replace "(.*) (.*)",'$2 $1'
 World Hello
 -split "hello world"

## Objects and getting attributes
$date = get-date
$date
$date | get-member
$date.year

## Creating arrays
$myArray = "Element 1", "Element 2", "Element 3"
$myArray[0]

## Creating hashes
$users = @{"john.doe"="jdoe"; "jane.doe"="jdoe1"}
$users["john.doe"]
$users.add("John.Smith", "jsmith")

## Strings
$a = "Error: This is an example error"
$a.toUpper()
$a.replace("Error: ", "")
$a.split(" ")

## Maths
[math]::sqrt

## 16 GB of Memory in Bytes
$ComputerMemory = 16849174528
$ComputerMemory / 1TB
$ComputerMemory / 1GB
$ComputerMemory / 1MB
$ComputerMemory / 1KB

## Type conversion
[string]$myString = "Forcing a string container"
[int]$myInt = 1

get-process | where-object {$_.handles -ge 500} | sort-object handles

$xmlContent = [xml]"<html><body><div></div></body></html>"
$xmlContent.html

wmi = windows management instrumentation

## .NET Framework
[ClassName]::MethodName(parameter list)
[System.Diagnostics.Process]::GetProcessById(0)

## Windows WGET
$webClient = New-Object Net.WebClient
$webClient.DownloadString("http://google.com")

## Passes an array that contains the $item1 and $item2
$result = GetMyRules($item1, $item2)

## Correct approach
$result = GetMyRules $item1 $item2

## invoke/call operator (&)

The return value/output of a script is any data that it generates
but does not capture.
