/**
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

import com.google.common.collect.HashBasedTable
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.core.io.FileSystemResource

class YamlDiff {

  def Properties read(File file) {
    YamlPropertiesFactoryBean p = new YamlPropertiesFactoryBean();
    p.setResources(new FileSystemResource(file));
    return p.getObject();
  }

  public DiffData diff(List<File> files) {
    Map<FileName, Properties> data = [:]
    files.each { it ->
      try{
        data.put(FileName.parser(it), read(it));
      }catch(RuntimeException e){
        e.printStackTrace();
      }
    }
    HashBasedTable table = HashBasedTable.<String, String, String> create();
    final int length = data.size();
    //把所有数据放进表格
    List columns = [];
    data.each { FileName k, Properties p ->
      columns << k.key();
      p.keys().each { String key ->
        if (table.containsRow(key)) {
          //已经放了
        } else {
          data.each { FileName kk, Properties pp ->
            table.put(key, kk.key(), (pp.get(key)?:"") as String);
          }
        }
      }
    }
    List rowsToDelete = []
    //把一样的数据从表格中剔除
    table.rowKeySet().each { String rowKey ->
      if (table.row(rowKey).values().toUnique().size() == 1) {
        rowsToDelete << rowKey;
      }
    }
    rowsToDelete.each { String row ->
      columns.each { String col ->
        table.remove(row, col);
      }
    }
    return new DiffData(columns: columns, table: table);
  }
}
