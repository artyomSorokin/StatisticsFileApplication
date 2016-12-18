Task:
1 Implement console application(mandatory)
  -  Read txt file and split it by lines
  -  Calculate statistic for each line: longest word(symbols between 2 spaces), shortest word, line length, average word length. Unit test are mandatory
  -  Aggregate these values for all lines from file(unit test)
  -  Store line and file statistic into DB(with JDBC).  
 
2. Implement Web application (mandatory)
  - Create Hiberante mapping  for tables
  - Return from server side list of handled files and statistic per file. You can use RESTful services or Spring MVC controllers
  - Implement frontend part - display list of files, and statistic per line for selected file. Usage of ReactJS framework is encouraged. In other case any other JS framework can be used
3. Add to console app possibility to handle all files in directory and sub-directories(optional)
4. Add to Web app filter by file's statistic(example: files with more than 10 lines)(optional)
5. Add to Web app possibility to enter and send own text for handling(optional)
6. Implement concurrent handling of each file in directory

Configuration:
1 Download and unzip project
2 Create in H2 database your database
3 Specify in file resources/config.properties parameters of connection to database
4 Build project, link for launch project: localhost:8080/fileStatistics
5 Log file on D:/logging.txt