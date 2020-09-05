# Yaml Diff

A script to export differenies between many yaml files.

## Build

    mvn package

## To Use

### By Code
    @Test
    public void shoult_show_1_different() {
      List<String> files = Arrays.asList("application.yml", "application-sit.yml", "application-uat.yml");
  
      List<File> files1 = files.stream().map(it -> new File("src/test/resources/" + it))
          .collect(Collectors.toList());
      YamlDiff diff = new YamlDiff();
      DiffData table = diff.diff(files1);
  
  
      assertThat(table.getColumns().size(), is(3));
      assertThat(table.getTable().rowKeySet().size(), is(1));
  
      HtmlReport.htmlTable(table.getTable(), table.getColumns(), new File("report.html"));
    }  

### By UI

    mvn -X javafx:run
    
## License

Apache License Version 2.0  