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

import com.google.common.io.Files

class FileName {
  String filename;
  String first;
  String second;

  public static FileName parser(File file) {
    def baseName = Files.getNameWithoutExtension(file.getName());
    String[] segment = baseName.split('-');
    final String first = segment[0];
    final String second;
    if (segment.length == 2) {
      second = segment[1]
    } else {
      second = "";
    }
    return new FileName(filename: file.getName(), first: first, second: second);
  }

  public String key() {
    return filename;
  }

}