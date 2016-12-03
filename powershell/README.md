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

## Escape sequences
	`n
	`t

## For loop
	for($counter = 0; $counter -lt 10; $counter++) { Write-Host "Processing item $counter" }
	foreach ($i in dir) { write-host $i}

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
	"a" -like "*a*"
	"a" -match "[a]"

## Type conversion
	[string]$myString = "Forcing a string container"
	[int]$myInt = 1

get-process | where-object {$_.handles -ge 500} | sort-object handles