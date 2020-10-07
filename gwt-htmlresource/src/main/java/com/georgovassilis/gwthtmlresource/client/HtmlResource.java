package com.georgovassilis.gwthtmlresource.client;

import com.georgovassilis.gwthtmlresource.generator.HtmlResourceGenerator;
import com.google.gwt.resources.client.ResourcePrototype;
import com.google.gwt.resources.ext.DefaultExtensions;
import com.google.gwt.resources.ext.ResourceGeneratorType;

@DefaultExtensions(value = {".html"})
@ResourceGeneratorType(HtmlResourceGenerator.class)
public interface HtmlResource extends ResourcePrototype {
  String getText();
}
