#Set-ExecutionPolicy Unrestricted
iex (new-object net.webclient).downloadstring('https://get.scoop.sh')
scoop install allure
allure --version
allure serve "\ExampleAutotest\allure-results" -p 50101