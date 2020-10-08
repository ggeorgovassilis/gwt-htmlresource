package com.georgovassilis.gwthtmlresource.client;

/**
 * Based on TextResource from the GWT project. Original copyright follows.
 *
 *
 * Copyright 2008 Google Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
import com.georgovassilis.gwthtmlresource.generator.HtmlResourceGenerator;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.resources.ext.DefaultExtensions;
import com.google.gwt.resources.ext.ResourceGeneratorType;

@DefaultExtensions(value = {".html"})
@ResourceGeneratorType(HtmlResourceGenerator.class)
public interface HtmlResource extends ResourcePrototype {
  String getText();
}
