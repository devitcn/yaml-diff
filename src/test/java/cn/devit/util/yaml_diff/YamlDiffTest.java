/*
 * Copyright © 2020 lxb (lxbzmy@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.devit.util.yaml_diff;

import groovy.lang.Closure;
import org.codehaus.groovy.runtime.DefaultGroovyMethods;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class YamlDiffTest {
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

}
