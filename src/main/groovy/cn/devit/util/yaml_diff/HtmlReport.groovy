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

import com.google.common.collect.Table
import groovy.xml.MarkupBuilder

class HtmlReport {
  public static void htmlTable(Table tableData, List<String> columns, File file) {
    MarkupBuilder b = new MarkupBuilder(new FileWriter(file));
    String html = b.html(lang: 'zh-CN') {
      head() {
        link(rel: 'stylesheet',
            href: 'https://cdn.bootcdn.net/ajax/libs/twitter-bootstrap/4.5.2/css/bootstrap.css'
        )
      }
      body {
        h1("YAML比较工具")
        div(class: 'container-fluid',style: 'font-family:monospace') {
          p("清单")
          table(class: 'table table-bordered table-hover table-sm') {
            thead {
              tr {
                th("KEY")
                columns.each { col ->
                  th(col)
                }
              }
            }
            tbody {
              tableData.rowKeySet().each { rowKey ->
                def row = tableData.row(rowKey);
                tr() {
                  td(rowKey)
                  columns.each { col ->
                    td(row[col])
                  }
                }
              }
            }

          }
          h1("行列转置")
          tableData.rowKeySet().each { rowKey ->
            def row = tableData.row(rowKey);
            table(class: 'table table-bordered table-hover table-sm', style: 'table-layout:fixed') {
              thead {
                th(width: 180); th(rowKey)
              }
              tbody {
                columns.each { col ->
                  tr { td(col); td(row[col]) }
                }
              }
            }
          }
        }
      }
    }
  }
}
